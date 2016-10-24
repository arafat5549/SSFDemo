### Maven(推荐) / Gradle / Ant:

0.下载一个Maven

1.设置环境变量到bin路径

2.要使用Eclipse的话需要下载Eclipse的Maven插件

- M2E - Maven Integration for Eclipse

3. 1. Mvn install
   2. Mvn Test
   3. Mvn eclipse:eclipse

4. 安装完插件后，Import ->ExsistingMavenProject

5. 到POM.xml文件的那个路径

6. - src/main/java:源代码目录
   - src/test/java: 自动运行所有的单元测试类
   - src/main/resources:配置文件,classpath
   - src/main/webapp:WEB工程多一个这个目录

7. maven安装路径\conf\settings.xml

8. ```xml
    <mirror>
         <id>alimaven</id>
         <name>aliyun maven</name>
         <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
         <mirrorOf>central</mirrorOf>        
       </mirror>
   ```

9. ​



Apache工具包：

Google Guava工具包：

ANI：AndroidNativeInterface

//Gradle新的打包工具 Groovy