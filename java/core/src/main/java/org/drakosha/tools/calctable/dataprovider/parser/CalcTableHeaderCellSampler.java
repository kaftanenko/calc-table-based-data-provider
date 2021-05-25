package org.drakosha.tools.calctable.dataprovider.parser;

import org.apache.poi.ss.usermodel.Cell;

import java.util.function.Predicate;

/**
 * Sampler component responsible for recognizing the Calc table cells as
 * belonging to the sheet header area.
 */
public interface CalcTableHeaderCellSampler
		extends
		Predicate<Cell>
{

	@Override
	boolean test(Cell cell);
}
