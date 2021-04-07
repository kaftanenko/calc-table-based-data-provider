package org.business.tools.calctable.dataprovider.parser;

import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Cell;

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
