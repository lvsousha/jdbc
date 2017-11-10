package com.lvdousha.jdbc.basic;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;


/**
 * Hello world!
 *
 */
public class Main 
{
	//批量插入10000，2s； 批量更新150s
    public static void main( String[] args ) throws Exception{
    	Logger log = Logger.getRootLogger();
    	String driverClassName = StaticVariable.GP_CLASS_NAME;
    	String url = StaticVariable.GP_URL;
    	String userName = "gpmon";
    	String password = "pivotal";
    	Class.forName(driverClassName);
    	java.sql.Connection connection = DriverManager.getConnection(url,userName,password);
//    	boolean autoCommit = connection.getAutoCommit();
//    	if(autoCommit == true){
//    		connection.setAutoCommit(false);
//    	}
//    	log.info(connection.getAutoCommit());
//    	log.info("开始插入");
//    	
    	System.out.println(connection);
    	PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM   gpadmin.information_schema.attributes");
    	ResultSet result = prepareStatement.executeQuery();
    	while(result.next()){
    		System.out.println(result.getObject(2));
    	}
//    	for(int i=4;i<1000;i++){
//    		prepareStatement.setInt(1, i);
//    		prepareStatement.setString(2, "test"+i);
//    		prepareStatement.addBatch();
//    	}
//    	prepareStatement.executeBatch();
//    	connection.commit();
//    	prepareStatement.clearBatch();
//    	log.info("插入结束");
//    	log.info("开始更新");
//    	prepareStatement = connection.prepareStatement("update user set name=? where name=?");
//    	for(int i=0;i<10000;i++){
//    		prepareStatement.setString(1, "update"+i);
//    		prepareStatement.setString(2, "insert"+i);
//    		prepareStatement.addBatch();
//    	}
//    	prepareStatement.executeBatch();
//    	connection.commit();
//    	log.info("更新结束");
//    	prepareStatement.close();
    	connection.close();
    	log.info("结束");
    }
}
