package org.business.tools.calctable.dataprovider.examples.dictionaries.generator.util;

import java.util.List;

import org.apache.commons.text.WordUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerTemplateMethodToEnumClassName
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
		tmpResult = tmpResult.replaceAll(
			"\\s+",
			""
		);
		tmpResult = "E" + tmpResult;

		return new SimpleScalar(tmpResult);
	}
}
