package org.business.tools.calctable.dataprovider.examples.dictionaries.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.examples.dictionaries.model.AbstractDomainObject;
import org.business.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;
import org.business.tools.calctable.dataprovider.examples.dictionaries.model.DictionaryEntry;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeStandardDataReader;

public class Example_Dictionaries_Parser {

	// ... constants

	private static final String STANDARD_SHEET_NAME__OVERVIEW = "Overview";

	// ... dependencies

	final CalcTableSheetLandscapeDataReader reader = new CalcTableSheetLandscapeStandardDataReader();

	// ... business methods

	public List<Dictionary> parseDictionaryFile(final String filePath)
			throws Exception
	{

		final List<RuntimeException> messageContainer = new ArrayList<>();

		try (final InputStream is = new FileInputStream(
			filePath
		);
				final Workbook workbook = new XSSFWorkbook(
					is
				))
		{
			final Sheet overviewSheet = CalcTablePoiNavigationUtils.getSheet(
				workbook,
				STANDARD_SHEET_NAME__OVERVIEW
			);

			final List<OverviewSheetEntry> overviewSheetEntries = reader.readData(
				overviewSheet,
				OverviewSheetEntry.class,
				messageContainer
			);

			final List<Dictionary> dictionaries = overviewSheetEntries.stream().map(dictionaryHeadInfo -> {

				final Sheet dictionarySheet = CalcTablePoiNavigationUtils.getSheet(
					workbook,
					dictionaryHeadInfo.getName()
				);

				final List<DictionaryEntry> dictionaryEntries = reader.readData(
					dictionarySheet,
					DictionaryEntry.class,
					messageContainer
				);

				return new Dictionary(
					dictionaryHeadInfo.getName(),
					dictionaryHeadInfo.getDescription(),
					dictionaryEntries
				);
			}).collect(Collectors.toList());

			if (messageContainer.isEmpty()) {

				return dictionaries;
			} else {
				throw new RuntimeException(messageContainer.toString());
			}
		}
	}

	// ... helper classes

	public static class OverviewSheetEntry
			extends
			AbstractDomainObject
	{

		// ... properties

		private String name;

		private String description;

		// ... getter/setter methods

		public String getName() {

			return name;
		}

		public void setName(final String name) {

			this.name = name;
		}

		public String getDescription() {

			return description;
		}

		public void setDescription(final String description) {

			this.description = description;
		}
	}
}
