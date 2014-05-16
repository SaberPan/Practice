/**
 * 
 */
package com.saber.util;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * @author Saber Pan
 * 
 */
public class XmlUtils {

	public static <T> T getFromFile(Class<T> clazz, String path) {
		try {
			Serializer serializer = new Persister();
			return (T) serializer.read(clazz, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T convert2Object(Class<T> clazz, String content) {
		try {
			Serializer serializer = new Persister();
			return (T) serializer.read(clazz, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convert2String(Object object) {
		try {
			Serializer serializer = new Persister();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			serializer.write(object, out);
			return new String(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeToFile(Object source, String path) {
		try {
			Serializer serializer = new Persister();
			File file = new File(path);
			IOUtil.createParentFolder(path);
			serializer.write(source, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
