// ============================================================================
//
// Copyright (C) 2006-2023 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.action.business;

import java.util.List;

import org.eclipse.gef.commands.Command;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public interface ICrossPlatformAction {

    boolean isEnabled(List<Object> selected);

    Command createCommand(List<Object> objects);

}
