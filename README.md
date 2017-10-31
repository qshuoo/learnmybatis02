# learn mybatis
再次学习mybatis
## 概述
MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

## 测试
### 环境搭建
导入mybatis相关包。和数据库驱动包

	<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis</artifactId>
	    <version>3.3.0</version>
	</dependency>
	
	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.24</version>
	</dependency>

### 编写核心配置文件

编写mybatis_config.xml	
对文件和路径无特别要求

	<!-- 添加约束 -->
	<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
	
	<configuration>
		<!-- 引用外部属性文件 -->
		<properties resource="jdbc.properties"></properties>
		<!-- 配置MyBatis运行环境 -->
		<environments default="development">
			<!-- 配置专门用于开发过程的运行环境 -->
			<environment id="development">
				<!-- 配置事务管理器 -->
				<!-- <transactionManager type="JDBC" /> -->
				<!-- 配置数据源 -->
				<dataSource type="POOLED">
					<property name="username" value="${jdbc.username}" />
					<property name="password" value="${jdbc.password}" />
					<property name="driver" value="${jdbc.driver}" />
					<property name="url" value="${jdbc.url}" />
				</dataSource>
			</environment>
		</environments>
	</configuration>


### 创建表等准备操作

### 创建mapper.xml文件

例:

	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.qshuoo.pojo.User">
		<select id="listUsers" resultType="com.qshuoo.pojo.User">
			select * from user
		</select>
	</mapper>

在mybatis核心配置文件中配置映射文件

	<mappers>
		<mapper resource="com/qshuoo/pojo/UserMapper.xml" />
	</mappers>
	
测试

	/**
	 * 查询所有用户 查询成功，返回userlist
	 */
	@Test
	public void listUsers() {

		List<User> users = session.selectList("com.qshuoo.pojo.User.listUsers");
		System.out.println(users);

	}
	
	
### 使用接口注解

接口

	/**
	 * 查询所有学生
	 * @return
	 */
	@Select("select * from student")
	List<Student> liststudents();

在核心配置文件中配置

	<mapper class="com.qshuoo.pojo.StudentInterface" />

测试类

	@Test
	public void listStudents() {
		StudentInterface si = session.getMapper(StudentInterface.class);
		List<Student> students = si.liststudents();
		System.out.println(students);
	}

优点：避免xml映射时，函数参数命名空间过长
缺点：接口方法注解会有过长的情况


### 使用mapper.xml+接口的方式

1.	定义pojo的接口与方法	
2.	在mapper.xml中定义命名空间是接口的包名加类名，方法的id与接口中的方法对应
3.	在核心配置文件中映射mapper.xml

### 动态sql

Mybatis提供了动态 SQL 元素，这些元素的使用方式和 JSTL 或其他类似基于 XML 的文本处理器标签相似。
总体说来mybatis 动态SQL 语句主要有以下几类:
1. if 语句 (简单的条件判断)
2. choose (when,otherwize) ,相当于java 语言中的 switch ,与 jstl 中的choose 很类似.
3. trim (对包含的内容加上 prefix,或者 suffix 等，前缀，后缀)
4. where (主要是用来简化sql语句中where条件判断的，能智能的处理 and or ,不必担心多余导致语法错误)
5. set (主要用于更新时)
6. foreach (在实现 mybatis in 语句查询时特别有用)


