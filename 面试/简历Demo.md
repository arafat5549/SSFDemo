

简历的目的是为了获得面试机会

突然好的部分 不好你就不要写。适当美化。

HR和技术部门



1.简历的命名规范: 你的名字+应聘岗位

- HR会从简历库过滤你的简历
- 内容尽量多涉及岗位里面要求的知识点

2.尽量控制在两页之内

- 个人信息(学校 ,专业,毕业时间,户籍, 年龄, 工作经验 )
- 求职目标：不要写实习生 直接写java工程师
- 薪资：35k - 实习生就不在这个范围内(1k-2k)-试用期一般3个月 正常工资的80%


- 专业技能(岗位要求的知识点)
- 其他技能(可选的部分 额外的闪光点  英语数学或者你当过兵或者学生会干部)

3.项目经验（最少要有两个项目 尽量要有连续性）

- servlet+jsp 前台
- 项目重构(用框架重构我们的项目)
- SSM框架 描写后台

5.特别的被动

- 有就业机会 尽量先去试一下(一般你找的工作都会有所欠缺)
- 有任何问题 尽量早跟我沟通()
- 如果一段时间都弄不出来 就要考虑外部帮助(尽量少麻烦别人 也不要不麻烦别人)

6.面试前的准备

- 了解你要去的公司，临时抱佛脚（根据岗位需求有目的性的看）

7.面试后的准备

- 带着脑子来 带着脑子去(一定要面试时有欠缺的东西给记下来 回去加强)
- 尽量在群里面反馈面试题
- 面试完之后 尽量跟我沟通一下





### 专业技能：

1. 精通JAVASE基础语法，面向对象，数据结构和算法，IO，多线程/并发，内管管理GC
2. 精通Mysql，Oracle，Sqlserver数据库，了解数据库事务transaction，批处理Batch，数据库索引Index，存储过程preocess，备份和恢复数据库
3. 熟练掌握Redis/MongoDb等Nosql非关系型数据库
4. 精通Tomcat/jetty/weblogic等常用WEB服务器，熟练掌握Nginx/ApacheHttp等HTTP服务器
5. 精通Spring框架,SpringMvc/Struts2webmvc框架,Hibernate/Mybatis ORM框架
6. 熟练掌握HTML5,BoostrapCSS框架，Jquery,JS框架，以及Jquery相关的一些前台UI框架 如JqueryEasyUI JqueryValidationJqueryZTree等
7. 精通Linux BashShell脚本编程，熟悉Linux系统的常用命令find,grep,vim等
8. 熟悉TCP/IP协议，Http等网络协议，OSI网络分层，熟练掌握简单的Socket编写
9. 了解web开发中的高并发，多线程处理，数据库操作等常见的IO瓶颈。
10. 了解分布式系统架构,消息队列和RPC,集群和分布式的区别

### 其他技能:

- 精通Eclipse / IntellJ等常用IDE
- 精通EditPlus/vim/sublimb等专业文本编辑器
- 能熟练阅读技术文档，项目说明文档
- 平时喜欢上GitHub，CSDN，IBMDevelopers,StackOverFlow,V2EX等开发者社区或者网站博客浏览技术文档
- 对程序开发有极大热情，能适应大压力的工作，希望能找到一份能持续提高技术技能的工作



项目经验说明：

（你负责什么，你解决了什么问题，你学到了什么）

准备两份简历，一份我们这推出去（就讲你学了什么东西，数据库，MVC），一份是你自己准备外投的（不要讲你的培训经历，更看重自学能力）



### 项目经验:   

#### 基础Servlet+JSP商城:

- 待遇：JAVA开发工程师 3.5K
- 项目描述：大型电子商务网站，为期望在3G手机平台上进行电子商务的公司架构网上销售平台
- Servlet+JSP+Tomcat7.0+JDK7.0+Mysql5.5
- 责任描述：
  - MVC思想进行网络四层分层架构：Model层，Dao层，Service层，web层
  - 基础MVC Servlet处理后台逻辑Contoller ,Jsp负责显示View
  - JDBC编程和DBUtils怎么实现ORM映射，利用反射和泛型实现一个通用的数据库方法
  - 数据库设计 一对多 多对多映射比如（分类和商品）外键关联其他表和 无限级分类外键关联自身(分类)
  - 数据库的编码规范和JDK的编码规范的统一
  - 单元测试我们的DAO方法
  - 业务功能，如何实现订单和购物车，商品和分类
  - JSTL标签库和EL表达式，还有WEB网页目录的架构（静态资源和动态页面和页面布局的管理和Jar包的管理（类加载机制））
  - web.xml的基本配置和Tomcat的基本配置
  - filter过滤器实现粗粒度的权限处理和编码过滤
  - 多带了一个参数method 来实现url方法定向到方法级别

#### 商城重构版：

- 项目描述

​     Spring4.1.3 + Mybatis 3.1.1 + Druid1.0 + Ehcache2.6+Maven

