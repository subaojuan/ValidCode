package com.baojuan.mapper;

import org.apache.ibatis.annotations.Select;

import com.baojuan.pojo.Users;

public interface UsersMapper {
	@Select ("select * from users where username=#{username} and password=#{password}")
	Users selectByUsersPwd(Users users);
}
