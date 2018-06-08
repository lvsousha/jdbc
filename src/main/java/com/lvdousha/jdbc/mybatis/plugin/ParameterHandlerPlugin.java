package com.lvdousha.jdbc.mybatis.plugin;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

import com.alibaba.druid.support.json.JSONUtils;


@Intercepts({ @Signature(type = ParameterHandler.class, 
							method = "setParameters", 
							args = { PreparedStatement.class }
						) 
			})
public class ParameterHandlerPlugin  implements Interceptor{
	
	private Logger log = Logger.getRootLogger();
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("----ParameterHandlerPlugin-------");
		DefaultParameterHandler target = (DefaultParameterHandler)invocation.getTarget();
		log.info(target.getClass());
		for(Object arg : invocation.getArgs()){
			log.info(arg.getClass());
		}
		PreparedStatement ps = (PreparedStatement)invocation.getArgs()[0];
		Field field = target.getClass().getDeclaredField("boundSql");
		field.setAccessible(true);
		BoundSql boundSql = (BoundSql)field.get(target);
		log.info(boundSql.getSql());
		log.info(ps.getParameterMetaData().getParameterCount());
		log.info(boundSql.getParameterObject().getClass());
		System.out.println("----------");
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
