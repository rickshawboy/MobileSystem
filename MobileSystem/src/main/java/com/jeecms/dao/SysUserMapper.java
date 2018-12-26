package com.jeecms.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jeecms.domain.SysUser;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SysUserMapper {

	   void insertUser(SysUser user);

	   void update(SysUser user);

	   void delete(Integer id);
	   
	   SysUser get(Integer id);
}
