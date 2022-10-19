package com.neo.mapper;

import java.util.List;

import com.neo.model.User;
import com.neo.param.UserParam;

public interface UserMapper {

	List<User> getAll();

	List<User> getList(UserParam userParam);

	int getCount(UserParam userParam);

	User getOne(Long id);

	void insert(User user);

	int update(User user);

	int delete(Long id);

}