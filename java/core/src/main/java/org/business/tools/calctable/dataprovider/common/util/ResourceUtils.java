package org.business.tools.calctable.dataprovider.common.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils {

	// ... constants

	private static final Charset STANDARD_CHARSET__UTF8 = Charset.forName("UTF-8");

	// ... business methods

	public static Path getAbsoluteResourcePath(final String resourcePath) {

		try {
			return Paths.get(
				ResourceUtils.class.getResource(
					"/"
				).toURI()
			);
		} catch (final URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String getResourceText(final String resourcePath) {

		return getResourceText(
			resourcePath,
			STANDARD_CHARSET__UTF8
		);
	}

	public static String getResourceText(
			final String resourcePath,
			final Charset charset
	)
	{

		try {

			final Path resourceFilePath = new File(ResourceUtils.class.getResource(resourcePath).toURI()).toPath();
			return new String(
				Files.readAllBytes(resourceFilePath),
				charset
			);
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static Path buildFilePath_And_CreateTargetDir_If_NotExists(
			final Path rootModuleAbsolutePath,
			final String targetModuleReleativePath,
			final String targetModuleSourceDirReleativePath,
			final String targetFileName
	)
			throws IOException
	{

		final Path absoluteDirPath = rootModuleAbsolutePath.resolve(targetModuleReleativePath).resolve(
			targetModuleSourceDirReleativePath
		);

		Files.createDirectories(absoluteDirPath);

		final Path absoluteFilePath = absoluteDirPath.resolve(targetFileName);
		return absoluteFilePath;
	}

}
