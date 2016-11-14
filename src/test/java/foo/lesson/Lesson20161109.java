package foo.lesson;

public class Lesson20161109 {
	//1.使用MAVen组织工程
	//src/main/java :源代码
	//src/main/resources: 配置文件   classpath:xxx.xml
	//src/main/webapp:     WEB应用的地址  /WEB_INF/web.xml
	//src/test/java
	//<packging>jar</packging>  //war
	
	//maven:pom.xml
	//spring.xml spring配置文件  jdbc.properties logging.properties
	
	//2.SpringBoot方式,建立RESTful方式的web应用(了解即可)
	//前台JSP页面的编写方式：BootStrap3和Jquery
	//静态网页模板: freemaker,velocity,thymeleaf
	
	//1.如何整合Struts2和Velocity
	//#1.导入velocity和velocity.tools
	//#2.修改web.xml，让velocity来处理*.vm的后缀路径 , INDEX路径改为index.vm
	//#3.配置文件/WEB-INF/ velocity.properties 和 toolbox.xml
	//#4.配置struts
}
