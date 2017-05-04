package com.ssf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springside.modules.utils.io.IOUtil;
//import org.springside.modules.utils.io.URLResourceUtil;
//import org.springside.modules.utils.number.NumberUtil;

/**
 * 关于Properties的工具类
 * 
 * 1. 统一读取Properties
 * 
 * 2. 从文件或字符串装载Properties
 * 
 * @author calvin
 */
public class PropertiesUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/////////////////// 读取Properties ////////////////////

	public static Boolean getBoolean(Properties p, String name, Boolean defaultValue) {
		return BooleanUtils.toBooleanObject(p.getProperty(name));
	}

	public static Integer getInt(Properties p, String name, Integer defaultValue) {
		return NumberUtils.toInt(p.getProperty(name), defaultValue);
	}

	public static Long getLong(Properties p, String name, Long defaultValue) {
		return NumberUtils.toLong(p.getProperty(name), defaultValue);
	}

	public static Double getDouble(Properties p, String name, Double defaultValue) {
		return NumberUtils.toDouble(p.getProperty(name), defaultValue);
	}

	public static String getString(Properties p, String name, String defaultValue) {
		return p.getProperty(name, defaultValue);
	}

	/////////// 加载Properties////////
	/**
	 * 从文件路径加载properties.
	 * 
	 * 路径支持从外部文件或resources文件加载, "file://"或无前缀代表外部文件, "classpath://"代表resources,
	 */
	public static Properties loadFromFile(String generalPath) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = URLResourceUtil.asStream(generalPath);
			p.load(is);
		} catch (IOException e) {
			logger.warn("Load property from " + generalPath + " fail ", e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		return p;
	}

	/**
	 * 从字符串内容加载Properties
	 */
	public static Properties loadFromString(String content) {
		Properties p = new Properties();
		Reader reader = new StringReader(content);
		try {
			p.load(reader);
		} catch (IOException ignored) {
		} finally {
			IOUtils.closeQuietly(reader);
		}

		return p;
	}

}
