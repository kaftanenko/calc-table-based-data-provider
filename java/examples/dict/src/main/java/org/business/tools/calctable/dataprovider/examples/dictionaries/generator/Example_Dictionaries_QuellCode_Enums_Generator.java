package org.business.tools.calctable.dataprovider.examples.dictionaries.generator;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.business.tools.calctable.dataprovider.examples.dictionaries.generator.util.FreemarkerTemplateMethodToEnumClassName;
import org.business.tools.calctable.dataprovider.examples.dictionaries.generator.util.FreemarkerTemplateMethodToEnumConstantName;
import org.business.tools.calctable.dataprovider.examples.dictionaries.model.Dictionary;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Example_Dictionaries_QuellCode_Enums_Generator {

	// ... constants

	private final static String FTL_TEMPALTE__FILE_NAME__ENUM = "template_enum.ftl";

	// ... dependencies

	private final Configuration freemarkerConfiguration = new Example_Dictionaries_Freemarker_Configuration();

	// ... business methods

	public void generate(
			final Dictionary dictionaryData,
			final String enumPackageName,
			final Writer enumWriter
	)
			throws Exception
	{

		final Template template = freemarkerConfiguration.getTemplate(FTL_TEMPALTE__FILE_NAME__ENUM);

		final Map<String, Object> templateDataModel = new HashMap<>();
		templateDataModel.put(
			"packageName",
			enumPackageName
		);
		templateDataModel.put(
			"dictionary",
			dictionaryData
		);
		templateDataModel.put(
			"toEnumClassName",
			new FreemarkerTemplateMethodToEnumClassName()
		);
		templateDataModel.put(
			"toEnumConstantName",
			new FreemarkerTemplateMethodToEnumConstantName()
		);

		template.process(
			templateDataModel,
			enumWriter
		);
	}
}
