package org.business.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import org.business.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class CalcTableDataParser_PrimitiveTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private SupportingPrimitiveBooleanTypes bool;

	private SupportingPrimitiveCalendarTypes calendar;

	private SupportingPrimitiveEnumerationTypes enumeration;

	private SupportingPrimitiveNumericTypes numeric;

	private SupportingPrimitiveTextTypes text;

	// ... constructors

	public CalcTableDataParser_PrimitiveTypes() {

	}

	private CalcTableDataParser_PrimitiveTypes(
			final SupportingPrimitiveBooleanTypes bool,
			final SupportingPrimitiveCalendarTypes calendar,
			final SupportingPrimitiveEnumerationTypes enumeration,
			final SupportingPrimitiveNumericTypes numeric,
			final SupportingPrimitiveTextTypes text
	)
	{

		this.bool = bool;
		this.calendar = calendar;
		this.enumeration = enumeration;
		this.numeric = numeric;
		this.text = text;
	}

	public static CalcTableDataParser_PrimitiveTypes of(
			final SupportingPrimitiveBooleanTypes bool,
			final SupportingPrimitiveCalendarTypes calendar,
			final SupportingPrimitiveEnumerationTypes enumeration,
			final SupportingPrimitiveNumericTypes numeric,
			final SupportingPrimitiveTextTypes text
	)
	{

		return new CalcTableDataParser_PrimitiveTypes(
			bool,
			calendar,
			enumeration,
			numeric,
			text
		);
	}

	// ... getter methods

	public SupportingPrimitiveBooleanTypes getBool() {

		return bool;
	}

	public void setBool(
			final SupportingPrimitiveBooleanTypes bool
	)
	{

		this.bool = bool;
	}

	public SupportingPrimitiveCalendarTypes getCalendar() {

		return calendar;
	}

	public void setCalendar(
			final SupportingPrimitiveCalendarTypes calendar
	)
	{

		this.calendar = calendar;
	}

	public SupportingPrimitiveEnumerationTypes getEnumeration() {

		return enumeration;
	}

	public void setEnumeration(
			final SupportingPrimitiveEnumerationTypes enumeration
	)
	{

		this.enumeration = enumeration;
	}

	public SupportingPrimitiveNumericTypes getNumeric() {

		return numeric;
	}

	public void setNumeric(
			final SupportingPrimitiveNumericTypes numeric
	)
	{

		this.numeric = numeric;
	}

	public SupportingPrimitiveTextTypes getText() {

		return text;
	}

	public void setText(
			final SupportingPrimitiveTextTypes text
	)
	{

		this.text = text;
	}

}
