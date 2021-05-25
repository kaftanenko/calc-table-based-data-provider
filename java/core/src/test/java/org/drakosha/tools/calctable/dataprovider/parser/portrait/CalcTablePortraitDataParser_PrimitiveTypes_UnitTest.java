package org.drakosha.tools.calctable.dataprovider.parser.portrait;

import org.testng.annotations.DataProvider;

import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.CalcTableDataParser_PrimitiveTypes_TestDataFactory;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.CalcTableDataParser_PrimitiveTypes;

class CalcTablePortraitDataParser_PrimitiveTypes_UnitTest
		extends
		AbstractCalcTablePortraitDataParser_UnitTest
{

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
						{
								TEST_DATA_SOURCE__PORTRAIT__DATA_PARSER__FILE_PATH,
								"UT - Data Primitive Types",
								new CalcTableDataParser_PrimitiveTypes[]
										{
												CalcTableDataParser_PrimitiveTypes_TestDataFactory.EXPECTED_DATA_NODES,
										}
						},
		};
	}

}
