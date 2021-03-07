package org.business.tools.calctable.dataprovider.parser.portrait;

import org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested.BeanNestedLevel_1;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested.BeanNestedLevel_1_2;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested.BeanNestedLevel_1_3;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested.BeanNestedLevel_1_3_2;
import org.business.tools.calctable.dataprovider.parser.common.testdata.type.beansnested.BeanNestedType;
import org.testng.annotations.DataProvider;

class CalcTablePortraitDataParser_BeansNestedTypes_UnitTest
		extends
		AbstractCalcTablePortraitDataParser_UnitTest
{

	@DataProvider
	private Object[][] dataProvider() {

		return new Object[][] {
				new Object[]
				{
						TEST_DATA_SOURCE__PORTRAIT__DATA_PARSER__FILE_PATH,
						"UT - Data Beans (nested)",
						new BeanNestedType[]
						{
								new BeanNestedType(
									new BeanNestedLevel_1(
										"1 # 1.1",
										new BeanNestedLevel_1_2(
											"1 # 1.2.1",
											"1 # 1.2.2"
										),
										new BeanNestedLevel_1_3(
											"1 # 1.3.1",
											new BeanNestedLevel_1_3_2(
												"1 # 1.3.2.1",
												"1 # 1.3.2.2"
											)
										)
									)
								),
								new BeanNestedType(
									new BeanNestedLevel_1(
										"2 # 1.1",
										new BeanNestedLevel_1_2(
											"2 # 1.2.1",
											"2 # 1.2.2"
										),
										new BeanNestedLevel_1_3(
											"2 # 1.3.1",
											new BeanNestedLevel_1_3_2(
												"2 # 1.3.2.1",
												"2 # 1.3.2.2"
											)
										)
									)
								),
								new BeanNestedType(
									new BeanNestedLevel_1(
										"3 # 1.1",
										new BeanNestedLevel_1_2(
											"3 # 1.2.1",
											"3 # 1.2.2"
										),
										new BeanNestedLevel_1_3(
											"3 # 1.3.1",
											new BeanNestedLevel_1_3_2(
												"3 # 1.3.2.1",
												"3 # 1.3.2.2"
											)
										)
									)
								),
						}
				},
		};
	}

}
