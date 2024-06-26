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
 * created by JYHU on Mar 27, 2013 Detailled comment
 *
 */
public class ChangePigVersionOfPigLoadMigrationTask extends AbstractJobMigrationTask {

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.migration.AbstractItemMigrationTask#execute(org.talend.core.model.properties.Item)
     */
    @Override
    public ExecutionResult execute(Item item) {
        ProcessType processType = getProcessType(item);
        if (processType == null) {
            return ExecutionResult.NOTHING_TO_DO;
        }
        String[] componentsName = new String[] { "tPigLoad" }; //$NON-NLS-1$

        try {

            for (String element : componentsName) {
                IComponentFilter filter = new NameComponentFilter(element);
                ModifyComponentsAction.searchAndModify(item, processType, filter,
                        Arrays.<IComponentConversion> asList(new IComponentConversion() {

                            @Override
                            public void transform(NodeType node) {
                                ElementParameterType ept = ComponentUtilities.getNodeProperty(node, "PIG_VERSION");//$NON-NLS-1$
                                if (ept != null) {
                                    if ("CLOUDERA_0.20_CDH3U1".equals(ept.getValue())) {//$NON-NLS-1$
                                        ComponentUtilities.setNodeValue(node, "PIG_VERSION", "Cloudera_0_20_CDH3U1"); //$NON-NLS-1$//$NON-NLS-2$
                                    } else if ("MAPR".equals(ept.getValue())) {//$NON-NLS-1$
                                        ComponentUtilities.setNodeValue(node, "PIG_VERSION", "MapR");//$NON-NLS-1$//$NON-NLS-2$
                                    } else if ("MAPR2".equals(ept.getValue())) {//$NON-NLS-1$
                                        ComponentUtilities.setNodeValue(node, "PIG_VERSION", "MapR2");//$NON-NLS-1$//$NON-NLS-2$
                                    }
                                }
                            }

                        }));
            }

            return ExecutionResult.SUCCESS_NO_ALERT;
        } catch (Exception e) {
            ExceptionHandler.process(e);
            return ExecutionResult.FAILURE;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.migration.IMigrationTask#getOrder()
     */
    @Override
    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2013, 3, 27, 19, 0, 0);
        return gc.getTime();
    }

}
