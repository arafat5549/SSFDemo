package foo.lesson;


/**
 * 
 * 大批量数据的处理
 * 完整的分页算法模块
 * 数据库事务transaction和批处理batch
 * 
 * @author wyy
 * 2016年11月23日
 *
 */
public class Lesson20161123 {
  //回顾昨天的内容
	
	//1.WEB开发  流程 UPDATE模块
	
	//2.Statement和PreparedStatement的区别？
	//PreparedStatement为推荐的方式
	//#1防止sql注入
	//#2SQL语句更容易拼写
	//#3SQL语句可以被缓存
	
	//3.什么是SQL注入？怎么预防他？
	//最简单SQL注入："=1' OR '1=1"
	//改变SQL语法的逻辑
	/**
	 * 完整的分页算法模块
	 */
	//4.分页算法
	//#1offset 从几条记录开始
	//#2limit  每页显示多少条
	//#3total  总记录数
	//#4ArrayList  当前页的List对象
	//#5totalPage  总页数 : total%limit == 0 ? total/limit : total/limit+1;
	//#6pageNum    当前页:  1 <= offset/limit +1 <= 5totalPage
	//会把上面留个元素作为Page对象的字段 
	
	
	//1.所有的页标签标签都列出来 
	//for(int i=1;i<=totalPage;i++) //BEGIN=1 END=totalPage
		//页标签==当前页
	//2.首页  offset=0
	//3.尾页   offset=totalPage%limit==0 ? (totalPage)*limit : (totalPage-1)*limit
	//4.上一页标签  在第一页的时候没有上一页标签
	//5.下一页          在最后一页的时候没有下一页标签
	//6.处理大批量数据 
	//#1.显示多少条标签pagenationSize=10
	//#2.BEGIN = Math.Max(1,pageNum - pagenationSize/2);
	//#3.END   = Math.Min(totalPage, BEGIN + (pagenationSize -1));
	   //END   = Math.Min(totalPage, pageNum + pagenationSize/2);
	
	
	//total=10000记录   limit=10    totalPage=1000
	//1,2,3,4,5...998,999,1000
	//5,6,7,8,9...998,999,1000
	
	
	/**
	 * 大批量数据的处理
	 */
	//事务：
	//什么是事务?为什么要使用事务？他的优点和缺点？
	//保障数据的一致性和完整性 
	//ACID特性
	//Atmoic原子性： 操作没法再分割
	//（Consistency）一致性：数据执行sql操作后需要一直
	//（Isolation）隔离性:事务之间互相不影响
	//持久性（Durability）: 内存里面操作，持久化就是落到本地（以文本方式储存到硬盘里）
	
	//缺点：事务使用不当会造成性能的极大瓶颈
	
	
	//#数据库连接 默认开启事务，对每一条是是sql都会开启
	
	//批处理Batch 处理大批量数据
	//#1.关闭自动事务
	//#2.执行批处理
	
	
}
