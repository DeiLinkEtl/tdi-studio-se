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
package org.talend.designer.core.ui.editor.subjobcontainer;

import org.eclipse.gef.commands.Command;

public interface ICrossPlatformCreateConnectionRequest extends ICrossPlatformCreateRequest {

    ICrossPlatformEditPart getSourceEditPart();

    void setSourceEditPart(ICrossPlatformEditPart ep);

    ICrossPlatformEditPart getTargetEditPart();

    void setTargetEditPart(ICrossPlatformEditPart ep);

    Command getStartCommand();

    void setStartCommand(Command c);

}