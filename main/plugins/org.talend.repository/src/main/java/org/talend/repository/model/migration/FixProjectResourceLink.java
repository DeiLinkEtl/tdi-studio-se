// ============================================================================
//
// Copyright (C) 2006-2021 Talaxie Inc. - www.deilink.fr
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talaxie SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.model.migration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.runtime.model.emf.EmfHelper;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.migration.AbstractItemMigrationTask;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ReferenceFileItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.core.repository.utils.XmiResourceManager;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.URIHelper;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class FixProjectResourceLink extends AbstractItemMigrationTask {

    @Override
    public ExecutionResult execute(Item item) {
        if (item.getProperty().getAuthor() == null) {
            // this case shouldn't happen normally, but just in case, each item should have one author
            IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
            item.getProperty().setAuthor(factory.getRepositoryContext().getUser());
        }
        if (ERepositoryObjectType.getItemType(item).isDQItemType() && !ERepositoryObjectType.getItemType(item).isDIItemType()) {
            return ExecutionResult.NOTHING_TO_DO;
        }
        String propertyPath = getPropertyPath(item);
        String path = item.getState().getPath();
        if (item.getProperty().getAuthor().getLogin() != null) {
            if (org.apache.commons.lang.StringUtils.isBlank(path) && org.apache.commons.lang.StringUtils.isBlank(propertyPath)
                    || StringUtils.equals(path, propertyPath)) {
                return ExecutionResult.NOTHING_TO_DO;
            }
        }
        if (StringUtils.isNotBlank(propertyPath) && !StringUtils.equals(path, propertyPath)) {
            item.getState().setPath(propertyPath);
        }
        // if go here, it means property got wrong link to the project.
        // (like for example item look for: ../../../talend.project, but it should be: ../../talend.project)
        IProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        item.getProperty().setAuthor(factory.getRepositoryContext().getUser());

        clearReferenceResourcesFileName(item);

        // now we should remove wrong URI from resource manager.
        // if this bug above happen, means the resourceSet keep wrong link to the project
        XmiResourceManager manager = ProxyRepositoryFactory.getInstance().getRepositoryFactoryFromProvider().getResourceManager();
        List<Resource> toDelete = new ArrayList<Resource>();
        for (Resource resource : manager.resourceSet.getResources()) {
            if (resource.getURI().toString().contains(manager.getProjectFilename())) {
                try {
                    IFile file = URIHelper.getFile(resource.getURI());
                    if (!file.exists()) {
                        toDelete.add(resource);
                    }
                } catch (Exception e) {
                    toDelete.add(resource);
                }
            }
        }
        EmfHelper.visitChilds(item);
        try {
            factory.save(item, true);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        } catch (UnsupportedOperationException e1) {
            ExceptionHandler.process(e1);
        }

        manager.resourceSet.getResources().removeAll(toDelete);

        return ExecutionResult.SUCCESS_NO_ALERT;
    }

    private String getPropertyPath(Item item) {
        String propertyPath = null;
        Resource eResource = item.getProperty().eResource();
        if (eResource != null) {
            URI uri = eResource.getURI();
            if (uri != null) {
                String url = uri.toString();
                String sb = StringUtils.substringBefore(url, uri.lastSegment());
                String projectName = ResourcesPlugin.getWorkspace().getRoot()
                        .getProject(ProjectManager.getInstance().getCurrentProject().getTechnicalLabel()).getLocation()
                        .lastSegment();
                String folderName = ERepositoryObjectType.getFolderName(ERepositoryObjectType.getItemType(item));
                String seprator = projectName + IPath.SEPARATOR + folderName + IPath.SEPARATOR;
                String sa = StringUtils.substringAfter(sb, seprator);
                if (StringUtils.isNoneBlank(sa)) {
                    propertyPath = sa.substring(0, sa.length() - 1);
                }
            }
        }
        return propertyPath;
    }

    private void clearReferenceResourcesFileName(Item item) {
        EList<ReferenceFileItem> referenceResources = item.getReferenceResources();
        if (referenceResources != null && !referenceResources.isEmpty()) {
            for (ReferenceFileItem refFileItem : referenceResources) {
                refFileItem.setName(null);
            }
        }
    }

    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2010, 12, 20, 12, 0, 0);
        return gc.getTime();
    }

}
