# Servlet

# JavaWeb

- 静态页面 HTML/CSS/JS
- servlet：（在java里面写HTML）
- JSP：HTML里面嵌套JAVA，在HTML里面写JAVA
- ​



### 什么是Servlet

- 用Java编写的服务器端程序。
- 是JavaWeb三大组件之一（Servlet、Filter、Listener）
  - Servlet  响应请求和处理请求
  - Filter过滤器：处理中文乱码
  - Listener监听器：Socket监听端口
    - Tomcat默认端口8080,mysql默认端口3306,80端口

### Servlet的主要用途

- 交互式浏览和修改数据，生成动态Web内容（处理客户端请求的动态资源）
- 获取请求数据-->处理请求-->完成响应
- 理论上Servlet可以响应任何类型的请求，但绝大多数只用来处理HTTP请求。

### 实现Servlet的方式

- 实现Servlet接口（不方便）
- 继承GenericServlet类（不方便）
- 继承HttpServlet类（方便）

### 创建DynamicWebProject动态web工程

- 导入在java_ee_sdk-7u2\glassfish4\mq\lib目录下的javax.servlet-api.jar

- 实现servlet接口

- Tomcat根目录下 webapp 每一个文件夹都是一个web工程，ROOT目录下是一个空的web.xml配置

- WEB-INF目录下创建web.xml

  ```xml
   <servlet>
    <!-- 给你的servlet起名字 -->
      <servlet-name>hello_servlet</servlet-name>
    <!-- 指明Class类 --><servlet-class>com.ssf.ssh.ServletDemo</servlet-class>
    <!-- servlet配置0~N个初始化参数 -->
    </servlet>

    <servlet-mapping>
      <servlet-name>hello_servlet</servlet-name>
    	<url-pattern>/sp</url-pattern>
    	<url-pattern>/othersp</url-pattern>
    </servlet-mapping>

  ```

  ​

### Servlet的生命周期

- 服务器创建Servlet对象
  + 当Servlet第一次被请求时，或服务器启动时，服务器会创建Servlet实例。
  + 服务器默认是在servlet第一次被请求时创建Servlet实例，如果希望服务器启动时就创建Servlet实现需要在web.xml中配置
  + 服务器只为一个类型的Servlet创建一个实例对象，所以Servlet是**单例**的。
- init() 初始化只会调用一次。
  + 当服务器创建Servlet实例后会马上调用Servlet的init(ServletConfig)方法，完成对Servlet的初始化
  + 服务器会在调用init()方法时传递ServletConfig参数
- service() 每一次请求都会调用一次。
- destroy() 服务器通常不会销毁Servlet，通常只有在服务器关闭时才会销毁Servlet，只会调用一次。
  + 可以在destory()方法中给出释放Servlet占有的资源，但通常Servlet是没什么可要释放的，所以该方法一般都是空的

### Servlet工程创建过程

- 创建一个DynamicWebProject

- 导入javax.servlet-api.jar

- 创建一个HttpServlet 

  - doGet ：安全性较差，传输量比较小
  - @doPost：安全性较好，传输量大-》表单传输（常用方式）

- 如果没有web.xml去Tomcat/webapps的根目录下WEB-INF目录下的复制过来

- 在web.xml下配置servlet

  - ```xml
    <servlet>
      <servlet-name>Demo</servlet-name>
      <servlet-class>包名+类名</servlet-class>
    </servlet>

    <servlet-mapping>
      <servlet-name>Demo</servlet-name>
      <url-pattern>/sp</url-pattern>
    </servlet-mapping>
    ```

    运行servlet，第一次运行时需要配置Web服务器

    制定到Tomcat的根目录

