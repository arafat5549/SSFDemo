Spring



SpringAop

- 集成了AcpectJ
- ProxyFactory代理工厂:
  - JVM动态代理
  - 第三方代理类库CGLIB


IOC/DI 控制反转/依赖注入：

- 1.BeanFactory和ApplicationContext
- ​

数据注入

1.接口注入 2.setter注入 3.构造器注入





Spring各个Jar包的作用：

- spring-core.jar: 
  - 核心包 提供Bean，BeanFactory（控制反转）
- spring-beans.jar:
  - 为核心包服务，提供读取Bean的功能
- spring-context.jar:
  - 上下文包，为核心包做了更高一层的分装
- spring-context-support.jar:
  - 上下文扩展包，用到spring-web的时候需要导入
- spring-jdbc.jar:spring 
  - 封装的JDBC连接包

、以上相当于IOC （InverseOfController）控制反转/DI（DependentInject）依赖注入的部分

- spring-expression.jar:
  - spring的表达式，为扫描限定范围

### Spring的依赖包：

- 日记相关：


- Log4j: 第三方日记
- Apache Common-logging: 第三方日记
- slf4j:用来管理 第三方日记 
- 数据库相关：
- mysql的连接包：mysql-connector-java
- mybaits:mybatis.jar
- spring-mybaits : mybatis-spring.jar
- Apache-Common通用功能包:
- common-io：
- common-logging:
- common-beanutils:





1.导入包

2.配置文件

- spring的配置文件（spring-mybaits.xml）-总管
  - 1.<bean id="xxx" class="xxx.xxx"></bean>
  - 2.自动扫描功能 <context:component-scan base-package="com.ssf.*"/>
  - 3.注解 标记哪个Bean会被扫描
  - 4.//@Component                    //组件bean
    //@Repository(value="user") //DAO层组件
    //@Service(value="user")       //Service层组件
- jdbc.properties 数据库配置文件
- ​