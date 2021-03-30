package org.business.tools.calctable.dataprovider.examples.testdatasets.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.business.tools.calctable.dataprovider.common.util.CalcTablePoiNavigationUtils;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableLandscapeDataReader;
import org.business.tools.calctable.dataprovider.reader.landscape.CalcTableLandscapeStandardDataReader;

public abstract class AbstractCalcTableDrivenDataRepository<DATA_ITEM_TYPE> {

	// ... properties

	private List<DATA_ITEM_TYPE> cachedDataItems;

	// ... configuration methods

	protected abstract String getDataSourceFilePath();

	protected abstract String getDataSourceSheetName();

	protected abstract Class<DATA_ITEM_TYPE> getDataItemType();

	// ... business methods

	public synchronized List<DATA_ITEM_TYPE> getDataItems() {

		if (cachedDataItems == null) {

			final List<RuntimeException> errorMessageContainer = new ArrayList<>();

			try (final InputStream is = new FileInputStream(
				getDataSourceFilePath()
			);
					final Workbook workbook = new XSSFWorkbook(
						is
					))
			{
				final Sheet sheet = CalcTablePoiNavigationUtils.getSheet(
					workbook,
					getDataSourceSheetName()
				);

				final CalcTableLandscapeDataReader reader = new CalcTableLandscapeStandardDataReader();

				cachedDataItems = reader.readData(
					sheet,
					getDataItemType(),
					errorMessageContainer
				);
			} catch (final Exception ex) {
				throw new RuntimeException(ex);
			}

			assertThat(errorMessageContainer).isEmpty();
		}

		return cachedDataItems;
	}

}
