package org.drakosha.tools.calctable.dataprovider.common.type;

public class CalcTableCellsDimension
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private final int row;

	private final int column;

	private final int rowSpan;

	private final int columnSpan;

	// ... constructors

	private CalcTableCellsDimension(
			final int row,
			final int column,
			final int rowSpan,
			final int columnSpan
	)
	{

		this.row = row;
		this.column = column;
		this.rowSpan = rowSpan;
		this.columnSpan = columnSpan;
	}

	public static CalcTableCellsDimension of(
			final int row,
			final int column,
			final int rowSpan,
			final int columnSpan
	)
	{

		return new CalcTableCellsDimension(
				row,
				column,
				rowSpan,
				columnSpan
		);
	}

	// ... setter/getter methods

	public int getRow() {

		return this.row;
	}

	public int getColumn() {

		return this.column;
	}

	public int getRowSpan() {

		return this.rowSpan;
	}

	public int getColumnSpan() {

		return this.columnSpan;
	}

	// ... helper methods

	public boolean contains(
			final int row,
			final int column
	)
	{

		return getRow() <= row
				&& getColumn() <= column
				&& (row - getRow()) < getRowSpan()
				&& (column - getColumn()) < getColumnSpan();
	}

}
