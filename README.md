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
	

### 测试curd

mapper.xml

	<!-- 查询所有用户 -->
	<select id="listUsers" resultType="com.qshuoo.pojo.User">
		select * from user
	</select>
	
	<!-- 根据id查询用户 -->
	<select id="getUserById" resultType="User" parameterType="int">
		<!-- 当参数只有一个时，只需参数类型对应，sql语句中变量名称随意(#{}中内容) -->
		select * from user where id = #{id}
	</select>
	
	<!-- 添加用户 -->
	<insert id="saveUser" parameterType="user">
		<!-- 若传递过来的参数是一个对象时，sql语句中的变量名必须与对象属相相对应 -->
		insert into user values (#{id}, #{name}, #{password})
	</insert>
	
	<!-- 删除用户 -->
	<delete id="deleteUserByNameAndPwd" parameterType="map">
		<!-- 若传递过来的参数时map,sql语句中的变量名必须与map中key的名相同 -->
		delete from user where name = #{name} and password = #{password}
	</delete>
	
	<update id="updateUserNameById" parameterType="User">
		update user set name = #{name} where id = #{id}
	</update>

test.java

	@Before
	public void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis_config.xml");

		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		session = factory.openSession();
	}

	/**
	 * 查询所有用户 查询成功，返回userlist
	 */
	@Test
	public void listUsers() {

		List<User> users = session.selectList("com.qshuoo.pojo.User.listUsers");
		System.out.println(users);

	}

	/**
	 * 根据id查询用户，查询成功
	 */
	@Test
	public void getUserById() {
		User user = session.selectOne("com.qshuoo.pojo.User.getUserById", 1);
		System.out.println(user);
	}
	
	/**
	 * 插入用户，插入成功
	 */
	@Test
	public void saveUser() {
		User user = new User();
		user.setName("ghm");
		user.setPassword("12138");
		int res = session.insert("com.qshuoo.pojo.User.saveUser", user);
		
		assertEquals(1, res);
	}
	
	/**
	 * 根据用户名和密码删除用户，删除成功
	 */
	
	@Test
	public void deleteUserByNameAndPwd() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ghm");
		map.put("password", "12138");
		
		int res = session.delete("com.qshuoo.pojo.User.deleteUserByNameAndPwd", map);
		
		assertEquals(2, res);
	}
	
	/**
	 * 测试更新用户，测试通过
	 */
	@Test
	public void updateUserNameById() {
		User user = new User();
		user.setId(1);
		user.setName("lgt");
		int res = session.update("com.qshuoo.pojo.User.updateUserNameById", user);
		
		assertEquals(1, res);
	}
	
	@After
	public void destory() {
		session.commit();
		session.close();
	}

