package org.drakosha.tools.calctable.dataprovider.examples.dictionaries.generator.util;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.apache.commons.text.WordUtils;

import java.util.List;

public class FreemarkerTemplateMethodToEnumConstantName
		implements
		TemplateMethodModel
{

	@Override
	public TemplateModel exec(final List args)
			throws TemplateModelException
	{

		if (args.size() != 1) {
			throw new TemplateModelException("An only argument of type 'String' is expected!");
		}

		String tmpResult = (String) args.get(0);
		tmpResult = tmpResult.replaceAll(
			"\\(.*\\)",
			" "
		);
		tmpResult = tmpResult.replaceAll(
			"[^A-Za-z0-9]+",
			" "
		);
		tmpResult = WordUtils.capitalize(
			tmpResult
		);
		tmpResult = tmpResult.trim();
		tmpResult = tmpResult.replaceAll(
			"\\s+",
			"_"
		);
		tmpResult = tmpResult.toUpperCase();

		return new SimpleScalar(tmpResult);
	}
}
