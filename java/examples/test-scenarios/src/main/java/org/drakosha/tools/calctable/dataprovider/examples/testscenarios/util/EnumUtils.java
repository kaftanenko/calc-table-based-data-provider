package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.util;

import java.lang.reflect.Field;

public class EnumUtils {

	// ... business methods

	public static void overrideValueOfBehaviour(
			final Enum<?> enumValue,
			final String alternativeValueToBeUsedByValueOf
	)
	{

		try {

			final Field fieldName = enumValue
					.getClass()
					.getSuperclass()
					.getDeclaredField("name");
			fieldName.setAccessible(true);
			fieldName.set(
					enumValue,
					alternativeValueToBeUsedByValueOf
			);
			fieldName.setAccessible(false);
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
