package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.selftest;

import java.util.List;

import static org.drakosha.tools.calctable.dataprovider.common.util.CalcTableCollectionUtils.listOf;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.EArithmeticOperation.ADD;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.EArithmeticOperation.DIVIDE;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.EArithmeticOperation.MULTIPLY;
import static org.drakosha.tools.calctable.dataprovider.examples.testscenarios.EArithmeticOperation.SUBTRACT;

import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.Example_TestScenarios_SimpleCalculator_Record;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.ExpectedErrorMessage;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.ExpectedOutput;
import org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model.InputParameters;

public interface _TestDataFactory {

	// ... constants

	List<Example_TestScenarios_SimpleCalculator_Record> EXPECTED__EXAMPLE_TEST_SCENARIOS__DATA = listOf(
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							1,
							ADD,
							2
					),
					new ExpectedOutput(
							3
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							2,
							ADD,
							1
					),
					new ExpectedOutput(
							3
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							2,
							ADD,
							-1
					),
					new ExpectedOutput(
							1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-2,
							ADD,
							1
					),
					new ExpectedOutput(
							-1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-2,
							ADD,
							-1
					),
					new ExpectedOutput(
							-3
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							2,
							ADD,
							-2
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							2,
							ADD,
							0
					),
					new ExpectedOutput(
							2
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							ADD,
							2
					),
					new ExpectedOutput(
							2
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							ADD,
							0
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							4,
							SUBTRACT,
							3
					),
					new ExpectedOutput(
							1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							3,
							SUBTRACT,
							4
					),
					new ExpectedOutput(
							-1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							4,
							SUBTRACT,
							-3
					),
					new ExpectedOutput(
							7
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-4,
							SUBTRACT,
							3
					),
					new ExpectedOutput(
							-7
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-4,
							SUBTRACT,
							-3
					),
					new ExpectedOutput(
							-1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							4,
							SUBTRACT,
							4
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							4,
							SUBTRACT,
							0
					),
					new ExpectedOutput(
							4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							SUBTRACT,
							4
					),
					new ExpectedOutput(
							-4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							SUBTRACT,
							0
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							5,
							MULTIPLY,
							6
					),
					new ExpectedOutput(
							30
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							6,
							MULTIPLY,
							5
					),
					new ExpectedOutput(
							30
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-5,
							MULTIPLY,
							6
					),
					new ExpectedOutput(
							-30
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							5,
							MULTIPLY,
							-6
					),
					new ExpectedOutput(
							-30
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-5,
							MULTIPLY,
							-6
					),
					new ExpectedOutput(
							30
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							3,
							MULTIPLY,
							0
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							MULTIPLY,
							3
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							MULTIPLY,
							0
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							8,
							DIVIDE,
							2
					),
					new ExpectedOutput(
							4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-8,
							DIVIDE,
							2
					),
					new ExpectedOutput(
							-4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							8,
							DIVIDE,
							-2
					),
					new ExpectedOutput(
							-4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							-8,
							DIVIDE,
							-2
					),
					new ExpectedOutput(
							4
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							8,
							DIVIDE,
							8
					),
					new ExpectedOutput(
							1
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							0,
							DIVIDE,
							8
					),
					new ExpectedOutput(
							0
					),
					new ExpectedErrorMessage(
							null
					)
			),
			new Example_TestScenarios_SimpleCalculator_Record(
					new InputParameters(
							8,
							DIVIDE,
							0
					),
					new ExpectedOutput(
							null
					),
					new ExpectedErrorMessage(
							"/ by zero"
					)
			)
	);
}
