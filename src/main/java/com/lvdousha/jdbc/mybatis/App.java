package com.lvdousha.jdbc.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lvdousha.jdbc.mybatis.mapper.UserMapper;
import com.lvdousha.jdbc.mybatis.model.User;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ) throws Exception{
    	String resource = "mybatis-config.xml";
    	InputStream inputStream = Resources.getResourceAsStream(resource);
    	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    	Configuration conf = sqlSessionFactory.getConfiguration();
    	System.out.println("---------ResultMaps-----------");
    	for(ResultMap rm : conf.getResultMaps()){
    		System.out.println(rm.getId());
    	}
    	System.out.println("---------MappedStatements-----------");
    	for(MappedStatement ms : conf.getMappedStatements()){
    		String namespaceId = ms.getId();
    		if(namespaceId.equals("com.lvdousha.jdbc.mybatis.mapper.UserMapper.selectUser")){
    			BoundSql boundSql = ms.getBoundSql(1);
    			System.out.println(namespaceId);
    			System.out.println(boundSql.getSql());
    			
    		}
    	}
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		UserMapper userMapper = session.getMapper(UserMapper.class);
//    		UserDetailMapper userDetailMapper = session.getMapper(UserDetailMapper.class);
//    		UserDetail userDetail = userDetailMapper.select(1);
//    		System.out.println(userDetail.getName());
    		User user = userMapper.selectUser(1);
    		System.out.println(user.getName());
    		User user2 = session.selectOne("com.lvdousha.jdbc.mybatis.mapper.UserMapper.selectUser", 1);
    		System.out.println(user2.getName());
//    		System.out.println(user.getUserDetails().size());
//    		List<User> users = new ArrayList<User>();
//    		for(int i=0; i < 10; i++){
//    			User user = new User();
//    			user.setName("test"+i);
//    			users.add(user);
//    		}
//    		userMapper.insertUsers(users);
    	} finally {
    		session.commit();
    		session.close();
    	}
    }
}
