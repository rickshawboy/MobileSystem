package com.jeecms.service;

import com.jeecms.domain.SysUser;

public interface SysUserService {

	void insertUser(SysUser user);

	void update(SysUser user);

	void delete(Integer id);
	
	SysUser get(Integer id);
}
