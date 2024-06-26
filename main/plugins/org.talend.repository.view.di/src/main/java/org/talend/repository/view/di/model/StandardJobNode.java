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
package org.talend.repository.view.di.model;

import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.StableRepositoryNode;
import org.talend.repository.view.di.i18n.Messages;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class StandardJobNode extends StableRepositoryNode {

    public static final String STANDARD_NODE_LABEL = Messages.getString("StandardJobNode_Label");

    public StandardJobNode(RepositoryNode parent) {
        super(parent, STANDARD_NODE_LABEL, ECoreImage.PROCESS_STANDARD_GENERIC_CATEGORY_CLOSE_ICON); // $NON-NLS-1$
        setType(ENodeType.SYSTEM_FOLDER);
        setProperties(EProperties.LABEL, ERepositoryObjectType.PROCESS);
        setProperties(EProperties.CONTENT_TYPE, ERepositoryObjectType.PROCESS);
    }

}
