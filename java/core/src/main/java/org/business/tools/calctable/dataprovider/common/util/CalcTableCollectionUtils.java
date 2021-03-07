package org.business.tools.calctable.dataprovider.common.util;

import java.util.Arrays;
import java.util.List;

public class CalcTableCollectionUtils {

	// ... business methods

	public static <T> List<T> listOf(final T... values) {

		return Arrays.asList(values);
	}

}
