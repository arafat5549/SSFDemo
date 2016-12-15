package foo.lesson.summary;

/**
 * 第三方技术框架总结<p>
 * 
 * 1.安全框架：Shiro/Spring Security<br>
 * 1-1权限管理<br>
 * 2.任务调度器: Quartz/SpringTask<br>
 * 3.验证框架:Hibernate-validation<br>
 *   前台验证框架:Jquery-validation<br>
 * 4.布局框架：SiteMesh/Tiles
 * 5.搜索框架：Apache lucene/Apache solr/Elasticsearch 
 * 6.缓存框架: Ehcache / Redis / MemCache
 * 7.数据库连接池：Alibaba Druid/dbcp/C3P0
 * 8.工作流引擎：Activiti/JBPM
 * 9.消息队列机制:Apache ActiveMQ/Apache Kafka 
 * 10.网络编程模块：netty
 * 11.文档处理：Apache POI<br>
 * 
 * <p>前台框架</p><br>
 * JS框架：JQuery 1.9<br>
 * CSS框架：Twitter Bootstrap 2.3.1。
 * 客户端验证：JQuery Validation Plugin 1.11。
 * 树结构控件：jQuery zTree   / Jquery EasyUI
 * 数据表格：jqGrid
 * 日期控件： My97DatePicker
 * 
 * 富文本：CKEcitor
 * 文件管理：CKFinder
 * 动态页签：Jerichotab
 * 手机端框架：Jingle
 * 对话框：jQuery jBox
 * 下拉选择框：jQuery Select2<br>
 * 
 * <p>分布式系统</p>
 * 100.分布式的概念-微服务<br>
 * 集群的概念<br>
 * 
 * 
 * <p>编码规范</p>
 * 101.文件存放规范，命名规范<br>
 * 102.UML统一建模语言<br>
 * 
 * 一些名词概念：
 * 分布式服务框架(DSF)DistributeServiceFrameWorks
 * CSC:信息技术和商业服务提供商
 * CRM(Cluster Resource Management):集群资源管理模块 
 * CRM(Customer Relationship Management):客户关系管理
 * 
 * @author wyy
 * 2016年12月6日
 *
 */
public class LessonFrameWorks {
	/**
	 * Security安全框架Apache-Shiro
	 * 
	 */
	//web开发有四个作用域：Page,Request,Session,Application
	
	//md5加密方式（不要使用明文密码）
	
	//1.加密你的用户密码
	  //原始密码：123456  加密密码：sssssssssssss
	  //MD5加密 - 不可逆的编码  【前台加密过来，后台也要加密你的原始密码】
	//2.安全机制（过多少分钟自动退出）
	  //默认的登录用户会在Session域里面
	  //Cookie存在本地你的文件片段，把你的用户名密码存在里面（为什么我要要做加密的原因）
	  //
	//3.管理你的权限
	  //权限表sys_menu：提供了一种更细粒度的锁，具体到按钮那一级别
	  //角色表sys_role: 一个角色拥有N个权限（权限的集合）
	  //用户表sys_user: 一个用户可以拥有多种角色
	  //关联表sys_role_menu:某个角色有几个权限 ：roleid menuid
	  //关联表sys_user_role: userid , roleid
	
	  //【部门表sys_office】:一个部门拥有多种角色
	  //关联表  sys_role_office:
    //Shiro怎么管理权限?
	//后台
	//前台
	
	//常见的安全框架：1.SpringSecurity（比较繁琐，不推荐使用） 2.Shiro（较为复杂的
	//你要实现细粒度的权限功能，粗粒度可以使用filter拦截URL的方式实现）
	
	//怎么从零创建一个权限模块
	//
	
	/**
	 * UML统一建模语言
	 */
	//为什么要使用UML
	//软件工程的概念：需要不同部门成员之间协作，需要一个统一的图例描述
	
	
	//UML相关的软件：
	//收费商业软件：Rose Rational
	//开源UML软件： staruml
	
	//1.看的懂系统架构图
	
	
	/**
	 * 100.分布式的概念：（微服务-一个大的业务分拆成为若干个小的业务，部署在不同的服务器上）
	 */
	//1.只有单服务器单应用程序
	//2.多服务器多应用程序
	//Message消息机制JMS - 应用程序之间通过消息传递
	//Apache-ZooKeeper 分布式：
	//Dubbo-》调用ZooKeeper
	//Mudo -》
	
	/**
	 * 100.集群的概念：（同一个业务，部署在多个服务器上）
	 */
	//负载均衡 
	//1.Nginx怎么跟WEB服务器协作？
	//2.Nginx的反向代理的概念？
	/**
	 * 102.微服务
	 */
	//#序列化常用第三方工具
	//hessain:使用二进制RPC协议传输数据，对象必须进行序列化，实现Serializable 接口
	
	/**
	 * 怎么解决BUG？
	 */
	//1.去百度(百度解决不了怎么办)
	//2.去Google（技术类的Google的准确率）
	//3.去StackOverFlow，全英文，绝大部分的错误都会在里面得到解答
	//4.常逛的技术社区：开发者头条（理解下技术顺便吹下牛P） / 知乎（吹牛P之余顺便了解下技术） / csdn新手比较友好
	//5.看论文（全新的技术）
	//6.GitHub：你们先阶段的开源代码都能在里面找到只要你们会找
	
	//专业人士素养：
	//1.不要用QQ邮箱
	//2.浏览器尽量使用火狐内核(Gecko)/Chrome:
	//3.Linux常用的命令:
	//4.经常整理你的文件结构
	//5.起名一定要有意义：admin/
	//6.打字还不是很熟练(自己有一套比较熟练的打字方法)
	//7.有条件开个VPN
	
	/**
	 * 你们项目中遇到的问题？你们怎么解决的？
	 */
	//自己尝试去实现其中的一个小的功能：
	//0.jquery-valiadation实现前台验证
	//1.jquery-ztree组件 实现树结构
	//稍微记录一下，Excel表格
	
	
	//为什么Jquery火起来？
	//对浏览器做Hack ，前台大概得有一半的精力得为IE6做Hack
	//Jquery（JS框架）为每个浏览器做Hack
}