- 解决中文乱码的问题

  - doGet：

    - 0.HTML的编码格式，如果有的话
    - 1.浏览器发起 ->request流->已编码好的ISO-8859-1数据流(这就是request.setCharEncoding("UTF-8")无效，因为数据流已经读取完毕
    - 2.处理请求:要把ISO-8859-1编码数据流转化为UTF-8, new String(name.getBytes("ISO-8859-1"),"UTF-8");
    - 3.response.getWriter();//我们要用response写入到HTML里面，先设置setContentType("text/html; charset=UTF-8")

  - doPost:

    - request数据流分为主数据流（表头等）加Form表单包（非常类似邮件里面的邮件内容和邮件附件）所以要读取两次

    - request内置一个form表单数据包,比doGet要多一步IO流操作

      ```java
      //多设置一步request的字符编码
      request.setCharacterEncoding("UTF-8");
      //doPOST表单传输在这里多了一个读取包IO流
      request.getParameter("name");
      //比doGet少了一步转化IO流的过程，因为他读取后的数据流就已经是UTF-8
      ```

    - response.getWriter();//我们要用response写入到HTML里面，先设置response.setContentType("text/html; charset=UTF-8")



- Tomcat的web.xml和自己WebAPP的web.xml

  - 先读取自己的配置和再读全局配置


-   只要你个性的东西

    ```xml
     <welcome-file-list>
          <welcome-file>index.html</welcome-file>
            <welcome-file>index.htm</welcome-file>
            <welcome-file>index.jsp</welcome-file>
        </welcome-file-list>

    ```

    ```xml
        <servlet>
            <servlet-name>jsp</servlet-name>
            <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
            <init-param>
                <param-name>fork</param-name>
                <param-value>false</param-value>
            </init-param>
            <init-param>
                <param-name>xpoweredBy</param-name>
                <param-value>false</param-value>
            </init-param>
            <load-on-startup>3</load-on-startup>
        </servlet>
    ```

-   1.访问网站根路径是不是会找不到资源

    - 少什么，少了一个主页，有哪两种方式

    - index.jsp 跟web.xml路径一样吗？

    - 等价于配置<url-pattern>/</url-pattern>的servlet

    - JSP跟Servlet是同一个东西，由TomcatWEB服务器的JSP解析器解析，由它来配置(参看Tomcat的web.xml)

    - 既然是同一个东西，为什么要用JSP

      - 为什么要用servlet？解决动态资源的问题
      - 为什么要用HTML？解决页面显示的问题
      - servlet缺点：输出到浏览器的过程太麻烦
      - 你要out.write整个HTML的架构,而不是只输出
      - HTML缺点:静态页面，不能动态显示
      - JSP就相当于他们中间的桥梁,它是不是既可以像HTML那样很容易架构你的页面显示，又可以像Servlet动态的请求资源

    - 碰到技术问题，首先要想为什么要有这样技术，解决什么样的技术痛点，任意一门流行起来的技术都不是凭空产生。

    - 参看Tomcat的work目录，底下有生成*jsp.java文件。

    - JSP帮我们做了大量重复的工作，输出HTML的文本结构

    - ​

    - ```java
       final javax.servlet.jsp.PageContext pageContext;
       javax.servlet.http.HttpSession session = null;
       final javax.servlet.ServletContext application;
        final javax.servlet.ServletConfig config;
          javax.servlet.jsp.JspWriter out = null;
          final java.lang.Object page = this;
          javax.servlet.jsp.JspWriter _jspx_out = null;
          javax.servlet.jsp.PageContext _jspx_page_context = null;
      ```

-   四大作用域

    - Page页面域：离开当前页面就无效
    - Request请求域：在一次请求期间有效->HttpRequest
    - Session会话域：会话（浏览器打开到关闭浏览器的过程）->HttpSession（登录名）
    - Application应用域：整个对Web应用都有效->ServletContext(网站访问量)
    - Cookie：（记住你登录名）保存在本地，就是为了浏览器之间

-   JSP语法

    - <%@ %> 定义JSP文件的属性类型，导入包
    - <% %> JAVA语法
    - <%! %> :方法和变量
    - <%= %>:获取变量

-   两种页面跳转方式

    - sendReirect
    - request.getRequestDispatcher("/out.jsp").forward(request, response)

-   MVC

    - Controller]: Servlet
    - View]:     JSP 或者HTML
    - Model]: 数据库


- 浏览器-(request)->[Controller]-逻辑处理->[Model]-查询或者更改后的数据->[Controller]-response->[View]

### ServletContext

- ServletContext是Servlet三大域对象之一。ServletContext在服务器启动时创建，在服务器关闭时销毁，一个JavaWeb应用只创建一个ServletContext对象。
- 功能：
  + 存取数据。因为在一个JavaWeb应用中，只有一个ServletContext对象，所以在ServletContext中保存的数据可以共整个JavaWeb应用中的动态资源共享。ServletContext是Servlet三大域对象之一，域对象内部有一个Map，用来保存数据
  + 读取web.xml中配置的应用初始化参数。
  + 获取项目资源。


### Session&Cookie

#### Session会话

- ​

#### Cookie

- 小的数据片段

- 关闭浏览器以后还有会缓存

- 不同浏览器会缓存不同的Cookie

- 添加Cookie

  ```java
  Cookie c = new Cookie("uname","");
  c.setMaxAge(60*60*24);
  response.addCookie(c);
  ```

- 删除Cookie

  ```java
  Cookie c = new Cookie("uname","");
  c.setMaxAge(0);
  response.addCookie(c);
  ```

  ​

- 1.浏览器第一次进来Login.jsp，从Cookie里面找

- 2.发请求到Servlet，添加到Cookie'里面去addCookie("uname",name)

- 3.存放到Session里面去session.setAttribute("uname",name)

- 4.Login.jsp我存放的是Cookie的值

  - 重新输入一个名字 我刷新，又从Cookie里面取一遍

- 5.Suc1.jsp我存放的是session的值

  - ​



### 正则表达式

- ^   字符串的开始
- $   字符串的结束
- ?     零次或一次
- *零次或多次
- +一次或多次
- [a-zA-Z0-9] 集合
- 怎么判断中文[\u4e00-\u9fa5]
- ​

Servlet3.0 @注解 -Tomcat7以后

Servlet2.5 配置表方式web.xml- Tomcat7以前

web.xml有一个配置 代表要不开启@注解功能

metadata-complete="true" 默认关闭

