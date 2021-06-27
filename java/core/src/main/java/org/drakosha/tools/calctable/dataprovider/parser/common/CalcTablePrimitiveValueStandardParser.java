package org.drakosha.tools.calctable.dataprovider.parser.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.drakosha.tools.calctable.dataprovider.common.error.CalcTableException;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTableBeanUtils;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.drakosha.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.drakosha.tools.calctable.dataprovider.parser.CalcTablePrimitiveValueParser;

/**
 * The standard implementation of the {@link CalcTablePrimitiveValueParser}.
 * <p>
 * Supports the following data types:
 * <ul>
 * <li>Java primitive types and their Wrapper equivalents
 * <li>Enum's
 * <li>BigDecimal
 * <li>BigInteger
 * <li>Date
 * <li>LocalDate
 * <li>LocalDateTime
 * </ul>
 * Very important at this point is also a description of the supported text
 * representations of this data types within the Calt table cells, but it will
 * stay a TODO I owe you next time. Up to this point look please in the source
 * code to explore possible error sources and to find out the correct
 * representation.
 */
public class CalcTablePrimitiveValueStandardParser
		implements
		CalcTablePrimitiveValueParser
{

	// ... properties

	protected final Locale locale;

	private final DateTimeFormatter[] supportedDateTimeFormattersWithLocale;

	// ... constructors

	public CalcTablePrimitiveValueStandardParser(final Locale locale) {

		super();

		this.locale = locale;

		this.supportedDateTimeFormattersWithLocale = Arrays
				.asList(getSupportedDateTimePatterns(locale))
				.stream()
				.map(
						pattern -> DateTimeFormatter.ofPattern(
								pattern,
								locale
						)
				)
				.collect(Collectors.toList())
				.toArray(new DateTimeFormatter[0]);
	}

	// ... configuration methods

	protected String[] getSupportedDateTimePatterns(final Locale locale) {

		return new String[] {
				"M/d/yyyy[ HH:mm[:ss]]",
				"dd.MM.yyyy[ HH:mm[:ss]]",
		};
	}

	// ... business methods

	private static final List<Class<? extends Object>> SUPPORTED_DATA_TYPES = Arrays.asList(

			String.class,

			boolean.class,
			Boolean.class,

			byte.class,
			Byte.class,

			char.class,
			Character.class,

			short.class,
			Short.class,

			int.class,
			Integer.class,

			long.class,
			Long.class,

			float.class,
			Float.class,

			double.class,
			Double.class,

			void.class,
			Void.class,

			BigDecimal.class,
			BigInteger.class,

			Date.class,
			LocalDate.class,
			LocalDateTime.class
	);

	@Override
	public boolean isApplicableTo(
			final Class<?> targetDataType
	)
	{

		return targetDataType.isEnum()
				|| targetDataType.isPrimitive()
				|| SUPPORTED_DATA_TYPES.contains(
				targetDataType
		);
	}

	@Override
	public <DATA_TYPE> Optional<DATA_TYPE> parseValue(
			final Cell cell,
			final Class<DATA_TYPE> targetDataType,
			final List<RuntimeException> messageContainer
	)
	{

		final String cellValueAsString = CalcTablePoiDataUtils.getCellValueAsString(
				cell
		);

		if (StringUtils.isBlank(cellValueAsString)) {
			return Optional.empty();
		}

		try {

			Object result;

			if (targetDataType.isEnum()) {

				final Class targetDataAsEnumType = targetDataType;
				result = Enum.valueOf(
						targetDataAsEnumType,
						cellValueAsString.toUpperCase()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					String.class
			)) {

				result = cellValueAsString;
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					boolean.class,
					Boolean.class
			)) {

				result = parseBooleanValue(cellValueAsString);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					char.class,
					Character.class
			)) {

				result = Character.valueOf(
						cellValueAsString.charAt(
								0
						)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					byte.class,
					Byte.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.byteValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					short.class,
					Short.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.shortValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					int.class,
					Integer.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.intValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					long.class,
					Long.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.longValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					float.class,
					Float.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.floatValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					double.class,
					Double.class
			)) {

				result = parseNumberValue(cellValueAsString)
						.doubleValue();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					BigDecimal.class
			)) {

				result = BigDecimal.valueOf(
						parseNumberValue(cellValueAsString)
								.doubleValue()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					BigInteger.class
			)) {

				result = BigInteger.valueOf(
						parseNumberValue(
								cellValueAsString
						)
								.longValue()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					Date.class
			)) {

				final LocalDateTime localDateTime = parseLocalDateTimeValue(
						cell,
						true
				);
				result = Date.from(
						localDateTime
								.atZone(
										ZoneId.systemDefault()
								)
								.toInstant()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					LocalDate.class
			)) {

				result = parseLocalDateTimeValue(
						cell,
						false
				)
						.toLocalDate();
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
					targetDataType,
					LocalDateTime.class
			)) {

				result = parseLocalDateTimeValue(
						cell,
						true
				);
			} else {
				throw CalcTableErrorHelper.handleUnsupportedValueType(
						targetDataType
				);
			}

			return (Optional<DATA_TYPE>) Optional.of(
					result
			);
		} catch (final Exception ex) {

			final RuntimeException warningException = new CalcTableException(
					String.format(
							"No primitive value could be parsed within the cell [sheet:'%s',rowNum:'%s',columnNum:'%s'] for the type %s from the original cell text '%s'.",
							cell
									.getSheet()
									.getSheetName(),
							cell.getRowIndex(),
							cell.getColumnIndex(),
							targetDataType,
							cellValueAsString
					)
			);
			messageContainer.add(
					warningException
			);
			return Optional.empty();
		}
	}

	// ... helper methods

	/**
	 * Parses a boolean value from its text representation.
	 * <p>
	 * Could be overridden to accept also the locale specific natural words like
	 * "yes/no","ja/nein", "sí/no", "oui/non" etc. or even numeric values.
	 *
	 * @param cellValueAsString
	 * @return
	 */
	protected boolean parseBooleanValue(final String cellValueAsString) {

		return Boolean.valueOf(
				cellValueAsString
		);
	}

	private LocalDateTime parseLocalDateTimeValue(
			final Cell cell,
			final boolean includingTimePart
	)
	{

		final CellType cellType = cell.getCellType();
		switch (cellType) {
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(
						cell
				)) {
					try {
						return cell
								.getDateCellValue()
								.toInstant()
								.atZone(
										ZoneId.systemDefault()
								)
								.toLocalDateTime();
					} catch (final NullPointerException ex) {
						return null;
					}
				}
				return null;
			case STRING:
				final String dateValueAsString = cell.getStringCellValue();
				for (final DateTimeFormatter formatter : supportedDateTimeFormattersWithLocale) {
					try {
						if (includingTimePart) {
							return LocalDateTime.parse(
									dateValueAsString,
									formatter
							);
						} else {
							return LocalDateTime.of(
									LocalDate.parse(
											dateValueAsString,
											formatter
									),
									LocalTime.of(
											0,
											0
									)
							);
						}
					} catch (final Exception ex) {
						// ... couldn't parse with the given formatter, try the next one.
					}
				}
				try {
					// ... last chance, try the default one parser.
					if (includingTimePart) {
						return LocalDateTime.parse(dateValueAsString);
					} else {
						return LocalDateTime.of(
								LocalDate.parse(dateValueAsString),
								LocalTime.of(
										0,
										0
								)
						);
					}
				} catch (final Exception ex) {
					// ... no one try succeeded, nothing to do.
					throw new CalcTableException(
							String.format(
									"No date formatter fits the given value '%s'. The date formatters tried: %s + the default one.",
									dateValueAsString,
									supportedDateTimeFormattersWithLocale
							)
					);
				}
			default:
				throw CalcTableErrorHelper.handleUnsupportedValue(
						cellType
				);
		}
	}

	private Number parseNumberValue(final String numberValueAsString)
			throws ParseException
	{

		final NumberFormat nf = NumberFormat.getInstance(locale);
		return nf.parse(numberValueAsString);
	}

}
