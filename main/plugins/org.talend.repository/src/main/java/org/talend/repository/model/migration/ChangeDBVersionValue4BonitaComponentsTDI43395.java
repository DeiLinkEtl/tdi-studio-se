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

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.model.components.ComponentUtilities;
import org.talend.core.model.components.ModifyComponentsAction;
import org.talend.core.model.components.conversions.IComponentConversion;
import org.talend.core.model.components.filters.IComponentFilter;
import org.talend.core.model.components.filters.NameComponentFilter;
import org.talend.core.model.migration.AbstractJobMigrationTask;
import org.talend.core.model.properties.Item;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class ChangeDBVersionValue4BonitaComponentsTDI43395 extends AbstractJobMigrationTask {

    @Override
    public ExecutionResult execute(Item item) {
        final ProcessType processType = getProcessType(item);
        String[] compNames = { "tBonitaDeploy", "tBonitaInstantiateProcess" };

        IComponentConversion conversion = new IComponentConversion() {

            public void transform(NodeType node) {
                if (node == null) {
                    return;
                }

                ElementParameterType parameter = ComponentUtilities.getNodeProperty(node, "DB_VERSION");

                if (parameter != null) {
                    String value = parameter.getValue();
                    if (value == null) {
                        return;
                    }

                    String oldDom4jVersion = "dom4j-1.6.1.jar";
                    String newDom4jVersion = "dom4j-2.1.1.jar";
                    if (value.indexOf(oldDom4jVersion) > -1) {
                        String newDBVersion = value.replaceAll(oldDom4jVersion, newDom4jVersion);
                        ComponentUtilities.setNodeValue(node, "DB_VERSION", newDBVersion);
                    }
                }
            }

        };

        boolean modified = false;
        for (String name : compNames) {
            IComponentFilter filter = new NameComponentFilter(name);

            try {
                modified |= ModifyComponentsAction.searchAndModify(item, processType, filter,
                        Arrays.<IComponentConversion> asList(conversion));
            } catch (PersistenceException e) {
                // TODO Auto-generated catch block
                ExceptionHandler.process(e);
                return ExecutionResult.FAILURE;
            }
        }

        if (modified) {
            return ExecutionResult.SUCCESS_NO_ALERT;
        } else {
            return ExecutionResult.NOTHING_TO_DO;
        }

    }

    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2020, 0, 2, 12, 0, 0);
        return gc.getTime();
    }
}
