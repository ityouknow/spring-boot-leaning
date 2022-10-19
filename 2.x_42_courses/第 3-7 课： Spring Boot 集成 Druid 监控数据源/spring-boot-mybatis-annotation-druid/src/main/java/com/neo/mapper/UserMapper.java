package com.neo.mapper;

import java.util.List;

import com.neo.param.UserParam;
import org.apache.ibatis.annotations.*;

import com.neo.model.User;
import com.neo.enums.UserSexEnum;

public interface UserMapper {
	
	@Select("SELECT * FROM users")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	List<User> getAll();

	@SelectProvider(type = UserSql.class, method = "getList")
	List<User> getList(UserParam userParam);

	@SelectProvider(type = UserSql.class, method = "getCount")
	int getCount(UserParam userParam);
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	User getOne(Long id);

	@Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
	void insert(User user);

	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(User user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);

}