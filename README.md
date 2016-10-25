### Maven(推荐) / Gradle / Ant:

0.下载一个Maven,下载地址如下：

http://mirrors.hust.edu.cn/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip

1.设置环境变量到bin路径(命令行运行mvn -version查看是否安装成功)

2.要使用Eclipse的话需要下载Eclipse的Maven插件:

- http://download.eclipse.org/technology/m2e/releases/1.4
- Eclipse- Help->Install New Software 输入上面那个地址


- 插件名称:[M2E - Maven Integration for Eclipse]

3. 1. 常用的Maven命令(到pom.xml所在的目录运行)
   2. Mvn install
   3. Mvn Test
   4. Mvn eclipse:eclipse

4. 安装完插件后，Import ->ExsistingMavenProject导入工程（POM.xml文件的那个路径）

5. - src/main/java:源代码目录
   - src/test/java: 自动运行所有的单元测试类
   - src/main/resources:配置文件,classpath
   - src/main/webapp:WEB工程多一个这个目录

6. 阿里的国内Maven仓库镜像不然下载很慢：

   maven安装路径 \conf\settings.xml <mirrors>标签对下面

7. ```xml
    <mirror>
         <id>alimaven</id>
         <name>aliyun maven</name>
         <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
         <mirrorOf>central</mirrorOf>        
       </mirror>
   ```

8. 其他常用打包工具:


- Apache工具包（不推荐）：不好用
- Gradle（推荐度 4星）:安卓官方打包工具