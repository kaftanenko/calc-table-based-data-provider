package org.drakosha.tools.calctable.dataprovider.parser.portrait.testdata;

import static java.util.Arrays.asList;

import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableCellsDimension;
import org.drakosha.tools.calctable.dataprovider.common.type.CalcTableStructureNode;

public interface CalcTablePortraitStructureParser_TestDataFactory {

	CalcTableStructureNode EXPECTED_STRUCTURE_NODES = //
			CalcTableStructureNode.of(
				"Header Level 1",
				CalcTableCellsDimension.of(
					2,
					1,
					1,
					1
				),
				asList(
					CalcTableStructureNode.of(
						"Header Level 1.1",
						CalcTableCellsDimension.of(
							3,
							2,
							1,
							1
						),
						asList()
					),
					CalcTableStructureNode.of(
						"Header Level 1.2",
						CalcTableCellsDimension.of(
							4,
							2,
							1,
							1
						),
						asList(
							CalcTableStructureNode.of(
								"Header Level 1.2.1",
								CalcTableCellsDimension.of(
									5,
									3,
									1,
									1
								),
								asList()
							),
							CalcTableStructureNode.of(
								"Header Level 1.2.2",
								CalcTableCellsDimension.of(
									6,
									3,
									1,
									1
								),
								asList()
							)
						)
					),
					CalcTableStructureNode.of(
						"Header Level 1.3",
						CalcTableCellsDimension.of(
							7,
							2,
							1,
							1
						),
						asList(
							CalcTableStructureNode.of(
								"Header Level 1.3.1",
								CalcTableCellsDimension.of(
									8,
									3,
									1,
									1
								),
								asList()
							),
							CalcTableStructureNode.of(
								"Header Level 1.3.2",
								CalcTableCellsDimension.of(
									9,
									3,
									1,
									1
								),
								asList(
									CalcTableStructureNode.of(
										"Header Level 1.3.2.1",
										CalcTableCellsDimension.of(
											10,
											4,
											1,
											1
										),
										asList()
									),
									CalcTableStructureNode.of(
										"Header Level 1.3.2.2",
										CalcTableCellsDimension.of(
											11,
											4,
											1,
											1
										),
										asList()
									)
								)
							)
						)
					)
				)
			);

}
