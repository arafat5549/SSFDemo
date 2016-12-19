package foo.lesson.summary;

/**
 * <b>权限系统</b><p>
 * 
 * 0.什么是B浏览器/S服务端,C客户端/S服务器？
 * 
 * 1.权限系统大概有几种做法?<br>
 * 2.常见的安全框架?<br>
 * 3.你怎么设计你的权限系统?<br>
 * 4.怎么进行多表连接join的用法 (一对多，多对一)?<br>
 * 
 * https/http的区别?
 * 1.访问https://
 * 2.服务器弹出一个页面要求你安装一证书CA
 * 3.验证数字证书，发送数字证书给某宝
 * CA：数字签名(RSA)
 * --1.某宝的公钥
 * --2.机密算法
 * --3.HASH算法（秘钥）
 * --4.证书到期时间?
 * --5.私人秘钥
 * 4.我发请求有人篡改我的证书怎么处理 ？
 * 你把私人秘钥生成的HASH码负在你的证书后面，保证是你本人的操作
 * 5.
 * Hash算法：MD5,SHA,Base64
 * 数字签名: RSA(非对称加密、有分公钥和秘钥)
 * 
 * 例子：
 * http://blog.csdn.net/catoop/article/details/50520958
 * 数字签名是什么?
 * http://www.ruanyifeng.com/blog/2011/08/what_is_a_digital_signature.html
 * @author wyy
 * 2016年12月9日
 *
 */
public class LessonSecurity {
	
	/**
	 * 1.权限系统大概有几种做法?
	 */
	//1.URL拦截：粗粒度 - filter(过滤器：拦截你定义的URL，如果你没有权限，就会返回登录或者首页)
	//2.按钮级别的拦截：前台需要对没有权限的按钮做一些特殊处理,后台需要对按钮对应的URL进行拦截。
	//#为什么我们的JSP页面需要放置在WEB-INF下? 
    //防止通过全路径访问JSP，不通过我们映射的URL路径。
	/**
	 * 2.常见的安全框架?
	 */
	//Shiro:密码校验，安全框架 
	//Spring Security:
	
	//如果你要实现细粒度的权限的话最好使用安全框架Shiro,（除非你本公司自身提供了一套安全框架）

   /**
    * 3.你怎么设计你的权限系统?
    */
	//Model
	//1.User
	//2.Role
	//3.Menu
	
	//1.p_user:
	
	//2.p_role:
	//3.p_menu:
	//4.p_user_role:一对多
	//5.p_role_menu:
	
	//生成测试数据
	/**
	 * 4.怎么进行多表连接join的用法 (一对多，多对一)
	 */
}
