package com.baojuan.service.impl;

import com.baojuan.mapper.UsersMapper;
import com.baojuan.pojo.Users;
import com.baojuan.service.UsersService;

public class UsersServiceImpl implements UsersService{
private UsersMapper usersMapper;
	
	public UsersMapper getUsersMapper() {
		return usersMapper;
	}

	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@Override
	public Users login(Users users) {
		return usersMapper.selectByUsersPwd(users);
	}

}
