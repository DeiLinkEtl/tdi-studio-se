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

import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.components.ComponentUtilities;
import org.talend.core.model.components.ModifyComponentsAction;
import org.talend.core.model.components.conversions.IComponentConversion;
import org.talend.core.model.components.filters.IComponentFilter;
import org.talend.core.model.components.filters.NameComponentFilter;
import org.talend.core.model.migration.AbstractJobMigrationTask;
import org.talend.core.model.properties.Item;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;

/**
 * this migrationTask for feature:11310, add a hide checkbox TRANSMIT_ORIGINAL_CONTEXT, the default value is true, so,
 * in this migrationTask, there should set the old job to false.
 */
public class AddTransmitOriginalContextFortRunJobMigrationTask extends AbstractJobMigrationTask {

    public ExecutionResult execute(Item item) {
        ProcessType processType = getProcessType(item);
        if (getProject().getLanguage() == ECodeLanguage.PERL || processType == null) {
            return ExecutionResult.NOTHING_TO_DO;
        }

        try {

            IComponentFilter filter = new NameComponentFilter("tRunJob"); //$NON-NLS-1$

            IComponentConversion addNewProperty = new IComponentConversion() {

                public void transform(NodeType node) {
                    ComponentUtilities.addNodeProperty(node, "TRANSMIT_ORIGINAL_CONTEXT", "CHECK"); //$NON-NLS-1$ //$NON-NLS-2$
                    ComponentUtilities.setNodeValue(node, "TRANSMIT_ORIGINAL_CONTEXT", "false"); //$NON-NLS-1$ //$NON-NLS-2$
                }
            };

            ModifyComponentsAction.searchAndModify(item, processType, filter, Arrays
                    .<IComponentConversion> asList(addNewProperty));

            return ExecutionResult.SUCCESS_NO_ALERT;

        } catch (Exception e) {
            ExceptionHandler.process(e);
            return ExecutionResult.FAILURE;
        }
    }

    public Date getOrder() {
        GregorianCalendar gc = new GregorianCalendar(2010, 2, 4, 12, 0, 0);
        return gc.getTime();
    }

}
