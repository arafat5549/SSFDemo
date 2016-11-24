package foo.lesson;

/**
 * 上传文件<p>
 * 
 * BOOK对象和数据表要多添加一个字段img：表示去哪里读这个文件
 * 
 * 1.JSP页面
 * #1.method必须post方法 - form表单格式来做
 * #2.<input type="file" name="img"/>
 * #3.enctype="multipart/form-data"
 * 
 * 2.后台方法
 * #1.无法使用req.getParameter()
 * #2.引入commons-fileUpload,commons-io
 * 
 * #3-1.factory = new DiskFileItemFactory() 从硬盘读取
 * #3-2.new ServletFileUpload(factory)
 * #3-3.List<FileItem>
 * #
 * 3.怎么处理图片
 * #1.文件名要唯一 (UUID,当前时间)
 * #2.当前工程的upload  创建文件夹
 * #3.写入硬盘
 * #4.更新book的img字段
 * 
 * #更新BOOK对象
 * 
 * 
 * 1.架构一个WEB框架
 * SSH(Spring框架[Controller] - Struts1/2[View] - Hibernate[数据库-Model])
 * 
 * 日记包：
 * #log4j
 * #commons-logging
 * #slf4j:日记的接口包,必须实现我这个接口
 * 
 * 
 * @author wyy
 * 2016年11月24日
 *
 */
public class Lesson20161124 {

}
