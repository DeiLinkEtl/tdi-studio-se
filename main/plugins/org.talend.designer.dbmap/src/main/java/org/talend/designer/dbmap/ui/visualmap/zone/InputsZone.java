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
package org.talend.designer.dbmap.ui.visualmap.zone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.designer.dbmap.managers.MapperManager;
import org.talend.designer.dbmap.ui.visualmap.zone.toolbar.ToolbarInputZone;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 *
 * $Id: InputsZone.java 1 2006-09-29 17:06:40Z nrousseau $
 *
 */
public class InputsZone extends Composite {

    private MapperManager mapperManager;

    private ToolbarInputZone toolbar;

    public InputsZone(Composite parent, int style, MapperManager mapperManager) {
        super(parent, style);
        GridLayout gridLayout = new GridLayout(1, true);
        gridLayout.marginBottom = 0;
        gridLayout.marginHeight = 0;
        gridLayout.marginLeft = 0;
        gridLayout.marginRight = 0;
        gridLayout.marginTop = 0;
        gridLayout.marginWidth = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.horizontalSpacing = 0;
        this.setLayout(gridLayout);
        // RowLayout layout = new RowLayout(SWT.VERTICAL);
        // this.setLayout(layout);

        this.mapperManager = mapperManager;
    }

    public void createHeaderZoneComponents() {
        toolbar = new ToolbarInputZone(this, SWT.BORDER, this.mapperManager);
        Composite toolBarComposite = toolbar.getComposite();
        toolBarComposite.setBackgroundMode(SWT.INHERIT_NONE);
        toolBarComposite.setBackground(toolBarComposite.getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        GridData gridDataToolbar = new GridData(GridData.FILL_HORIZONTAL);
        toolBarComposite.setLayoutData(gridDataToolbar);
        gridDataToolbar.grabExcessHorizontalSpace = true;

    }

    public ToolbarInputZone getToolbar() {
        return this.toolbar;
    }

}
