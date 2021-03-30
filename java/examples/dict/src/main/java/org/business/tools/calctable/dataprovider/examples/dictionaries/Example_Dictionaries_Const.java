package org.business.tools.calctable.dataprovider.examples.dictionaries;

import java.nio.file.Path;

import org.business.tools.calctable.dataprovider.common.util.ResourceUtils;

public interface Example_Dictionaries_Const {

	// ... constants

	Path EXAMPLE_DICTIONARIES__FILE_PATH__IN_LANDSCAPE_FORMAT = //
			ResourceUtils.getAbsoluteResourcePath(
				"data-sources/Example_Dictionaries_PersonData_in_LandscapeFormat.xlsx"
			);

	Path ROOT_MODULE_PATH = ResourceUtils.getAbsoluteResourcePath("../../../..");

	String MODULE_PATH__EXAMPLES_TESTDATA = "examples/test-data";

	String MODULE_INTERNAL_PATH__SRC_MAIN_JAVA = "src/main/java";

	String MODULE_INTERNAL_PATH__SRC_MAIN_RESOURCES = "src/main/resources";

	String TARGET_ENUMS_PACKAGE_NAME = "org.business.tools.calctable.dataprovider.examples.testdatasets.external.model.type";

}
