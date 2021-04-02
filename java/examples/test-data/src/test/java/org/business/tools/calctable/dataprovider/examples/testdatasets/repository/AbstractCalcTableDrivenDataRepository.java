package org.business.tools.calctable.dataprovider.examples.testdatasets.repository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.business.tools.calctable.dataprovider.reader.CalcTableWorkbookDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableSheetLandscapeStandardDataReader;

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
				final CalcTableSheetLandscapeDataReader sheetDataReader = new CalcTableSheetLandscapeStandardDataReader();

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
