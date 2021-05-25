package org.drakosha.tools.calctable.dataprovider.parser.common.testdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.lang.model.element.ElementKind;

import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.CalcTableDataParser_PrimitiveTypes;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.ECustomEnumeration;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.SupportingPrimitiveBooleanTypes;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.SupportingPrimitiveCalendarTypes;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.SupportingPrimitiveEnumerationTypes;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.SupportingPrimitiveNumericTypes;
import org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive.SupportingPrimitiveTextTypes;

public interface CalcTableDataParser_PrimitiveTypes_TestDataFactory {

	CalcTableDataParser_PrimitiveTypes EXPECTED_DATA_NODES = //
			CalcTableDataParser_PrimitiveTypes.of(
					SupportingPrimitiveBooleanTypes.of(
							true,
							Boolean.FALSE
					),
					SupportingPrimitiveCalendarTypes.of(
							new Date(
									119,
									11,
									31
							),
							new Date(
									119,
									11,
									31,
									12,
									30,
									56
							),
							LocalDate.of(
									2019,
									12,
									31
							),
							LocalDateTime.of(
									2019,
									12,
									31,
									12,
									30,
									56
							)
					),
					SupportingPrimitiveEnumerationTypes.of(
							ElementKind.CLASS,
							ECustomEnumeration.ENUM_CONSTANT
					),
					SupportingPrimitiveNumericTypes.of(
							// BigInteger.valueOf(
							// 1000000
							// ),
							// BigDecimal.valueOf(
							// 2000000.123
							// ),
							(byte) 1,
							(short) 23,
							345,
							4567,
							5.6789f,
							6.78901d,
							Byte.valueOf(
									"1"
							),
							Short.valueOf(
									"23"
							),
							Integer.valueOf(
									"345"
							),
							Long.valueOf(
									"4567"
							),
							Float.valueOf(
									"5.6789"
							),
							Double.valueOf(
									"6.78901"
							)
					),
					SupportingPrimitiveTextTypes.of(
							"ABC"
					)
			);

}
