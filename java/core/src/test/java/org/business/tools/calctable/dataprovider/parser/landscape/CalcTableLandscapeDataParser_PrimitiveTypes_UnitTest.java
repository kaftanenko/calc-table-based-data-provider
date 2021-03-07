package org.business.tools.calctable.dataprovider.parser.landscape;

import org.business.tools.calctable.dataprovider.parser.common.testdata.CalcTableDataParser_PrimitiveTypes_TestDataFactory;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.primitive.CalcTableDataParser_PrimitiveTypes;
import org.testng.annotations.DataProvider;

class CalcTableLandscapeDataParser_PrimitiveTypes_UnitTest
		extends
		AbstractCalcTableLandscapeDataParser_UnitTest
{

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
				{
						TEST_DATA_SOURCE__LANDSCAPE__DATA_PARSER__FILE_PATH,
						"UT - Data Primitive Types",
						new CalcTableDataParser_PrimitiveTypes[]
						{
								CalcTableDataParser_PrimitiveTypes_TestDataFactory.EXPECTED_DATA_NODES,
						}
				},
		};
	}

}
