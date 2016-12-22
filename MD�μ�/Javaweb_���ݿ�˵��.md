### 安装Tomcat

- 设置BIN目录到Path路径
- 新建环境变量 CATALINA_HOME 到你的Tomcat根目录
- cmd命令行 startup 开启Tomcat服务 (shutdown关闭服务)
- 进入localhost:8080



### MyBaits

- 导入Mybaits.jar （Java工程右键BuildPath）

- 1建立数据库对应的【JavaBean】类（跟你的数据库表结构一一对应）

- 2建立Mybaits配置文件conf.xml

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC" />
              <!-- 配置数据库连接信息 -->
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver" />
                  <property name="url" value="jdbc:mysql://localhost:3306/downloadDB" />
                  <property name="username" value="root" />
                  <property name="password" value="" />
              </dataSource>
          </environment>
      </environments>

      
      <mappers>
        	 <!-- 有哪些映射表 -->
           <mapper resource="PersonMapper.xml"/>
       </mappers>
  </configuration>
  ```

- 3建立【JavaBean】类对应的映射XML (默认为类名+Mapper.xml)

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!-- 为这个mapper指定一个唯一的namespace，namespace的值习惯上设置成包名+sql映射文件名，这样就能够保证namespace的值是唯一的
  例如namespace="me.gacl.mapping.userMapper"就是me.gacl.mapping(包名)+userMapper(userMapper.xml文件去除后缀)
   -->
  <mapper namespace="PersonMapper">
      <!-- 在select标签中编写查询的SQL语句， 设置select标签的id属性为getUser，id属性值必须是唯一的，不能够重复
      使用parameterType属性指明查询时使用的参数类型，resultType属性指明查询返回的结果集类型
      resultType="me.gacl.domain.User"就表示将查询结果封装成一个User类的对象返回
      User类就是users表所对应的实体类
      -->
      <!-- 根据id查询得到一个user对象 -->
      <select id="getUser" parameterType="long" resultType="com.sff.ssh.Person">
          select * from users where user_id=#{user_id}
      </select>
      
      <select id="getUsers" resultType="com.sff.ssh.Person">
          select * from users
      </select>
      
  </mapper>
  ```

- 创建Mybaits Session:

- ```java

  ```

- ​





### MySql

> > Mysql就是一个关系型数据库DRBS
> >
> > - 常用关系型数据库
> >   + **SqlServer**  优点：windows内置 装机量最大 和 微软的推广语言C#完美整合 缺点：社区不活跃 
> >     - 应用范围：C#你用微软的语言和工具就尽量用微软的数据库
> >   + **Orcale**       优点：最安全 Sql效率最高     缺点：贵，复杂，系统过于庞大
> >     - 应用范围：银行，金融(国家大型企业) ，业务变化不会那么频繁，对数据安全性要求特别严格
> >   + **Mysql**        优点：便宜，社会活跃开源，灵活跟各个框架对接   缺点：未建立索引的大数据量表查询效率比较低（500W）  
> >     -  应用范围：中小型创业公司，特别是web方向的
> >   + **PosterSql**  优点：
> >
> >
> > - 关系型数据库优点： 
> >
> >   + 1.数据的安全性和一致性
> >   + ​2.Sql复杂查询
> >
> >   > 关系型数据库的难点：
> >   >
> >   > 1. Sql语句  特别是复杂的Sql语句
> >   >    - 推荐书籍： **[SQL必知必会]**   
> >   > 2. ​
> >
> > Nosql非关系型数据库 （文档+索引）：
> >
> > - 常用Nosql
> >
> >   + **Mongodb**  文档型存储    大数据常用数据库
> >
> >     - 应用范围：大数据相关，特别是**Hadoop**的开源框架应用数据库
> >
> >
> >     - 优点：Hadoop的Java的大数据开源框架（**Lucene**内置搜索引擎框架和大数据搜索框架**EL**的作者）Google公司开源三篇论文  MapReduce  / GoogleFileSystem / 
> > + **Redis**         键值对存储     多作为内存数据库
> >
> >      - 应用范围：缓存管理 ， 为数据库外嵌一套缓存管理系统 
> >
> >     - 优点：键值对存储 特别适合做缓存管理！缓存实现原理Key：文本路径-V：文本内容
> >
> >       - 提高网页读取效率，避免硬盘IO瓶颈（）
> >
> >       ​
> > - Nosql的优点：
> >   + 1.Document-Oriented用文档来组织数据不需要严格的结构。
> >   + 2.High performace 高性能（文档结构简单，建立简单索引只做查询效率高）
> >   + 3.High  Avalibility高可用性（文档结构简单，任何类型的数据都可填充，可用性高）
> >   + 4.易扩展性（文档结构简单 没有数据库复杂的Scheme行列存储结构，易于扩展）
> >   + 4.易扩展性
> >   + 5.授权费便宜，几乎免费
> >
> >
> > - 什么时候会用到Nosql
> >
> >   + 你对数据安全性不敏感, 日记文件来记录用户操作和行为
> >
> >   大数据：
> >
> >   - 海量数据，几十T的数据量，
> >   - 非常多的屌丝机器，一起处理数据Reduce，最后再整合起来Map （分而治之）
> >   - 有哪些工具：Hadoop，Mongodb  ，Linux

