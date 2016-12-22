# Socket

### C/S框架：QQ，微信，网络游戏，安卓

- client客户端 server服务器
- 需要建立Socket长连接

#### 优点

- 实时,稳定
- 安全性比较好

### 缺点

- 需要客户端

### B/S框架: JavaWeb应用，网站

- Browser浏览器（html5） Server服务器
- 只需要短连接,请求完连接就中断

#### 优点

- 基本只需要服务端程序servlet,工作量少 开发快 需要人员少。

### 缺点

- 安全性较差

- 体验一般要比APP差

  ​

# B/SWeb架构

### Web资源

- 静态资源:嵌套在HTML里面的资源：
  - 图片
  - CSS文件
  - JS文件
  - 。。。。。
- 动态资源:
  - JSP
  - Php
  - Asp
- 静态资源与动态资源的区别:
  - 浏览器可以缓存静态资源
  - 静态资源是不变的 只需要请求一次，一般在浏览器加载整个页面时自动帮你请求和缓存

### Web服务器

- Tomcat(Apache):

  - 优点：
    - 开源，免费
    - 社区会活跃
    - 进入市场比较早，有大量的用户基础
    - 这两个优点导致他应用广泛
  - 缺点：
    + 不支持JaveEE规范，功能比较弱（流行）
  - 应用范围：
    - SatrtUp公司，中小型企业，业务需求没有那么复杂，PV访问峰值没有那么大

- Ningx(俄罗斯人):为俄罗斯的facebook专门开门一个服务器系统 把他开源了

  - 优点：
    - 轻量级 高性能 反向代理
  - 缺点：
    - 太晚出来了，用户群偏少
    - 功能单一而强大 专注于HTTP请求 ， 峰值承载大概是Tomcat的5-6倍
  - 应用范围：
    - Tomcat的替代品，公司业务只有Http请求

- WebLogic(Oracle): WebService服务器  （GEO地图应用）

  - 优点：

    - 功能强大 安全


    - 支持JaveEE规范（大型WEB架构）

- 缺点：

    - 收费 
    - 笨重

- 应用范围：

    - 大型国企 有钱，性能和安全性要求较高

- JBOSS:

- Websphere（IBM）:


### HTTP服务器：

- Nginx:
- IIS:windows自带的HTTP服务器
- Apache HTTP：Apache专门的HTTP服务器

### 反向代理（负载均衡）：

​	-  正向代理 代理的是Client客户端，跟客户端绑定在一起

​	反向代理 代理的是Server服务端。

- 为什么要做反向代理：
  - 让多个服务器服务多个用户
  - 为了实现负载均衡：”分而治之“ ，除了提高响应，还能保证系统健壮性（防止某个服务器宕机，其他服务器能正常使用）

为什么Tomcat和Nginx绑在一起用来实现负载均衡：

- Tomcat：能处理JSP（servlet）等非Http请求和需要JAVA解释器的动态Web资源
- Nginx只服务HTTP请求
- 请求：
  - HTTP请求：直接交给-》Nginx（更加高效）
  - 非HTTP请求：Tomcat -》Nginx，先经过Tomcat处理再分发给Nginx

大数据：

- Map到多个机器处理（将数据交给多个服务器处理），处理完的数据Reduce回来（将处理回来的数据整合）

Cloud云服务（服务器资源浪费和管理的问题）：

- 12306，峰值很高，平常访问量又比较好

- 1.配置非常多的服务器：浪费大量资金和资源

- 2.配置刚刚多或者稍多一点的服务器数量：

   峰值无法处理

  ### 云解决方案

  - 购买大量的服务器：在空闲的把服务器空间分配出去
  - 常用的云服务：amazon云，谷歌云，阿里云，腾讯云，百度云

### IDE:

- Eclipse For JavaEE（开发JavaWeb应用）:
  - new -> project->新建 **Dynamic Web Project**
  - 导入**javax.servlet-api.jar**
- MyEclipse(自动帮你构建项目):
  - ​



### 在浏览器输入一个URL后具体发生了什么，背后有哪些技术步骤在支持

-  1.通过域名访问服务器  http://主机名:端口号/路径，例如：http://www.localhost:80/index.html

-  www.sina.com

-  2.浏览器查找域名 DNS(Domain Name System)域名服务系统：

   + DNS：
     - 作用：讲主机域名sina转为IP地址xxx.xxx.xxx.xx    localhost->127.0.0.1 本地IP地址
     - 为什么需要DNS
       - IP地址不好记忆，域名更有助于你记忆