### JSTL(Java标准标签库)

- 简化JSP的操作
- 导入标签库 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
- 常用标签 <c:url value="/res/img/favicon.ico" />
- 等价于 ${你的web应用名}/res/img/favicon.ico


- bootStrap css的模板 他跟Jquery（js的工具类）
- 这两个合作可以实现绝大部分的页面效果，
- 我对你们的要求就是 如果有一个HTML页面给你的话
- 你们要会改成JSP
- 修改成JSP需要注意的地方：
  - 把硬编码的资源地址改成绝对路径（相对你WEB应用的绝对路径）
  - JSP的头文件 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  - 如果你用JSTL，还得一起导入JSTL标签库
  - <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  - 你改form表单的Action
  - 改后缀名



- 绝对路径：写全你要去的地址路径 C://Users/Test
- 相对路径：比如你在C://Users 你只需要输入Test就可以导向到 C://Users/Test
- 验证参数是否正确（双端验证）前台验证（避免无效请求，提升网站有效访问量）和后台验证（保证数据的正确性）

# Tomcat 

JDK的版本要跟Eclipse的版本对应（都是32位或者都是64位）

JDK的根目录，Eclipse的根目录，Tomcat的根目录尽量使用全英文路径。

### 环境变量设置 

- JDK版本：JDK1.7
- 先新建环境变量 JAVA_HOME  ：JDK1.7
- 环境变量CLASSPATH ：            %JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\jre\lib\rt.jar
- 设置BIN目录到Path对象（jdk和jre都要）:%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

### 安装Tomcat7

- 设置BIN目录到Path路径
- 新建环境变量 CATALINA_HOME 到你的Tomcat根目录
- cmd命令行 startup 开启Tomcat服务 (shutdown关闭服务)
- 进入localhost:8080

### Tomcat各个路径的作用

- bin目录：二进制可执行文件 (.exe或者.bat)
- conf：配置文件，例如：server.xml、context.xml、web.xml、tomcatusers.xml
- lib：Tomcat需要的lib包
- logs：日志文件
- temp：存放tomcat运行时产生的临时文件，当tomcat关闭后，这个目录中的文件可以删除
- webapps：这个目录下的每个文件夹对应一个JavaWeb应用程序
- work：webapps下的应用程序在运行时会自动生成文件，就在work目录下。work目录删除了也没问题，但再次运行应用程序还要再生成work目录和文件。





### TomcatTips:

- tomcat6支持servlet2.5 / tomcat7支持servlet3.0
- 默认端口8080 - mysql的默认端口为3306



### HTML简介:

**万维网**（world wide web），又称WWW，便是我们所熟知的Web。Web的本质是一个由[超文本文档](https://en.wikipedia.org/wiki/Hypertext)互相链接而成的文档系统，可以看做一整个分布式的文档。万维网是互联网提供的众多服务之一，运行在互联网的基础上。

提到万维网，我们得谈一谈 **万维网联盟**（World Wide Web Consortium，W3C），如果你从事web开发，你会常常接触到它。万维网联盟又称W3C理事会，负责万维网标准的制定（包括HTML、CSS等），以解决web的兼容性问题。

> 我们知道，人们一般通过浏览器来查看web文档（也就是网页）。然而，不同的浏览器可能对同样的web文档做出不同的显示；与此同时，不同的浏览器会定义其特定的web文档的语法（例如`-webkit`, `-moz`, `-o`, `-ms`）。浏览器之间的区别使得开发一致观感的页面变得更加困难。这时便需要w3c标准化的努力。

**超文本文档**（hypertext document）是指具有超链接功能的文档，通常被用来创建web文档（网页）。它可以包含文本、图片、音频、视频等。我们一般通过浏览器来查看超文本文档，而浏览器则通过互联网来获取这些文档。

超文本文档中一般包含若干[超链接](https://zh.wikipedia.org/wiki/%E8%B6%85%E9%80%A3%E7%B5%90)（hyperlink），用户可以跟随超链接，而浏览器负责取回超链接以标识的超文本文档。

> **URL**（uniform resource locator），统一资源定位符，用来全局唯一地标识Web的资源。超链接所标示的地址、浏览器地址栏的文字都是URL。

**HTML**（HyperText Markup Language，超文本标记语言）是一种创建超文本文档的语言，已经广泛地用于网页设计。HTML是用一系列的HTML标签写成的，每种HTML标签都有对应的语义和一致的外观。html文件通常以`.html`作为后缀名。

一个简单的HTML文件可能是这样的（我们的介绍基于[HTML5](https://zh.wikipedia.org/zh-cn/HTML5)标准）：

```html
<html>
<head>
  <title>Hello, HTML!</title>
</head>

<body>
  <p>这是一个简单的HTML文件</p>
</body>

</html>
```

> 可以把上述文件保存为`hello.html`（见参考代码），然后从浏览器打开。你会在页面中看到`这是一个简单的HTML文件`字样，同时浏览器的标签栏会显示`Hello, HTML!`。现在你可以不必理解上述的HTML文件，在之后的课程中我们会详细介绍HTML的语法。