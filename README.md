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
	



