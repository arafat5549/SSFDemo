### 专业技能:

1. 精通JAVASE基础语法，精通数据结构,IO,并发/多线程,内存管理GC,基础排序和查找算法
2. 精通mysql,Oracle,Sqlserver数据库，了解数据库事务transaction,批处理Batch，索引Index一些常用的数据库优化技术
3. 熟练掌握Redis/MongoDb等Nosql菲关系型数据库
4. 精通Tomcat/jetty/weblogic等常用WEB服务器，熟练掌握Nginx/ApacheHttp等HTTP服务器
5. 熟练掌握HTML5,BoostrapCSS框架，JqueryJS框架，以及Jquery相关的一些前台UI框架 如JqueryEasyUI JqueryValidationJqueryZTree等
6. 精通Spring框架,SpringMvc/Struts2webmvc框架,Hibernate/Mybatis ORM框架
7. 精通Linux BashShell脚本编程，熟悉Linux系统的常用命令find,grep等
8. 精通ApacheShrio等安全框架，精通Servlet/jsp/jstL/el 表达式
9. 熟悉TCP/IP，Http等网络协议，OSI网络分层，熟练掌握简单的Socket编写。
10. 了解web开发中的高并发，多线程处理，数据库操作等常见的IO瓶颈。
11. 了解分布式系统架构,消息队列和RPC,集群和分布式的区别

### 其他技能:

- 精通Eclipse / IntellJ等常用IDE
- 精通EditPlus/vim/sublimb等专业文本编辑器
- 能熟练阅读技术文档，项目说明文档
- 平时喜欢上GitHub，CSDN，IBMDevelopers,StackOverFlow,V2EX等开发者社区或者网站博客浏览技术文档
- 对程序开发有极大热情，能适应大压力的工作，希望能找到一份能持续提高技术技能的工作

### 项目经验:

#### 天创网上商城系统(1年):

- 待遇：JAVA开发工程师 3.5K

- 项目描述：大型电子商务网站，为期望在3G手机平台上进行电子商务的公司架构网上销售平台

- 责任描述：

  负责其中的CMS后台管理系统,主要包含内容管理模块cms,用户管理模块,部门管理模块,为了后台的安全性和权限管理的细粒度划分，我们采用了ApacheShiro作为我们的安全框架,实现了

  1.前后台双重验证(在后台采用了Hibernate-Validation验证框架,符合JSR303验证规范，前端采用了Jquery-Validation验证框架，更容易与Jquery整合并且很容易实现Ajax验证)

  2.细粒度权限划分(具体到按钮那一级别)

  Menu按钮权限表,

  Role角色表

  User用户表

  用关联表r_user_role,r_role_menu来实现数据表之间的一对多映射和多对多映射

  采用了Mybatis作为我们的ORM框架，因为它在满足了JavaBe对象和数据集的映射情况下，能很方便的让我们手动编写SQL来实现数据库操作。我们的多表操作利用Join操作来完成，由用户可以获取出所有用户管理的权限

  采用Spring框架整合了ApacheShrio安全框架，零侵入

  整合，并且采用了SpringMvC作为我们的WEBMVC框架，shrio的后台权限控制通过自带的注解来控制完成，跟SpringMVC的注解风格一致，并且SpringMVC能很轻易跟实现JSON与JAVABEAN之间的映射，更容易与手机APP整合，而我们的后台代码不需要修改接口，并且更容易实现RESTFUL风格，通过@PathVariable的方式。

  Shrio一样提供了前台标签库，来控制标签的显示，当你的用户对某个按钮（Menu对象）的权限不足，他不会显示，这样避免用户进行无效操作。

  并且我么通过JqueryZTree生成按钮的树结构，因为一个按钮Menu可以包含若干个自身对象，通过传构建完成JSON数据来构建生成的结构树。