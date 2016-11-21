package foo.lesson;

/**
 * Tips:自己选择并搭建一个WEB框架来实现登录注册功能?
 * 
 * @author wyy
 * 2016年11月8日
 *
 */
public class Lesson20161108 
{
	//1.评估选择的技术（对几种常用的框架有一定了解）
	//#不用框架：小项目，没有所需的资源（）
	//#使用Spring+ORM: 省JDBC操作，
	//#Hibernate和Mybaits：不需要SQL操作，对SQL语法不是很熟悉建议用hibernate
	//Spring+ORM+webMVC：注意struts2怎么配置？ web.xml 配置spring的加载，struts.xml不能改名
	
	//2.选择XML配置和注解方式：
	//XML配置:（优点：结构清晰，修改方便，统一管理修改方便 缺点：比较繁琐）
	//注解：（优点：简单，灵活  缺点：结构散落在整个工程里  ，查找bug和修改起来比较困难）
	//Spring的Bean用扫描的方式
	//ORM我习惯XML配置
	//struts2一定要用XML配置方式
	
	//3.Maven 组织这个工程
	//Maven（1.管理依赖包，工程特别容易管理 2.工程发布和转移起来特别方便  3.第三方工程也是以Maven组织的，借鉴起来更加方便）	
	//保存常用的POM文件: SSH(古老方式，新的方式), SSM / 
	//保存常用的starter工程（配置文件）
	
	//4.第三方组件
	//缓存 ehcache # 数据库连接池dbcp,c3p0,druid 
	
	//5.常用的数据库优化？
	//1.fetchsize 以空间换时间
	//2.数据库连接池
	//3.数据库缓存：减少数据库连接
	//4.STATEMENT 和 prearedstatement ： 预处理SQL语句
	 
	
	//6.遇到问题你怎么解决问题?
	//1.jdk的版本问题
	//#32 64区别？/JDK版本问题1.6/1.7
	//2.拷贝POM的时候
	//工程名字不匹配
	//<packing>war</packing> war打包的需要需要webapp/WEB_INF/web.xml
	//3.test方法错误
	//MVN INSTALL会运行mvn test 会执行test目录下所有带@Test标签的方法
	//1#spring.xml配置出错
	//2#数据库和数据库表没有 -
	//3#ORM框架的配置
	//4#事务(默认的方法是需要事务的)
	
	//iBatis / MyBatis t同一个东西
	//struts的Action类的scope要配置为 prototype
}
