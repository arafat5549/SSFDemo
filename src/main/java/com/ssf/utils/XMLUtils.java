package com.ssf.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @类名: XmlParser
 * @类功能描述: 解析xml
 * @时间 ：2011-4-13 上午08:33:50
 */
public class XMLUtils {
	/**
	 * 
	 * @方法功能描述：生成空的xml文件头
	 * @方法名:createEmptyXmlFile
	 * @param xmlPath
	 * @返回类型：Document
	 * @时间：2011-4-14下午12:44:20
	 */
	public static Document createEmptyXmlFile(String xmlPath) {
		if (xmlPath == null || xmlPath.equals(""))
			return null;

		XMLWriter output;
		Document document = DocumentHelper.createDocument();

		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			output = new XMLWriter(new FileWriter(xmlPath), format);
			output.write(document);
			output.close();
		} catch (IOException e) {
			return null;
		}
		return document;
	}

	/**
	 * 根据xml文件路径取得document对象
	 * 
	 * @param xmlPath
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocument(String xmlPath) {
		if (xmlPath == null || xmlPath.equals(""))
			return null;

		File file = new File(xmlPath);
		if (file.exists() == false) {
			return createEmptyXmlFile(xmlPath);
		}

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 
	 * @方法功能描述：得到根节点
	 * @方法名:getRootEleme
	 * @param DOC对象
	 * @返回类型：Element
	 * @时间：2016-04-06 20:00:08
	 */
	public static Element getRootNode(Document document) {
		if (document == null)
			return null;

		Element root = document.getRootElement();
		return root;
	}

	/**
	 * 
	 * @方法功能描述: 根据路径直接拿到根节点
	 * @方法名:getRootElement
	 * @param xmlPath
	 * @return
	 * @throws DocumentException
	 * 			@参数描述 :
	 * @返回类型：Element
	 * @时间：2016-04-06 20:00:08
	 */
	public static Element getRootNode(String xmlPath) {
		if (xmlPath == null || (xmlPath.trim()).equals(""))
			return null;
		Document document = getDocument(xmlPath);
		if (document == null)
			return null;
		return getRootNode(document);
	}
	
	public static String getStringValue(Document document,String path){
		String result = null;
		if(null == document || StringUtils.isBlank(path)){
			System.err.println("document or path is null");
		} else {
			Element element = (Element) document.selectSingleNode(path);
			result = element.getTextTrim();
		}
		
		return result;
	}
	public static int getIntergeValue(Document document,String path){
		int result = 0;
		String value = getStringValue(document,path);
		if(StringUtils.isBlank(value)){
			System.err.println("the text is null");
		} else {
			result = Integer.parseInt(value);
		}
		return result;
	}
}
