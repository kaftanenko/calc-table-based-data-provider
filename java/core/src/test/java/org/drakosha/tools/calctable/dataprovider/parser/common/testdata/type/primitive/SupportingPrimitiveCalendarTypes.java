package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingPrimitiveCalendarTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private Date date;

	private Date dateTime;

	private LocalDate localDate;

	private LocalDateTime localDateTime;

	// ... constructors

	public SupportingPrimitiveCalendarTypes() {

	}

	private SupportingPrimitiveCalendarTypes(
			final Date date,
			final Date dateTime,
			final LocalDate localDate,
			final LocalDateTime localDateTime
	)
	{

		this.date = date;
		this.dateTime = dateTime;
		this.localDate = localDate;
		this.localDateTime = localDateTime;
	}

	public static SupportingPrimitiveCalendarTypes of(
			final Date date,
			final Date dateTime,
			final LocalDate localDate,
			final LocalDateTime localDateTime
	)
	{

		return new SupportingPrimitiveCalendarTypes(
				date,
				dateTime,
				localDate,
				localDateTime
		);
	}

	// ... getter methods

	public Date getDate() {

		return date;
	}

	public void setDate(
			final Date date
	)
	{

		this.date = date;
	}

	public Date getDateTime() {

		return dateTime;
	}

	public void setDateTime(
			final Date dateTime
	)
	{

		this.dateTime = dateTime;
	}

	public LocalDate getLocalDate() {

		return localDate;
	}

	public void setLocalDate(
			final LocalDate localDate
	)
	{

		this.localDate = localDate;
	}

	public LocalDateTime getLocalDateTime() {

		return localDateTime;
	}

	public void setLocalDateTime(
			final LocalDateTime localDateTime
	)
	{

		this.localDateTime = localDateTime;
	}

}
