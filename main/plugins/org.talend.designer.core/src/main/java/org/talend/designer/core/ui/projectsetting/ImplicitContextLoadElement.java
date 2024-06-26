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
package org.talend.designer.core.ui.projectsetting;


import org.talend.core.model.process.Element;

/**
 * this class is only used in projectsetting to contains project's ImplicitContextLoadSettings
 */
public class ImplicitContextLoadElement extends Element {

    private static final String IMPLICIT_CONTEXT_LOAD_ELEMENT_NAME = "implicitContextLoad"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.process.Element#getElementName()
     */
    @Override
    public String getElementName() {
        // TODO Auto-generated method stub
        return IMPLICIT_CONTEXT_LOAD_ELEMENT_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.process.IElement#isReadOnly()
     */
    public boolean isReadOnly() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.core.model.process.IElement#setReadOnly(boolean)
     */
    public void setReadOnly(boolean readOnly) {
        // TODO Auto-generated method stub

    }

}
