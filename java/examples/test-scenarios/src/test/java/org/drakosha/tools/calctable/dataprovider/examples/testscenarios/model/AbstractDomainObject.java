package org.drakosha.tools.calctable.dataprovider.examples.testscenarios.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractDomainObject {

	// ... dependencies

	private final static ObjectMapper JSON_OBJECT_WRAPPER = new ObjectMapper();

	static {
		JSON_OBJECT_WRAPPER.registerModule(new JSR310Module());
		JSON_OBJECT_WRAPPER.configure(
				SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
				false
		);
	}

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

		try {
			return JSON_OBJECT_WRAPPER
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString(this);
		} catch (final JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}

}
