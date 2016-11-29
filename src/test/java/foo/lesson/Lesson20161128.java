package foo.lesson;

/**
 * ORM(ObjectRelationMapping)框架<p>
 * 
 * 1.为什么要用ORM框架？
 * 
 * #将数据库的数据映射成编程语言的Bean对象
 * #ORM框架帮你实现了JDBCUtils（数据库多种多样的）
 * 
 * 2.常见的ORM框架(Hibernate,Mybatis,SpringJDBC)
 * 
 * 
 * @author wyy
 * 2016年11月28日
 *
 */
public class Lesson20161128 {
	
     //什么是Spring框架？为什么要使用它
	//最大的优点  解耦合
	//怎么实现解耦合?
	//springcontext.xml  BeanFactory   ApliicationContext
	//IOC（控制反转）/ DI(依赖注入):
	
	//配置文件：
	//#1.XML配置
	//<bean<</bean> ctx.getBean()  
	
	//#2.注解扫描方式
	
	//1.扫描某个路径底下的所有包 <context:component-scan  base-package="com.ssf.bookshop.*"/>
	//默认不扫描  # 注解扫描
	//WEB的4个层： model - dao(DataAccessObject) - service业务层 - controller控制层(与前台交互，传递参数，控制页面跳转)
    //四个扫描注解：@Component  @Repository  @Service @Controller
	//相当于<bean id="userServiceImpl" class="">
	//	<property></property>
	//</bean>
	//注入注解：  @Autowired(按类别注入 Spring的注解)  @Resource(按名称 JDK的注解)
	
	//orm-第三方服务-与spring整合，在spring配置文件里面注册它
	//#1.
	/**
	 * 怎么使用Mybatis
	 */
	
	//#1.导入依赖包
	/*
    <!-- mybatis start -->
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis</artifactId>
		<version>3.1.1</version>
	</dependency>
	<dependency>
		<groupId>org.mybatis</groupId>
		<artifactId>mybatis-spring</artifactId>
		<version>1.2.3</version>
	</dependency>
	<!-- mybatis end -->
	*/
	//#2.在spring配置文件里面注册
//	<!-- 1.数据源配置 -->
//	<bean id="dataSource" 
//	class="org.springframework.jdbc.datasource.DriverManagerDataSource"> 
//	    <property name="driverClassName" value="${jdbc.driverClassName}" />
//		<property name="url" value="${jdbc.url}" />
//		<property name="username" value="${jdbc.username}" />
//		<property name="password" value="${jdbc.password}" />
//	</bean>
	
//	<!-- 2.配置MyBatis -->
//    <bean id="sqlSessionFactory" 
//	class="org.mybatis.spring.SqlSessionFactoryBean">
//        <property name="dataSource" ref="dataSource"/>
//        <property name="mapperLocations" value="classpath:/mappings/**/*.xml"/>
//    </bean>
//    
//   <!-- 3.扫描basePackage下的接口 -->
//    <bean id="mapperScannerConfigurer" 
//	class="org.mybatis.spring.mapper.MapperScannerConfigurer">
//        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
//        <property name="basePackage" value="com.ssf.bookshop.dao"/>
//    </bean>
	
	//#3.在你Mybatis的扫描路径下建立com.ssf.bookshop.dao
	//创建一个接口 IUserDao
	
	//#4.在你的映射文件配置路径下 创建一个Mapper映射文件
	//#name = com.ssf.bookshop.dao.IUserDao
	//IUserDa每一个接口的方法需要跟映射文件方法同名
	
	//#5把IUserDao注入进你的程序 就能调用DAO方法
	
	/**
	 * 怎么使用EhCache缓存
	 */
	//#1导入依赖包
	//#2配置文件ehcache-local.xml的配置文件
	//#3在Spring配置文件里面注册它
//	<!-- 缓存配置开始 -->
//	<!--扫描缓存注解-->
//    <cache:annotation-driven cache-manager="cacheManager"/> 
//    <!--  
//	<bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager"
//          p:cacheManager-ref="ehcacheManager"/>
//     -->
//    <bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager">
//        <property name="cacheManager" ref="ehcacheManager"/>
//    </bean>
//    
//	  <!--先把ehcache的配置文件给读取进来>
//    <bean id="ehcacheManager" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean"
//          p:configLocation="classpath:ehcache-local.xml"/>
//	<!-- 缓存配置结束 -->
	//#4使用注解 引入缓存的注解 @Cacheable
}
