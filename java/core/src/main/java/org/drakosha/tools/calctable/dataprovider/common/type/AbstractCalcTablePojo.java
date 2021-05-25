package org.drakosha.tools.calctable.dataprovider.common.type;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class AbstractCalcTablePojo {

	// ... helper methods

	@Override
	public int hashCode() {

		return HashCodeBuilder.reflectionHashCode(
				this,
				true
		);
	}

	@Override
	public boolean equals(
			final Object obj
	)
	{

		return EqualsBuilder.reflectionEquals(
				this,
				obj,
				true
		);
	}

	@Override
	public String toString() {

		return ToStringBuilder.reflectionToString(
				this,
				ToStringStyle.MULTI_LINE_STYLE,
				true
		);
	}

}
