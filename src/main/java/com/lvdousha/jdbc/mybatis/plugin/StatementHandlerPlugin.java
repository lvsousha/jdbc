package com.lvdousha.jdbc.mybatis.plugin;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;

@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})  
public class StatementHandlerPlugin implements Interceptor {

	private Logger log = Logger.getRootLogger();

	public Object intercept(Invocation invocation) throws Throwable {
		log.info("StatementHandlerPlugin");
		log.info(invocation.getTarget().getClass());
		RoutingStatementHandler rsh = (RoutingStatementHandler)invocation.getTarget();
		for(Object object : invocation.getArgs()){
			log.info(object.getClass());
		}
		BoundSql boundSql = rsh.getBoundSql();
		String orginSql = boundSql.getSql();
		if(orginSql.matches("[\r\n\\s]*select[.\n]+")){
			Field sqlField = boundSql.getClass().getDeclaredField("sql");
			sqlField.setAccessible(true);
			sqlField.set(boundSql, orginSql);
		}
//		log.info(boundSql.getSql());
//		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//		BoundSql boundSql = statementHandler.getBoundSql();
//		log.info(boundSql.getSql());
//		log.info(boundSql.getParameterObject());
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}  
}  
