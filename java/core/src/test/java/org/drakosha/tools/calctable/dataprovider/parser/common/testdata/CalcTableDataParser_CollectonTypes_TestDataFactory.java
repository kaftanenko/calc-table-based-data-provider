package org.drakosha.tools.calctable.dataprovider.parser.common.testdata;

import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.collection.SupportingCollectionItem;

public interface CalcTableDataParser_CollectonTypes_TestDataFactory {

	SupportingCollectionItem ENTRY_1 = //
			SupportingCollectionItem.of(
					true,
					0,
					"ABC"
			);

	SupportingCollectionItem ENTRY_2 = //
			SupportingCollectionItem.of(
					false,
					0,
					"BCD"
			);

}
