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
package org.talend.designer.runprocess.shadow;

import java.util.List;
import java.util.Map;

import org.talend.core.model.components.ComponentCategory;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.ui.component.ComponentsFactoryProvider;

/**
 * DOC cantoine class global comment. Detailled comment <br/>
 *
 * $Id$
 *
 */
public class FileInputXmlNode extends FileInputNode {

    private List<Map<String, String>> mapping = null;

    /**
     * Constructs a new FileInputNode.
     */
    public FileInputXmlNode(String filename, String loopQuery, List<Map<String, String>> mapping, Integer loopLimit,
            String encoding) {
        super("tFileInputXML", mapping.size()); //$NON-NLS-1$

        String limitLoop = ""; //$NON-NLS-1$
        if (loopLimit != null && loopLimit != 0) {
            limitLoop = Integer.toString(loopLimit);
        }

        String[] paramNames = new String[] { "FILENAME", "LOOP_QUERY", "MAPPING", "LIMIT", "ENCODING", "GENERATION_MODE" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        // see bug 9785.
        Object[] paramValues = new Object[] { filename, loopQuery, mapping, limitLoop, encoding, "Dom4j" }; //$NON-NLS-1$
        // Object[] paramValues = new Object[] { filename, loopQuery, mapping, limitLoop, encoding, "Xerces" }; //$NON-NLS-1$

        IComponent component = ComponentsFactoryProvider.getInstance().get("tFileInputXML",
                ComponentCategory.CATEGORY_4_DI.getName());
        this.setElementParameters(component.createElementParameters(this));
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] != null) {
                IElementParameter param = this.getElementParameter(paramNames[i]);
                if (param != null) {
                    param.setValue(paramValues[i]);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.designer.runprocess.shadow.ShadowNode#getMappingList()
     */
    @Override
    public List<Map<String, String>> getMappingList() {
        return mapping;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.talend.designer.runprocess.shadow.ShadowNode#setMappingList(java.util.List)
     */
    @Override
    public void setMappingList(List<Map<String, String>> mapping) {
        this.mapping = mapping;
    }
}
