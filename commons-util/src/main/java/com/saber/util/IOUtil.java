package com.saber.util;

import java.io.File;

/**
 * @author Saber Pan
 * 
 */
public class IOUtil {

	public static void createParentFolder(String path) {
		File parent = new File(path).getParentFile();
		if (!isExist(parent)) {
			new File(path).getParentFile().mkdir();
		}
	}

	public static boolean isExist(String path) {
		return isExist(new File(path));
	}

	public static boolean isExist(File file) {
		return file.exists();
	}
}
