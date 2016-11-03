package foo.lesson;

/***
 * Struts2<p>
 * 
 * 拦截器<br>
 * ValueStack<br>
 * ActionSupport<br>
 * Validation验证机制<br>
 * Strtus.xml配置方式<br>
 * 国际化<br>
 * Struts2和Spring整合<br>
 * 分页算法<br>
 * Jqeury(简单)<br>
 * 
 * @author wyy
 * 2016年10月31日
 *
 */
public class Lesson20161031 {
	/**
	 * 拿到一个新的工程首先要做的是：
	 * 1.查看数据库连接
	 * 2.缺哪些表,创建表
	 * 3.开启Web服务器
	 * 4.
	 */
	//struts1 -》 struts2(xwork2)
	//
	
    /**
     * 1.webmvc框架的作用
     * #1.请求参数到Java类型的数据绑定
     * #2.验证数据
     * #3.访问业务逻辑 SERVICE
     * #4.访问数据层  dao
     * #5.层显表示层（HTML等）
     * #6.配置所有的跳转路径
     * 
     * #6.提供国际化和本地化等
     * 
     */

	//没有使用框架servlet
	//DOGET(REQUESt ,RESPONSE)
	//struts1
	//public String addBook(REQUEST ,RESPONSE)
	//<action name="add" class="book" method="addBook">
	//<result name="success">/index.jsp</result>
	//<result name="error">/error.jsp</result>
	//</action>
	//int money = request.getParammeter("money")
	
	//struts2更进一步
	//Action作为JavaBean，他的属性值就是setter和getter方法
	
	
	//$struts2：常见流程
	//1.form表单  /XXX.action
	//2.在struts.xml配置XXX.action          ： <action name="XXX" class="book" method="addBook">
	//3.在application-beans.xml（spring配置）里面配置class="book" book对应一个Bean(依赖注入)
	//4.在book对应的类中填写方法 方法的返回值在XXX.action中配置
	
	
	//.web开发常见流程  （用户模块-注册功能）
	//1.JavaBean 字段跟属性  setter和getter
	//2.建表 - XXX.hbm.xml - 配置application-base.xml
	//3.页面JSP  - Input 、 action
	//4.$struts2：常见流程
	//5.Service - 配置application-beans.xml
	//6.DAO     - 配置application-beans.xml
	
	//1.数据库相关
	//1.jdbc.propeties 配置文件
	//2.查看你本地的数据库配置跟jdbc.propeties匹配不匹配
	//3.application-base.xml -> DataSource数据源(JDBC,DBCP) 需要JDBC配置
	//4.sessionFactory(Hibernate,Mybaits)  需要DataSoource
	//5.事务配置 需要sessionFactory配置
	
	//2.
	
}
