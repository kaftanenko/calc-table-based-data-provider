package org.drakosha.tools.calctable.dataprovider.common.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public class CalcTableBeanUtils {

	// ... business methods

	public static Class<?> getPropertyType(
			final Object dataBean,
			final String propertyName
	)
	{

		try {
			return PropertyUtils.getPropertyType(
				dataBean,
				propertyName
			);
		} catch (final Exception ex) {
			throw CalcTableErrorHelper.handleFatalException(
				ex
			);
		}
	}

	public static boolean isCollectionType(
			final Class<?> propertyType
	)
	{

		return propertyType.isArray()
				|| isTypeCompatibleWith(
					propertyType,
					Collection.class
				);
	}

	public static Class<?> getPropertyItemType(
			final Object dataBean,
			final String propertyName
	)
			throws Exception
	{

		final Class<?> propertyType = getPropertyType(
			dataBean,
			propertyName
		);
		if (propertyType.isArray()) {

			return propertyType.getComponentType();
		} else if (isTypeCompatibleWith(
			propertyType,
			Collection.class
		))
		{
			final Field field = dataBean.getClass().getDeclaredField(
				propertyName
			);
			field.setAccessible(
				true
			);

			final Type genericFieldType = field.getGenericType();

			if (genericFieldType instanceof ParameterizedType) {
				final ParameterizedType aType = (ParameterizedType) genericFieldType;
				final Type[] fieldArgTypes = aType.getActualTypeArguments();
				switch (fieldArgTypes.length) {
					case 0:
						throw new RuntimeException(
							"No generics for item type specification found: "
									+ buildErrorContext(
										dataBean,
										propertyName
									)
						);
					case 1:
						return (Class) fieldArgTypes[0];
					default:
						throw new RuntimeException(
							"Multiply generics for item type specification found: "
									+ buildErrorContext(
										dataBean,
										propertyName
									)
						);
				}
			} else {
				throw new RuntimeException(
					"No generics for item type specification found: "
							+ buildErrorContext(
								dataBean,
								propertyName
							)
				);
			}
		} else {
			throw CalcTableErrorHelper.handleUnsupportedValueType(
				propertyType
			);
		}
	}

	public static Object getPropertyValue(
			final Object dataBean,
			final String propertyName
	)
	{

		try {
			return PropertyUtils.getProperty(
				dataBean,
				propertyName
			);
		} catch (final Exception ex) {
			throw CalcTableErrorHelper.handleFatalException(
				ex
			);
		}
	}

	public static boolean hasProperty(
			final Object dataBean,
			final String propertyName
	)
	{

		try {
			PropertyUtils.getProperty(
				dataBean,
				propertyName
			);
			return true;
		} catch (final Exception ex) {
			return false;
		}
	}

	public static void setPropertyValue(
			final Object dataBean,
			final String propertyName,
			final Object propertyValue
	)
	{

		try {
			PropertyUtils.setProperty(
				dataBean,
				propertyName,
				propertyValue
			);
		} catch (final Exception ex) {
			throw CalcTableErrorHelper.handleFatalException(
				ex
			);
		}
	}

	public static <DATA_TYPE> DATA_TYPE instantiate(
			final Class<DATA_TYPE> dataBeanType
	)
			throws Exception
	{

		return dataBeanType.newInstance();
	}

	public static boolean isTypeCompatibleWith(
			final Class<?> propertyType,
			final Class<?> expectedSameOrSuperClass
	)
	{

		return expectedSameOrSuperClass.isAssignableFrom(
			propertyType
		);
	}

	public static boolean isTypeCompatibleWithAnyOf(
			final Class<?> propertyType,
			final Class<?>... expectedSameOrSuperClasses
	)
	{

		return Arrays.stream(
			expectedSameOrSuperClasses
		).filter(
			expectedSameOrSuperClass -> isTypeCompatibleWith(
				propertyType,
				expectedSameOrSuperClass
			)
		).findFirst().isPresent();
	}

	// ... helper methods

	private static String buildErrorContext(
			final Object dataBean,
			final String propertyName
	)
	{

		return String.format(
			"[bean: '%s', property name: %s]",
			dataBean.getClass(),
			propertyName
		);
	}

}
