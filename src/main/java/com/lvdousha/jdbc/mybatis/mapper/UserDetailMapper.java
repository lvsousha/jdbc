package com.lvdousha.jdbc.mybatis.mapper;

import java.util.List;

import com.lvdousha.jdbc.mybatis.model.UserDetail;

public interface UserDetailMapper {
	
	 public UserDetail select(int id);
	 
	 public List<UserDetail> selectByUser(int id);
}
