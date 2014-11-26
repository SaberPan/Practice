package com.saber;

import com.saber.util.PathUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * @author Saber Pan
 * 
 */
public class BatchMain {

	public static void main(String[] args) throws Exception {
		System.setProperty("WORKDIR", PathUtils.getProjectRoot());
		String filePath = PathUtils.getPathInProject("data");
		File root = new File(filePath);
		for (File file : root.listFiles()) {
			if (isSkip(file.getName())) {
				continue;
			}
			Main.execute(FilenameUtils.getBaseName(file.getName()));
		}
	}

	private static boolean isSkip(String name) {
		if (name.contains("empty") || name.contains("true") || name.contains("false") || name.contains("temp")) {
			return true;
		}

		return false;
	}

}