- 设置 mysql的安装路径(到bin目录) 到 Path路径

- cmd命令行安装mysqld服务  mysqld -install mysql   [Win10用户可能需要管理员运行cmd命令行获取权限]

- 开启mysqld服务：net start[stop] mysql 

- > **mysql数据库设置:**
  > **解决中文乱码**
  > **修改my.ini**
  >
  > **[client]**
  > **default-character-set=utf8**
  >
  > [mysql]  
  > default-character-set = utf8 
  >
  > **[mysqld]**
  > **default-storage-engine=INNODB**
  > **character-set-server=utf8**
  > **collation-server=utf8_general_ci**
  >
  > ​

- 安装NaviCat 数据库可视化GUI操作

- 导入Mysql连接库： mysql-java-connector.jar

- 创建数据库语句Sql语言

  ```sql
  drop database if exists downloadDB;
  create database downloadDB
  CHARACTER SET 'utf8'
  COLLATE 'utf8_general_ci';
  use downloadDB;

  CREATE TABLE users(user_id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),password VARCHAR(20), age INT,creaeTime VARCHAR(20));
  INSERT INTO users(name,password, age) VALUES('孤傲苍狼', '123456',127);
  INSERT INTO users(name,password, age) VALUES('name1', '123456',37);
  INSERT INTO users(name,password, age) VALUES('name2', '123456',47);
  INSERT INTO users(name,password, age) VALUES('name3', '123456',57);
  INSERT INTO users(name,password, age) VALUES('name3', '12345678',27);
  ```

- DBUtils的创建数据库连接

  + ```java
    public static Connection openConnection() throws SQLException, ClassNotFoundException, IOException 
    	{
    		if (null == con || con.isClosed()) 
    		{
    			String db_driver = "com.mysql.jdbc.Driver";
    			String db_url    = "jdbc:mysql://localhost:3306/downloadDB";
    			String db_username = "root";
    			String db_password = "";
    			//调用Class.forName()方法加载驱动程序
    			Class.forName(db_driver); 
    			//调用DriverManager对象的getConnection()方法，获得一个Connection对象
    			con = DriverManager.getConnection(db_url, db_username,db_password);
    		}
    		return con;
    	}
    ```

    ​

    ​

### Tips

- Eclipse自动补全：Windows->perferences->Java->editor->Content Assist



### 作业

构建学生表student,在Mybaits建立StudentMapper.xml映射

有插入 insert into student(name,classNo) values(?,?);

查询  select * from student where name =? and classNO=?;

- Student  
  + id  Int 主键 自动增长
  + name vatchar(20)
  + classNo int

   show variables like '%char%';