package org.drakosha.tools.calctable.dataprovider.parser.common.testdata.type.primitive;

import org.drakosha.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingPrimitiveNumericTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	// private BigInteger bigInteger;
	//
	// private BigDecimal bigDecimal;

	private byte primitiveByte;

	private short primitiveShort;

	private int primitiveInteger;

	private long primitiveLong;

	private float primitiveFloat;

	private double primitiveDouble;

	private Byte wrapperByte;

	private Short wrapperShort;

	private Integer wrapperInteger;

	private Long wrapperLong;

	private Float wrapperFloat;

	private Double wrapperDouble;

	// ... constructors

	public SupportingPrimitiveNumericTypes() {

	}

	private SupportingPrimitiveNumericTypes(
			// final BigInteger bigInteger,
			// final BigDecimal bigDecimal,
			final byte primitiveByte,
			final short primitiveShort,
			final int primitiveInteger,
			final long primitiveLong,
			final float primitiveFloat,
			final double primitiveDouble,
			final Byte wrapperByte,
			final Short wrapperShort,
			final Integer wrapperInteger,
			final Long wrapperLong,
			final Float wrapperFloat,
			final Double wrapperDouble
	)
	{

		// this.bigInteger = bigInteger;
		// this.bigDecimal = bigDecimal;
		this.primitiveByte = primitiveByte;
		this.primitiveShort = primitiveShort;
		this.primitiveInteger = primitiveInteger;
		this.primitiveLong = primitiveLong;
		this.primitiveFloat = primitiveFloat;
		this.primitiveDouble = primitiveDouble;
		this.wrapperByte = wrapperByte;
		this.wrapperShort = wrapperShort;
		this.wrapperInteger = wrapperInteger;
		this.wrapperLong = wrapperLong;
		this.wrapperFloat = wrapperFloat;
		this.wrapperDouble = wrapperDouble;
	}

	public static SupportingPrimitiveNumericTypes of(
			// final BigInteger bigInteger,
			// final BigDecimal bigDecimal,
			final byte primitiveByte,
			final short primitiveShort,
			final int primitiveInteger,
			final long primitiveLong,
			final float primitiveFloat,
			final double primitiveDouble,
			final Byte wrapperByte,
			final Short wrapperShort,
			final Integer wrapperInteger,
			final Long wrapperLong,
			final Float wrapperFloat,
			final Double wrapperDouble
	)
	{

		return new SupportingPrimitiveNumericTypes(
				// bigInteger,
				// bigDecimal,
				primitiveByte,
				primitiveShort,
				primitiveInteger,
				primitiveLong,
				primitiveFloat,
				primitiveDouble,
				wrapperByte,
				wrapperShort,
				wrapperInteger,
				wrapperLong,
				wrapperFloat,
				wrapperDouble
		);
	}

	// ... getter methods

	// public BigInteger getBigInteger() {
	//
	// return bigInteger;
	// }
	//
	// public void setBigInteger(
	// final BigInteger bigInteger
	// )
	// {
	//
	// this.bigInteger = bigInteger;
	// }
	//
	// public BigDecimal getBigDecimal() {
	//
	// return bigDecimal;
	// }
	//
	// public void setBigDecimal(
	// final BigDecimal bigDecimal
	// )
	// {
	//
	// this.bigDecimal = bigDecimal;
	// }

	public byte getPrimitiveByte() {

		return primitiveByte;
	}

	public void setPrimitiveByte(
			final byte primitiveByte
	)
	{

		this.primitiveByte = primitiveByte;
	}

	public short getPrimitiveShort() {

		return primitiveShort;
	}

	public void setPrimitiveShort(
			final short primitiveShort
	)
	{

		this.primitiveShort = primitiveShort;
	}

	public int getPrimitiveInteger() {

		return primitiveInteger;
	}

	public void setPrimitiveInteger(
			final int primitiveInteger
	)
	{

		this.primitiveInteger = primitiveInteger;
	}

	public long getPrimitiveLong() {

		return primitiveLong;
	}

	public void setPrimitiveLong(
			final long primitiveLong
	)
	{

		this.primitiveLong = primitiveLong;
	}

	public float getPrimitiveFloat() {

		return primitiveFloat;
	}

	public void setPrimitiveFloat(
			final float primitiveFloat
	)
	{

		this.primitiveFloat = primitiveFloat;
	}

	public double getPrimitiveDouble() {

		return primitiveDouble;
	}

	public void setPrimitiveDouble(
			final double primitiveDouble
	)
	{

		this.primitiveDouble = primitiveDouble;
	}

	public Byte getWrapperByte() {

		return wrapperByte;
	}

	public void setWrapperByte(
			final Byte wrapperByte
	)
	{

		this.wrapperByte = wrapperByte;
	}

	public Short getWrapperShort() {

		return wrapperShort;
	}

	public void setWrapperShort(
			final Short wrapperShort
	)
	{

		this.wrapperShort = wrapperShort;
	}

	public Integer getWrapperInteger() {

		return wrapperInteger;
	}

	public void setWrapperInteger(
			final Integer wrapperInteger
	)
	{

		this.wrapperInteger = wrapperInteger;
	}

	public Long getWrapperLong() {

		return wrapperLong;
	}

	public void setWrapperLong(
			final Long wrapperLong
	)
	{

		this.wrapperLong = wrapperLong;
	}

	public Float getWrapperFloat() {

		return wrapperFloat;
	}

	public void setWrapperFloat(
			final Float wrapperFloat
	)
	{

		this.wrapperFloat = wrapperFloat;
	}

	public Double getWrapperDouble() {

		return wrapperDouble;
	}

	public void setWrapperDouble(
			final Double wrapperDouble
	)
	{

		this.wrapperDouble = wrapperDouble;
	}

}
