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
package org.talend.spark.operation;
import java.util.List;

import org.talend.spark.TalendPairRDD;
import org.talend.spark.TalendRDD;
import org.talend.spark.function.KeyByCompareColFunction;
import org.talend.spark.utils.CompareCol;

/**
 * Company : Altic - LIPN
 * User: Tugdual Sarazin
 * Date: 22/04/14
 * Time: 12:48
 */

public class KeyByCompareCol {
	public static TalendPairRDD<List<Object>, List<Object>> run(final TalendRDD<List<Object>> dataM, final List<CompareCol> compCols) throws Exception {
    	return dataM.mapToPair(new KeyByCompareColFunction(compCols));
    }
}
