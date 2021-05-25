package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;

public class Example_Dictionaries_Freemarker_Configuration
		extends
		Configuration
{

	// ... constants

	private final static File FTL_TEMPALTES_DIR = new File(
		Example_Dictionaries_Freemarker_Configuration.class.getResource(
			"/generator-templates"
		).getFile()
	);

	private final static String STANDARD_ENCODING__UTF8 = "UTF-8";

	// ... properties

	public Example_Dictionaries_Freemarker_Configuration() {

		super(Configuration.VERSION_2_3_29);
		try {
			setDirectoryForTemplateLoading(FTL_TEMPALTES_DIR);
			setDefaultEncoding(STANDARD_ENCODING__UTF8);
			setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			setLogTemplateExceptions(false);
			setWrapUncheckedExceptions(true);
			setFallbackOnNullLoopVariable(false);
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
