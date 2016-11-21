package foo.lesson;

/**
 * HTTP协议
 * 
 * 
 * 浏览器到WEB服务器：
 * 1.输入HTTPURL
 * 2.-DNS(DomainNameServer)域名服务器：将网站地址解析成IP地址，localhost:127.0.0.1
 * 3.请求发到服务器
 * 4.服务器解析请求
 * 5.返回响应
 * 6.页面上的所有资源都要请求一次重新走一遍流程
 * 
 * 
 * 为什么要有HTTP协议(TCP/IP协议)：
 * 
 * <b>JAVA网络编程</b><p>
 * 1.计算机架构分层
 *  -物理层
 *  -数据链路层
 *  -网络层(IP)
 *  -传输层(TCP/UDP)
 *  -会话层
 *  -表示层
 *  -应用层(HTTP)
 *  
 * 2.TCP/ip协议是什么？什么是TCP协议，什么是UDP协议？
 *  -提供网络通信，是一个协议簇（包含 TCP/UDP/IP）,
 *  -TCP协议-面向连接的连接，可靠连接-相当于打电话-靠三次握手来实现
 *  -UDP协议-面向无连接，不可靠连接-相当于发短信-
 *  
 * 3.什么是三次握手？怎么实现？为什么要有三次握手
 *  -1.Server发送syn包
 *  -2.Client收到syn 发送 syn+ack包 , Client可以进行通信
 *  -3.Server收到syn+ack包，给client发送ack包，代表Server可以进行通信
 *  保证数据的正确传输
 *  
 * 4.什么是Socket？为什么要有Socket？会写简单的Socket的程序？
 *  -Socket套接字，本身并不是协议，他只是一套API
 *  -简化网络通信的操作
 *  -EchoServer和EchoClient
 *  
 * 5.什么是HTTP协议？为什么HTTP协议最流行
 *  -TCP/IP协议提供了网络通信功能，HTTP协议指定网络通信的格式
 *  -超文本传输
 *  
 *  -1.简单灵活，各种各样的文件格式都可以通过他传输
 *   利用MIME传输不同的文件格式
 *   
 *   request请求头
 *   
 * //Accept:text/html,application/xhtml+xml,application/xml;q=0.9,;q=0.8
 * //Accept-Encoding:gzip, deflate, sdch, br
 * //Accept-Language:zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,zh-TW;q=0.2
 * //Connection:keep-alive
 * //Cookie:__cfduid=dea7ed669d1c9831d1fd3f2c259704b1d1475328010; BAIDUID=5540DEAEB5B8E919AF7B586E98A6D65E:FG=1; PSTM=1477569022; BIDUPSID=4EB04CE7AD8F01ABA186C12F8A7AAC51; ispeed_lsm=2; BDUSS=VLbXQ1WjFWamRHQjVRSWYxczlvN35wU3dqdi1ja0RVM0hsaDhXY2VOZ2cyRXRZSVFBQUFBJCQAAAAAAAAAAAEAAACGjrIhYXJhZmF0NTU0NwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACBLJFggSyRYRX; B64_BOT=1; BD_HOME=1; H_PS_PSSID=1468_13550_21097_17001_20593_21454_21408_21377_21525_21190_21339; BD_UPN=12314353; __bsi=16489505035940762603_00_0_I_R_12_0303_C02F_N_I_I_0
 * //Host:www.baidu.com
 * //Upgrade-Insecure-Requests:1
 * //User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36
 *   
 *   response响应头
 *   
 *  Accept-Ranges:bytes
	Age:38227
	Cache-Control:max-age=2592000
	Content-Encoding:gzip
	Content-Type:text/css
	Date:Wed, 02 Nov 2016 13:55:41 GMT
	ETag:W/"57baa49e-63c6"
	Expires:Sun, 20 Nov 2016 09:16:42 GMT
	Last-Modified:Mon, 22 Aug 2016 07:07:10 GMT
	
	
	REPONSE CODE:
	404:无法访问
	200:访问成功
	500:找不到资源
	
	
	什么是Maven？
	打包工具？
	1.建立四个文件夹
		src/main/java:      源代码
		src/main/resources: 配置文件ClassPath
		src/main/webapp:    web应用 需要导入/WEB-INF/web.xml
		src/test/java:      单元测试代码
	2.导入POM.xml文件（跟的你src目录同级）
		POM.xml:配置的你的依赖包还有你工程的格式
		1#配置国内的阿里云镜像
	3.运行命令，确认你的Maven正确安装和配置
		常用的命令:mvn install
	4.IDE导入工程 （Exsiting Maven Project）
		
 * @author wyy
 * 2016年11月15日
 *
 */
public class Lesson20161115 
{
	//1.回顾昨天的内容-需要掌握的内容
	
	//0.WEB应用的简单发展历程？什么是BS架构？什么是CS架构？它们之间有啥异同？举一个例子出来？
	  //静态页面->Servlet->JSP
	  //BS：浏览器-服务端（任意用浏览器访问的应用都是）
	  //CS：客户端-服务端（有客户端的程序，如QQ，手机上的应用等）
	  //异同：1.服务端是一致的，前端显示不一样
	  //     2.BS比较简单一些，他只需要关注服务端。
	//1.jdk的环境配置
	//2.tomcat的环境配置
	
	//3.WEB服务器：
	 //1静态网页服务器：ApacheHTTP，Nginx
	 //2动态网页服务器:ApacheTomcat, jetty ,  Jboss,WebLogic
	 //怎么用这两个东西：
	//静态网页服务器效率高，但只能处理静态资源
	//动态网页服务器效率偏低，但可以处理动态资源
	//负载均衡：
	 //只有一台WEB服务器，请求量很大的时候，服务器有可能宕机，健壮性很差
	 //配置多态WEB服务器
	 //一般Nginx+多台tomcat做反向代理/负载均衡
	//1#Nginx来处理所有的请求，能处理的就处理了，不能处理的就转发给Tomcat
	
	//4.Servlet和JSP
	//5.Tomcat的简单配置
	  //热更新：reloadable="true"
	  //server.xml和web.xml
	//
	
	//开发时环境和发布时环境
	//开发时环境:
	  //要debugLOG
	  //WEB服务器要 热更新，reloadable="true"
	  //JSP页面不需要缓存
	
	//tips:
	//前端工程师，WEB工程师，JAVAWEB工程师
	//前端工程师：HTML5+JS+CSS(页面效果)
	//WEB工程师：ROR(RubyOnRails),python,PHP(开发效率特别高，性能，灵活 不好做工程语言)
	//JAVAWEB工程师:高性能 高并发 可扩展性强的那种应用情况
	
	//JAVA语言的特性：
	//1.很小的功能也需要很大的代码量来实现
	//2.拉低高手的层次，拉高新手的层次 来实现工程语言的特性。
	
	//Apache是什么东西?
	//Apache印第安部落阿帕奇 ， 战略直升机也叫阿帕奇
	//Apache是一个开源组织，开源第三方库都是由这个来维护的。
	
	
	//1116
	//数据库               -mysql
	//数据库GUI界面-NaviCat
	//jDBC操作
	//sql语句
}
