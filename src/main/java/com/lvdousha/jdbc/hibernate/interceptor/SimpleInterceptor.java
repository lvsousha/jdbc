package com.lvdousha.jdbc.hibernate.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class SimpleInterceptor extends EmptyInterceptor{

	private static final long serialVersionUID = -4570825760115740365L;
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("IN");
		return false;
	}

}
