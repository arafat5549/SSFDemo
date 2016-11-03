package foo.lesson;

/**
 * 1.分页算法
 * 
 * #1.总记录数total(数据库查)
 * #2.从哪条记录开始offset
 * #3.每页显示多少条记录limit
 * #4.显示的当前页List 根据offset，limit来查找(数据库查)
 * #5.总页数totalPage     total%limit ==0 ? total/limit :  total/limit+1
 * #6.当前页数pageNum     1 <= total/limit+1  <=  totalPage
 * 
 * 
 * 往前台发送(total,totalPage,pageNum,List,Page)
 * 
 * 分页栏
 * 首页     pageNum=1  (没有上一页标签)
 * 中间页 pageNum < totalPage
 * 尾页    pageNum=totalPage (没有下一页标签和尾页标签)
 * 
 * 数据库方法
 * #1.取记录数 
 * #2.根据offset，limit来查找List对象
 * 测试数据库方法
 * 
 * 显示List页面  listUser.jsp[ Action的名字 --> 方法名 ](struts.xml)
 * 
 * 添加分类功能
 * 1.Category 确认JavaBean （id ,name）
 * 2.建表
 * 3.创建并关联Category.hbm.xml（app-base.xml）
 * 4.CategoryService,CategoryDao测试数据库方法(app-bean.xml利用依赖注入)
 * 5.建立jsp，关联Action struts.xml
 * 
 * 
 * 常见错误：
 * 1.事务问题
 * 2.名称不匹配
 * 3.你关联了两个DAO 只注入了一个DAO
 * 
 * @author wyy
 * 2016年11月2日
 *
 */
public class Lesson20161102 {

}
