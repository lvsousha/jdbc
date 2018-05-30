package com.lvdousha.jdbc.mybatis.plugin;

import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;


@Intercepts({ @Signature(type = ParameterHandler.class, 
							method = "setParameters", 
							args = { PreparedStatement.class }
						) 
			})
public class ParameterHandlerPlugin  implements Interceptor{
	
	private Logger log = Logger.getRootLogger();
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("ParameterHandlerPlugin");
		log.info(invocation.getTarget().getClass());
//		Object[] args = invocation.getArgs();
//		PreparedStatement ps = (PreparedStatement) args[0];
//		log.info(ps.toString());
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
