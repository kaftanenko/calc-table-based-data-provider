package org.business.tools.calctable.dataprovider.parser.common.testdata.type.collection;

import java.util.List;
import java.util.Set;

import org.business.tools.calctable.dataprovider.common.type.AbstractCalcTablePojo;

public class SupportingCollectionTypes
		extends
		AbstractCalcTablePojo
{

	// ... properties

	private SupportingCollectionItem[] array;

	private List<SupportingCollectionItem> list;

	private Set<SupportingCollectionItem> set;

	// ... constructors

	public SupportingCollectionTypes() {

	}

	private SupportingCollectionTypes(
			final SupportingCollectionItem[] array,
			final List<SupportingCollectionItem> list,
			final Set<SupportingCollectionItem> set
	)
	{

		this.array = array;
		this.list = list;
		this.set = set;
	}

	public static SupportingCollectionTypes of(
			final SupportingCollectionItem[] array,
			final List<SupportingCollectionItem> list,
			final Set<SupportingCollectionItem> set
	)
	{

		return new SupportingCollectionTypes(
			array,
			list,
			set
		);
	}

	// ... getter/setter methods

	public SupportingCollectionItem[] getArray() {

		return array;
	}

	public void setArray(
			final SupportingCollectionItem[] array
	)
	{

		this.array = array;
	}

	public List<SupportingCollectionItem> getList() {

		return list;
	}

	public void setList(
			final List<SupportingCollectionItem> list
	)
	{

		this.list = list;
	}

	public Set<SupportingCollectionItem> getSet() {

		return set;
	}

	public void setSet(
			final Set<SupportingCollectionItem> set
	)
	{

		this.set = set;
	}

}
