package foo.lesson;

/**
 * WEB网站的发展历程<p>
 * 
 * 1.静态网站(HTML)只要浏览器就能访问,内容没法改变。
 * 2.动态网页资源->Servlet
 * 
 * 
 * 一，环境配置：
 * 一，JDK的配置过程
 * JDK[JavaDevolepmentKit](包含了JRE[JavaRuntimeEnviorment])安装过程:
 * 1.%JAVA_HOME%:JDK的根目录
 * 2.PATH环境变量(可运行文件/BIN):%JAVA_HOME%/BIN,%JAVA_HOME%/jre/BIN
 * 3.ClassPath：%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\jre\lib\rt.jar;
 * 加载java虚拟机本身需要的类文件
 * 
 * JAVA文件的编译过程
 * 先调用JAVA.exe编译字节码.class文件
 * 字节码装载到JVM(Java虚拟机)里面，由JVM来运行。
 * JVM是个程序 也需要加载类
 * 
 * 二，WEB服务器（Tomcat）
 * ApacheTomcat的配置
 * 1.%CATALINA_HOME%:根目录
 * 2.PATH环境变量：%CATALINA_HOME%/BIN
 * 
 * 三，Maven
 * 1.%M2_HOME%:Maven的根目录
 * 2.PATH环境变量：%M2_HOME%/BIN
 * 什么是Maven:
 * 
 * 
 * 四，什么是WEB服务器：
 * 1.什么是Tomcat?
 * #WEB服务器,处理动态网络资源(servlet)
 * 	
 * 2.为什么要用Tomcat?
 * #处理动态网络资源(servlet)
 * 3.Tomcat的优点和缺点是什么？
 * #可以处理动态网络资源
 * #相比静态页面方式，效率要低很多
 * 4.优点和缺点决定了它的适用范围
 * #你需要用到动态网络资源JSP
 * 
 * 5.Tomcat的机构
 * -bin目录:可执行文件
 * -conf(config)目录:配置文件
 * -webapps：网站应用程序
 * 
 * 五，什么是Servlet 什么是JSP
 * 1.什么是Servlet，为什么要使用它？
 * #用Java编写的服务器片段，可以处理动态网络资源
 * 2.Servlet的优点和缺点？
 * #优点:JAVA语言本身的优点 加上 处理动态网络资源
 * #缺点：由于它生成静态页面的方式，导致使用不方便，有大量的冗余代码
 * 
 * 1.什么是JSP(JavaServletPage):
 * -本质上就是一个Servlet
 * 2.JSP的优点和缺点:
 * -优点：你只需要处理网页动态的部分
 * -缺点：本身需要转化成JAVA文件，性能会有一点影响，
 *       JSP相当于Servlet+HTML,在网页里面嵌套JAVA代码，维护不易。
 * 
 * 3.怎么使用Servlet和JSP
 * Servlet:处理后台逻辑
 * JSP    :页面显示
 * 
 * 六，程序应用架构
 * B/S架构:Broswer浏览器/Servler服务器 
 *   #通过浏览器访问网页-WEBQQ
 * C/S架构:Client/Server 
 *   #QQ
 * 
 * 八，XML和HTML：
 * XML：
 * <?xml version='1.0' encoding='utf-8'?>
 * HTML：
 * <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 * 
 * 九，中文乱码：
 * charset：字符集     /
 * encoding:字符编码/
 * ascii码：英文256个就够了    一个字节Byte
 * 中文字符编码：GBK/GB2312 二个字节Byte
 * 
 * Unicode:（统一各个国家的字符编码）
 * UTF-8:      第一个代表你是哪种编码格式，后两位字节怎么进行编码  三个字节
 * 
 * UTF-8(+BOM):windows自己做的标准
 * UTF-8：
 * UTF-16:a占4个字节，太占资源
 * 老版本ANSI:ascii
 * 
 * 防止中文乱码：
 * 为什么有中文乱码？
 * 1.字符编码不对
 * 2.没有对应字符集
 * 
 * 编码格式要求:统一用UTF-8
 * 1.修改IDE本身的编码格式:
 * #windows->prefenrences->General->WorkSpace (Java文件的编码)
 * #windows->prefenrences->General->Editor->TextEditors->Spelling
 * 
 * 2.要用一个专业的文本编辑器，必要用windows的记事本
 * #windows的记事本会根据的你的文字内容改变你的编码格式
 * #Note++，EditpLus，SublimeText
 * 
 * 1.为什么会有中文乱码?
 * -不同字符编码集字节不同
 * 2.怎么避免中文乱码？
 * -使用Unicode编码（UTF-8 UTF-16）
 * -使用UTF-8(为什么不用UTF-16)：
 * -有哪些地方要用UTF-8（任何适合涉及到IO流的地方都要统一编码）
 * -1.IDE
 * #windows->prefenrences->General->WorkSpace (Java文件的编码)
 * #windows->prefenrences->General->Editor->TextEditors->Spelling
 * -2.文本编辑器
 * #避免使用记事本(为什么)
 * #尽量使用专业的文本编辑器(可以查看字符编码，不会自作主张改变你的文件编码格式)
 * 
 * @author wyy
 * 2016年11月14日
 *
 */
public class Lesson20161114 {
	//构建一个简单的WEB应用：
	//1.Eclipse JAVAEE - WTP插件
	//2.导入servlet-api.jar
	//3.要启动一个Tomcat
	//4.新建一个DynamicWebProject ： 编写Servlet
	//5.WEB-INF/web.xml配置你写的那个Servlet
	
	
	//常见问题
	//1.已经开启一个Tomcat，端口已经被占用
	//2.工程名和类名要起得有意义一点
		//Test/Demo：测试工程
		//包名：你公司的网站名反过来  com.ssf.工程名.
	//3.不要用中文路径()
	
	
	//JAVAWEB应用跟原始的Java应用相比:
	//1.WEB服务器：
	//2.配置文件web.xml
	//3.导入servlet包
	//4.浏览器交互
	
	
	
	//明天讲
	//HTTP协议，Maven，JSTL
	//重定向，请求转发
	//作用域(Page页面域，Requets请求域，Session会话域，Application服务器域)
	//JSP页面的九大对象
	
	
   public static void main(String[] args) {
	   System.out.println("HELLO");
   }
}
