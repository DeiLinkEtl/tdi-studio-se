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
package org.talend.repository.view.di.viewer.tester;

import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.tester.AbstractNodeTester;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ContextNodeTester extends AbstractNodeTester {

    private static final String IS_CONTEXT_TOP_NODE = "isContextTopNode"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     *
     * @see org.talend.repository.tester.AbstractNodeTester#testProperty(java.lang.Object, java.lang.String,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    protected Boolean testProperty(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof RepositoryNode) {
            RepositoryNode repositoryNode = (RepositoryNode) receiver;
            if (IS_CONTEXT_TOP_NODE.equals(property)) {
                return isContextTopNode(repositoryNode);
            }
        }
        return null;
    }

    public boolean isContextTopNode(RepositoryNode repositoryNode) {
        return isTypeNode(repositoryNode, ERepositoryObjectType.CONTEXT);
    }
}
