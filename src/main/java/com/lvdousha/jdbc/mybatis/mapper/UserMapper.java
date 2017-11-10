package com.lvdousha.jdbc.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.lvdousha.jdbc.mybatis.model.User;

public interface UserMapper {
	 public User selectUser(int id);
	 
	 public User selectUserByNameAndPassword(String name, String password);
	 
	 public Map<String,Object> selectUserByName(String name);
	 
	 public void insertUser(User user);
	 
	 public void insertUsers(List<User> users);
}
