package foo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML解析常用工具包<p>
 * #三种XML解析方式：DOM/SAX/PULL
 * 1.XercesJ（apache力推，下一代XML解析器，JDK1.5版本自带） 推荐度5星
 * 2.Dom4j 推荐度4星
 * 3.XML PULL解析器：（在JavaWeb好像还不是很流行，在Android基本是标配了）
 * @author wyy
 *
 */
public class XmlParseDemo 
{
	//private Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void Dom4jTest(){}
	//
	@Test
	public void XercesJ() throws SAXException, ParserConfigurationException, FileNotFoundException, IOException{
		// 1.新建一个工厂类SAXParserFactory
		SAXParserFactory factory =SAXParserFactory.newInstance();
		// 2.让工厂类产生一个SAX的解析类SAXParser
		SAXParser parser = (SAXParser)factory.newSAXParser();
		// 3.从SAXPsrser中得到一个XMLReader实例
		XMLReader reader = parser.getXMLReader();
		// 4.得到内容处理器
		MyDefaultHandler saxHandler = new MyDefaultHandler();
		// 5.把自己写的handler注册到XMLReader中，一般最重要的就是ContentHandler
		reader.setContentHandler(saxHandler);
		// 6.将一个xml文档或者资源变成一个java可以处理的InputStream流后，解析正式开始
		reader.parse(new InputSource(new FileInputStream("src//spring-mybatis.xml")));
	}
}

// 自定义的解析类,通过此类中的startElement了解uri,localName,qName,Attributes的含义  
class MyDefaultHandler extends DefaultHandler 
{  
	private Logger logger = LoggerFactory.getLogger(getClass());
	boolean isOk = false;
	
    @Override  
    public void startElement(String uri, String localName, String qName,  Attributes attributes) throws SAXException {  
        super.startElement(uri, localName, qName, attributes);  
        //logger.info("--------------startElement开始执行--------------------------");  
        logger.info("uri: " + uri);  
        logger.info("localName: " + localName);  
        logger.info("qName: " + qName);  
        for (int i = 0; i < attributes.getLength(); i++) {  
            String value = attributes.getValue(i);// 获取属性的value值  
            logger.info(attributes.getQName(i) + ": " + value);  
        }  
        logger.info("");
        //logger.info("------------------startElement执行完毕---------------------------");  
    }  
    
    @Override  
    public void endElement(String uri, String localName, String qName) throws SAXException {  
        super.endElement(uri, localName, qName);  
        //当解析作者元素的结束的时候,设置isOK为false  
        if ("aop:config".equals(qName)) {  
            isOk = false;  
            System.out.println("----------------");
        }  
    }  
    
    @Override  
    public void characters(char[] ch, int start, int length) throws SAXException {  
        super.characters(ch, start, length);  
        // 当解析的标识符为true时,打印元素的内容  
        if (isOk) {  
            System.out.println("####"+new String(ch, start, length));  
        }  
    } 

} 

