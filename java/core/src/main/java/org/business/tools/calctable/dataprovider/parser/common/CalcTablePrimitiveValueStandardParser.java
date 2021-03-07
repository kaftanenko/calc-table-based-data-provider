package org.business.tools.calctable.dataprovider.parser.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import org.business.tools.calctable.dataprovider.common.error.CalcTableException;
import org.business.tools.calctable.dataprovider.common.util.CalcTableBeanUtils;
import org.business.tools.calctable.dataprovider.common.util.CalcTableErrorHelper;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiDataUtils;
import org.business.tools.calctable.dataprovider.parser.CalcTablePrimitiveValueParser;

public class CalcTablePrimitiveValueStandardParser
		implements
		CalcTablePrimitiveValueParser
{

	// ... constants

	final DateTimeFormatter[] LOCAL_DATE_TIME_FORMATTERS = new DateTimeFormatter[] {
			DateTimeFormatter.ofPattern(
				"M/d/yyyy"
			),
			DateTimeFormatter.ofPattern(
				"dd.MM.yyyy"
			),
			DateTimeFormatter.ofPattern(
				"M/d/yyyy [HH:mm[:ss]]"
			),
			DateTimeFormatter.ofPattern(
				"dd.MM.yyyy [HH:mm[:ss]]"
			),
	};

	public static final CalcTablePrimitiveValueParser INSTANCE = new CalcTablePrimitiveValueStandardParser();

	// ... constructors

	protected CalcTablePrimitiveValueStandardParser() {

		super();
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
			final Class<?> propertyType
	)
	{

		return propertyType.isEnum()
				|| propertyType.isPrimitive()
				|| SUPPORTED_DATA_TYPES.contains(
					propertyType
				);
	}

	@Override
	public <DATA_TYPE> Optional<DATA_TYPE> parseValue(
			final Cell cell,
			final Class<DATA_TYPE> propertyType,
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

			if (propertyType.isEnum()) {

				final Class propertyAsEnumType = propertyType;
				result = Enum.valueOf(
					propertyAsEnumType,
					cellValueAsString.toUpperCase()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				String.class
			))
			{

				result = cellValueAsString;
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				boolean.class,
				Boolean.class
			))
			{

				result = Arrays.asList(
					"1",
					"yes"
				).contains(
					cellValueAsString
				)
						|| Boolean.valueOf(
							cellValueAsString
						);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				char.class,
				Character.class
			))
			{

				result = Character.valueOf(
					cellValueAsString.charAt(
						0
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				byte.class,
				Byte.class
			))
			{

				result = Byte.valueOf(
					normalizeStringRepresentationOfIntegerDatatype(
						cellValueAsString
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				short.class,
				Short.class
			))
			{

				result = Short.valueOf(
					normalizeStringRepresentationOfIntegerDatatype(
						cellValueAsString
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				int.class,
				Integer.class
			))
			{

				result = Integer.valueOf(
					normalizeStringRepresentationOfIntegerDatatype(
						cellValueAsString
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				long.class,
				Long.class
			))
			{

				result = Long.valueOf(
					normalizeStringRepresentationOfIntegerDatatype(
						cellValueAsString
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				float.class,
				Float.class
			))
			{

				result = Float.valueOf(
					cellValueAsString
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				double.class,
				Double.class
			))
			{

				result = Double.valueOf(
					cellValueAsString
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				BigDecimal.class
			))
			{

				result = BigDecimal.valueOf(
					Double.valueOf(
						cellValueAsString
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				BigInteger.class
			))
			{

				result = BigInteger.valueOf(
					Long.valueOf(
						normalizeStringRepresentationOfIntegerDatatype(
							cellValueAsString
						)
					)
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				Date.class
			))
			{

				final LocalDateTime localDateTime = parseLocalDateTime(
					cell
				);
				result = Date.from(
					localDateTime.atZone(
						ZoneId.systemDefault()
					).toInstant()
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				LocalDate.class
			))
			{

				result = parseLocalDate(
					cell
				);
			} else if (CalcTableBeanUtils.isTypeCompatibleWithAnyOf(
				propertyType,
				LocalDateTime.class
			))
			{

				result = parseLocalDateTime(
					cell
				);
			} else {
				throw CalcTableErrorHelper.handleUnsupportedValueType(
					propertyType
				);
			}

			return (Optional<DATA_TYPE>) Optional.of(
				result
			);
		} catch (final Exception ex) {

			final RuntimeException warningException = new CalcTableException(
				String.format(
					"No primitive value could be parsed within the cell [sheet:'%s',rowNum:'%s',columnNum:'%s'] for the type %s from the original cell text '%s'.",
					cell.getSheet().getSheetName(),
					cell.getRowIndex(),
					cell.getColumnIndex(),
					propertyType,
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

	private String normalizeStringRepresentationOfIntegerDatatype(
			final String value
	)
	{

		return StringUtils.substringBefore(
			value,
			"."
		).replaceAll(
			",",
			""
		);
	}

	private LocalDate parseLocalDate(
			final Cell cell
	)
	{

		final CellType cellType = cell.getCellTypeEnum();
		switch (cellType) {
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(
					cell
				))
				{
					try {
						return cell.getDateCellValue().toInstant().atZone(
							ZoneId.systemDefault()
						).toLocalDate();
					} catch (final NullPointerException ex) {
						return null;
					}
				}
				return null;
			case STRING:
				final String dateValueAsString = cell.getStringCellValue();
				return parseLocalDate(
					dateValueAsString,
					LOCAL_DATE_TIME_FORMATTERS
				);
			default:
				throw CalcTableErrorHelper.handleUnsupportedValue(
					cellType
				);
		}
	}

	private LocalDateTime parseLocalDateTime(
			final Cell cell
	)
	{

		final CellType cellType = cell.getCellTypeEnum();
		switch (cellType) {
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(
					cell
				))
				{
					try {
						return cell.getDateCellValue().toInstant().atZone(
							ZoneId.systemDefault()
						).toLocalDateTime();
					} catch (final NullPointerException ex) {
						return null;
					}
				}
				return null;
			case STRING:
				final String dateValueAsString = cell.getStringCellValue();
				return parseLocalDateTime(
					dateValueAsString,
					LOCAL_DATE_TIME_FORMATTERS
				);
			default:
				throw CalcTableErrorHelper.handleUnsupportedValue(
					cellType
				);
		}
	}

	private LocalDate parseLocalDate(
			final String dateValueAsString,
			final DateTimeFormatter... formatters
	)
	{

		return (LocalDate) parseGenericDateTime(
			dateValueAsString,
			(
					value,
					formatter
			) -> LocalDate.parse(
				value,
				((DateTimeFormatter) formatter)
			),
			formatters
		);
	}

	private LocalDateTime parseLocalDateTime(
			final String dateValueAsString,
			final DateTimeFormatter... formatters
	)
	{

		return (LocalDateTime) parseGenericDateTime(
			dateValueAsString,
			(
					value,
					formatter
			) -> LocalDateTime.parse(
				value,
				((DateTimeFormatter) formatter)
			),
			formatters
		);
	}

	private Object parseGenericDateTime(
			final String dateValueAsString,
			final BiFunction<String, Object, Object> parser,
			final Object... formatters
	)
	{

		return Arrays.asList(
			formatters
		).stream().map(
			formater ->
			{
				try {
					final Object result = parser.apply(
						dateValueAsString,
						formater
					);
					return Optional.of(
						result
					);
				} catch (final Exception ex) {
					return Optional.empty();
				}
			}
		).filter(
			Optional::isPresent
		).findFirst().orElseThrow(
			() -> new CalcTableException(
				String.format(
					"No date formatter fits the given value '%s'. The date formatter tried: %s",
					dateValueAsString,
					formatters
				)
			)
		).get();
	}

}
