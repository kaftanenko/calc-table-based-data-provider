package org.business.tools.calctable.dataprovider.parser.portrait;

import java.util.Arrays;
import java.util.HashSet;

import org.business.tools.calctable.dataprovider.parser.common.testdata.CalcTableDataParser_CollectonTypes_TestDataFactory;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.collection.SupportingCollectionTypes;
import org.testng.annotations.DataProvider;

class CalcTablePortraitDataParser_CollectionTypes_UnitTest
		extends
		AbstractCalcTablePortraitDataParser_UnitTest
{

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
				{
						TEST_DATA_SOURCE__PORTRAIT__DATA_PARSER__FILE_PATH,
						"UT - Data Collection Types",
						new SupportingCollectionTypes[]
						{
								SupportingCollectionTypes.of(
									null,
									Arrays.asList(
										CalcTableDataParser_CollectonTypes_TestDataFactory.ENTRY_1,
										CalcTableDataParser_CollectonTypes_TestDataFactory.ENTRY_2,
										CalcTableDataParser_CollectonTypes_TestDataFactory.ENTRY_1
									),
									new HashSet(
										Arrays.asList(
											CalcTableDataParser_CollectonTypes_TestDataFactory.ENTRY_1,
											CalcTableDataParser_CollectonTypes_TestDataFactory.ENTRY_2
										)
									)
								)
						}
				},
		};
	}

}
