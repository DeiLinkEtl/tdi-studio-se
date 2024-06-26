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
package org.talend.designer.core.ui.editor.dependencies.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.talend.designer.core.ui.editor.dependencies.model.JobContextTreeNode;

public class JobContextLabelProvider extends LabelProvider {

    @Override
    public Image getImage(Object element) {
        // TODO Auto-generated method stub
        return super.getImage(element);
    }

    @Override
    public String getText(Object element) {
        return ((JobContextTreeNode) element) == null ? "" : ((JobContextTreeNode) element).getName();
    }

}
