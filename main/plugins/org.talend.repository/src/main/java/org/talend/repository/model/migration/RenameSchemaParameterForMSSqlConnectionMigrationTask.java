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

import java.util.Date;
import java.util.GregorianCalendar;

import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.model.migration.AbstractJobMigrationTask;
import org.talend.core.model.properties.Item;
import org.talend.core.repository.model.ProxyRepositoryFactory;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * wzhang class global comment. Detailled comment
 */
public class RenameSchemaParameterForMSSqlConnectionMigrationTask extends AbstractJobMigrationTask {

    @Override
    public ExecutionResult execute(Item item) {
        try {
            renamePasswordName(item);
            return ExecutionResult.SUCCESS_NO_ALERT;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return ExecutionResult.FAILURE;
        }
    }

    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2012, 1, 19, 16, 20, 0);
        return gc.getTime();
    }

    private void renamePasswordName(Item item) {
        ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        boolean modified = false;
        ProcessType processType = getProcessType(item);
        for (Object object : processType.getNode()) {
            if (object instanceof NodeType) {
                NodeType currentNode = (NodeType) object;
                if (currentNode.getComponentName().startsWith("tMSSqlConnection")) { //$NON-NLS-1$
                    for (Object o : currentNode.getElementParameter()) {
                        ElementParameterType para = (ElementParameterType) o;
                        if ("DB_SCHEMA".equals(para.getName())) { //$NON-NLS-1$
                            para.setName("SCHEMA_DB"); //$NON-NLS-1$
                            modified = true;
                            break;
                        }
                    }
                }
            }
        }
        if (modified) {
            try {
                factory.save(item, true);
            } catch (PersistenceException e) {
                ExceptionHandler.process(e);
            }
        }
    }
}
