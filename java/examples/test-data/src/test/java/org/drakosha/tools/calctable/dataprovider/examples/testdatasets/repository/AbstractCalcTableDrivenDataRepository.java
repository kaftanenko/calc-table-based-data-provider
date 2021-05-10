package org.drakosha.tools.calctable.dataprovider.examples.testdatasets.repository;

import static org.drakosha.tools.calctable.dataprovider.examples.testdatasets.Example_TestDataSets_Const.EXAMPLE_TEST_DATA_SETS__SHEET_DATA_READER__PARAMETER__LOCALE__US;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.drakosha.tools.calctable.dataprovider.reader.CalcTableWorkbookDataReader;
import org.drakosha.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeDataReader;
import org.drakosha.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeStandardDataReader;

public abstract class AbstractCalcTableDrivenDataRepository<DATA_ITEM_TYPE> {

	// ... properties

	private List<DATA_ITEM_TYPE> cachedDataItems;

	// ... configuration methods

	protected abstract String getDataSourceFilePath();

	protected abstract String getDataSourceSheetName();

	protected abstract Class<DATA_ITEM_TYPE> getDataRecordType();

	// ... business methods

	public synchronized List<DATA_ITEM_TYPE> getDataItems() {

		if (cachedDataItems == null) {

			try (final InputStream workbookInputStream = new FileInputStream(
				getDataSourceFilePath()
			))
			{
				final String sheetName = getDataSourceSheetName();
				final Class<DATA_ITEM_TYPE> dataRecordType = getDataRecordType();
				final CalcTableSheetLandscapeDataReader sheetDataReader = new CalcTableSheetLandscapeStandardDataReader(
					EXAMPLE_TEST_DATA_SETS__SHEET_DATA_READER__PARAMETER__LOCALE__US
				);

				final CalcTableWorkbookDataReader workbookReader = new CalcTableWorkbookDataReader();

				cachedDataItems = workbookReader.readData(
					workbookInputStream,
					sheetName,
					dataRecordType,
					sheetDataReader
				);
			} catch (final Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		return cachedDataItems;
	}

}