- 责任描述：(准备工作,框架的学习)
  - SSM框架重构我们这个系统
  - Log4j日记模块进行系统日记处理
  - Spring整合Junit4进行单元测试
  - SpringMVC加入了JSON数据的处理，来进行双端的统一(手机端和PC端的统一)
  - Mybatis ORM框架最小化配置方式来进行DAO的处理
  - Spring框架的基本配置，最小化配置以约定为主，注解扫描的方式
  - Spring整合了Druid连接池(防止sql注入 sql监控)
  - Spring整合了Ehcache缓存
  - Spring整合了事务功能
  - Spring整合SpringMVC
  - SpringMVC整合了Hibernate-Validator参数验证框架
  - SpringMVC进行统一的错误处理
  - 工具类 Apache-commons  / Guava工具类
  - 利用Maven构建我们的系统工程
  - 利用Git进行项目的管理

### 商城完整版：

- 项目描述：

  Spring4.1.3 + Mybatis 3.1.1 + Druid1.0 + Ehcache2.6

- 责任描述：

  - 前后台双重验证(在后台采用了Hibernate-Validation验证框架,符合JSR303验证规范，前端采用了Jquery-Validation验证框架，更容易与Jquery整合并且很容易实现Ajax验证)

  - 后台权限模块的添加进行 细粒度权限划分(具体到按钮那一级别)

    Menu按钮权限表，Role角色表，User用户表

    用关联表r_user_role,r_role_menu来实现数据表之间的一对多映射和多对多映射

    我们的多表操作利用Join操作来完成，由用户可以获取出所有用户管理的权限，让用户自定义后台管理权限。

  - 使用Bootstrap进行页面的设计，利用Jqeury进行前台DOM操作和AJAX，获取后台传过来的JSON数据来构建系统树。

  - 配置Qurtaz进行后台的任务调度(定时清理缓存或者定点任务)

  - 整合Redis内存数据库进行Nosql的整合（可配置高可用的模式）

  - ajax封装JSON 请求的返回类型结果（数据格中日期格式化处理 ）

  - SpringMVC实现RESTFUL风格






- 完整版说明:
  - ​




TIPs：

- Oracle和mysql的区别，SQL语句的区别Dialect

Oracle的SQL语句叫做PL/SQL,可以编程的SQL语句，存储过程。DBA什么事情都通过数据库来完成。

互联网风格更偏向于在应用层和逻辑层处理逻辑编程。SQL语句只负责数据存取。

- 什么是数据库事务?

- 最好能讲清楚 什么是索引有什么用?

  索引本质上是个排序的列，每次索引列的变更都要重新进行排序，因为索引是排序好的列所以它查找是非常快速的（二分查找）

- 精通Mysql，Oracle，Sqlserver关系型数据库

  DDL：建库建表语句

  DQL：SELECT(DQL)查询语句

  多表连接JOIN和子查询

- 什么是非关系型数据库

  重要的对安全性和完整性 要求非常高就要使用关系型数据库

  比如说商品点赞功能 或者关注功能，最好在内存数据库里面完成。频繁改变的 重要性不是那么高的数据(量大，改变频繁，更关注于整体的量)

- WEB服务器 /Servlet容器：  都是能够处理动态资源

- HTTP服务器：一般只能处理静态资源，做请求转发，如果是静态资源我就自己处理，如果是动态资源就转发给WEB服务器比如说Tomcat处理。Nginx 反向代理（代理的是服务器）

- Spring框架（作为一个Bean对象的容器）

  - IOC/DI 控制反转和依赖注入：
  - IOC：每一个Bean对象都是单例，而且是延迟加载LazyInit，第一次使用的时候才会创建对象，由我们的Spring容器来控制对象的生命周期
  - DI：对象要依赖另外一个对象，在我需要时候自动创建对象并且注入进来
  - AOP面向切面编程：
    - Pointcut切点 ：是你要在那里做
    - Advice通知或者增强方法    ：是你要做什么
    - Pointcut加Advice = 切面Aspect
  - Spring怎么整合事务，一般在Service层来处理

- SpringMVC框架

  - 统一编程规范
  - 简化参数传递（直接帮我映射成对象）
  - 路由 /user/login 可以指定到方法那一级别，同一个类的路由前缀统一
  - 很容易整合JSON、XML等其他序列化协议
  - 很容易与Spring进行整合
  - 集成了RESTFUL风格

- Mybatis框架

  - 最简洁的ORM映射
  - 需要我们自己编写SQL，适合初学者练习
  - 生成器Generator自动生成Mapper映射文件和DAO和Model类

- OSI网络分层

  - 第三层 网络层    IP协议：我怎么找到你
  - 第四层 传输层    TCP协议/UDP协议： 我可以进行网络传输
  - 第七层 应用层    HTTP协议：               我传输的类型是什么





- 序列化 （将Java对象转化为字符串 或者将字符串转化为java对象）























































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