/**
 * 
 */
package com.saber.util;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

/**
 * @author Saber Pan
 * 
 */
public class PathUtils {

	private static String projectRoot = null;

	public static String getProjectRoot() {
		if (projectRoot == null) {
			File classFile = new File(PathUtils.class.getClassLoader().getResource("").getPath());
			projectRoot = classFile.getParentFile().getParentFile().getAbsolutePath();
		}
		return projectRoot;
	}

	public static String getPathInProject(String relatedPath) {
		return FilenameUtils.normalize(getProjectRoot() + "/" + relatedPath);
	}

	public static String getPath(String source) {
		return ClassLoader.getSystemResource(source).getPath();
	}
}