+ DNS查找顺序：
    - 1.浏览器缓存
    - 2.系统缓存
    - 3.路由器缓存
    - 4.ISP DNS缓存
    - 5.DNS递归搜索：  .com顶级域名服务器到Facebook域名服务器

- 3.@浏览器给Web服务器发送一个Http请求 -浏览器

  ```json
  HTTP协议请求表头：

  GET /hello3/index.jsp HTTP/1.1
  Accept: application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, 
  Accept-Language: zh-CN,en-US;q=0.5
  User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0C; .NET4.0E)
  Accept-Encoding: gzip, deflate
  Host: localhost
  Connection: Keep-Alive

  请求行(请求方式 请求路径 协议/版本)
  多个请求头信息：头名称:头值
  空行
  请求体
  ```

  - mime格式

- 4.facebook服务永久重定向响应   

  - http://facebook.com  重定向到--> http://www.facebook.com/user_o=3


-   为什么需要重定向？(搜索引擎会把不同地址当成两/多个网站，其实他们是一个网站，减少冗余)

-   5.浏览器跟踪重定向地址

-   6.@WEB服务器“处理”请求 - servlet

    - 处理请求

-   7.@Web服务器发回一个HTML响应 HttpResponse - servlet

    ```json
      HTTP协议响应消息表头：

      *****HTTP/1.1 200 OK
      Server: Apache-Coyote/1.1
      Set-Cookie: JSESSIONID=48F75E08BD4DF3C3E72919543CBFDF81; Path=/hello3/; HttpOnly
      *****响应内容的MIME类型：Content-Type: text/html;charset=ISO-8859-1
      Content-Length: 646
      Date: Thu, 22 May 2014 06:45:26 GMT

      响应行(协议/版本 状态码 状态码的解析)
      响应头（key/value格式）
      空行
      响应正文
    ```

      ​

-   8.浏览器显示HTML - 浏览器（HTML/CSS/JS）

-   9.浏览器发送获取嵌入在HTML中的对象

    - 这些地址都要经历与URL请求类似的过程


-   一些静态Web资源（允许浏览器进行缓存）

-   10.浏览器发送异步(AJAX)请求（HTML/CSS/JS）

    - 为什么要有异步请求：整个页面加载进来，局部请求一些数据
    - Web2.0  页面显示完成后，客户端仍与服务器端保持着联系
    - 比如facebook发送 chat/buddy_list.php 获取你好友的在线信息

      ​



# Tips

### 安卓android： 

 Linux的内核 , JVM(底层用C交互)

- Linux操作系统+Java虚拟机JVM
- GUI图形界面：UI

 ### IOS ： 

- Object-C


- Cocoa最好的优点就是控件库

##### 3GUI库： 

- JAVA的控件库：AWT,Swing
- ​



##### 页游：

- Flash：ActionScript3
- Java :Applet

##### 脚本语言：

- JavaScript
- ActionScript
- ​



动态链接库(实现)：.dll \ .so \ .a

静态链接库(接口)：.Lib / .jar



### JVM虚拟机的

##### 优点：

- 跨平台，
- GC（垃圾清理）

##### 缺点：

- 性能较低,跟硬件多的地方不适合Java
- ​



## 垃圾收集 GC(Garbage Collection)

- 只要你把指针设为null，JVM会自动帮我们释放内存。


- 正常情况下，统一时间释放 ：隔几分钟释放一次
- 内存突然大量暴增，内存量到达某一个峰值的时候，释放内存

### 内存分配和释放

##### 分配内存：

- new的时候 

##### 释放内存:

```java
ClassA a = new ClassA;//等价于A a = new A()Class.forName("package.A").newInstance();

a= null;

Sysytem.gc();//手动调用Java GC，进行垃圾收集

```



### 面试

- 看考官心情

- 面试的没发挥好的地方，记下知识点，回去补缺补漏。

- 不要害怕失败

- ​

  const char*[10] 

  指针从1开始

//1输入域名

//2解析域名

//3发送请求

//4重定向

//5浏览器记录重定向

//6WEB服务器处理请求

//7响应，给浏览器发响应消息

//8浏览器显示

//9HTML嵌入的资源的请求（重新走一遍流程）

//10AJAX  (WEB2.0)



//发送请求

//处理请求

//响应

