package com.neo.mapper;

import java.util.List;

import com.neo.entity.UserEntity;
import com.neo.param.PageParam;
import com.neo.param.UserParam;
import com.neo.result.Page;

public interface UserMapper {

	List<UserEntity> getAll();

	List<UserEntity> getList(UserParam userParam);

	int getCount(UserParam userParam);

	UserEntity getOne(Long id);

	void insert(UserEntity user);

	int update(UserEntity user);

	int delete(Long id);

}