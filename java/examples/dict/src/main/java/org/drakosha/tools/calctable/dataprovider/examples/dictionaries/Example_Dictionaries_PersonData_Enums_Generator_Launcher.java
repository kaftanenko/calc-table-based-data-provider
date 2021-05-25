package org.drakosha.tools.calctable.dataprovider.examples.dictionaries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const.EXAMPLE_DICTIONARIES__FILE_PATH__IN_LANDSCAPE_FORMAT;
import static org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const.MODULE_INTERNAL_PATH__SRC_MAIN_JAVA;
import static org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const.MODULE_PATH__EXAMPLES_TESTDATA;
import static org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const.ROOT_MODULE_PATH;
import static org.drakosha.tools.calctable.dataprovider.examples.dictionaries.Example_Dictionaries_Const.TARGET_ENUMS_PACKAGE_NAME;

import org.drakosha.tools.calctable.dataprovider.common.util.ResourceUtils;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator.Example_Dictionaries_QuellCode_Enums_Generator;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator.util.FreemarkerTemplateMethodToEnumClassName;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;
import org.drakosha.tools.calctable.dataprovider.examples.dictionaries.parser.Example_Dictionaries_CalcTable_Parser;

public class Example_Dictionaries_PersonData_Enums_Generator_Launcher {

	// ... dependencies

	private static final Example_Dictionaries_CalcTable_Parser dictionariesParser = //
			new Example_Dictionaries_CalcTable_Parser();

	private static final Example_Dictionaries_QuellCode_Enums_Generator dictionariesEnumsGenerator = //
			new Example_Dictionaries_QuellCode_Enums_Generator();

	// ... launcher method

	public static void main(final String... args)
			throws Exception
	{

		final Path sourceFilePath = EXAMPLE_DICTIONARIES__FILE_PATH__IN_LANDSCAPE_FORMAT;

		// ... read dictionary data
		final List<Dictionary> dictionariesSourceData = //
				dictionariesParser.parseDictionaryFile(sourceFilePath.toString());

		// ... generate enumerations
		dictionariesSourceData.forEach(dictionary -> {
			try {

				final String enumClassName = new FreemarkerTemplateMethodToEnumClassName().exec(
					Collections.singletonList(dictionary.getName())
				).toString();

				final Path targetFilePath = buildTargetFilePath(enumClassName + ".java");

				System.out.println(
					String.format(
						"Try to generate the file: %s",
						targetFilePath
					)
				);

				dictionariesEnumsGenerator.generate(
					dictionary,
					TARGET_ENUMS_PACKAGE_NAME,
					new FileWriter(targetFilePath.toFile())
				);

				System.out.println("... succeeded.");
			} catch (final Exception ex) {

				System.out.println("... failed.");
				throw new RuntimeException(ex);
			}
		});
	}

	// ... helper methods

	private static Path buildTargetFilePath(final String enumClassFileName)
			throws IOException
	{

		final String targetPackageDirRelativePath = TARGET_ENUMS_PACKAGE_NAME.replaceAll(
			"\\.",
			"/"
		);

		final String targetModuleInternalResourceDirPath = MODULE_INTERNAL_PATH__SRC_MAIN_JAVA
				+ File.separator
				+ targetPackageDirRelativePath;

		final Path targetFilePath = ResourceUtils.buildFilePath_And_CreateTargetDir_If_NotExists(
			ROOT_MODULE_PATH,
			MODULE_PATH__EXAMPLES_TESTDATA,
			targetModuleInternalResourceDirPath,
			enumClassFileName
		);
		return targetFilePath;
	}
}
