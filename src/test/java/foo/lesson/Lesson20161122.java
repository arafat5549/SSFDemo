package foo.lesson;


/**
 * 分页算法<p>
 * 
 * 1.每页显示多少条记录  number(前台传过来)
 * 2.总页数                         totalPage(算出来 根据total和number)
 * 3.总记录数                     total(数据库查询)
 * 4.从几条记录开始          offset (前台传过来的)
 * 5.ArrayList      page(数据库查询 根据offset和number)
 * 6.现在是第几页             curPage()
 * 
 * 
 * @author wyy
 * 2016年11月22日
 *
 */
public class Lesson20161122 
{
	//修改页面-（命名规则）
	
	//1.确认有没有JavaBean对象（ABC），没有则创建(setter getter)
	   //无参构造器   model层
	//2.创建数据表
	//3.dao层 ABCDao  -  service层 ABCservice
	//4.单元测试
	//4.jsp页面（负责显示）/ Servlet（负责业务逻辑）
	//list.jsp  显示所有的对象 - 表格里面嵌套 修改和删除 （需要对象ID）
	//
	 
	
	
	
	//什么是MVC（ModelViewContoller）
	//Model
	//View层（JSP HTML）：只负责显示
	//Contoller层（Servlet）：负责业务逻辑
	
	
	//Maven
	
	
	 //Statment和PreparedStatement的区别：
	 //PreparedStatement的优点:预编译
	 //1.Sql语句更容易书写
	 //2.避免【sql注入】
	 //3.性能更高，数据库连接池可以利用pt缓存
	
	public static void main(String[] args) {
		//User.class.newInstance();//new User()
	}
	
}
