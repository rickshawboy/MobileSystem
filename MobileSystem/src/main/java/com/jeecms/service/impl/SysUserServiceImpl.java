package com.jeecms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.dao.SysUserMapper;
import com.jeecms.domain.SysUser;
import com.jeecms.service.SysUserService;

@Service(value="sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public void insertUser(SysUser user) {
		sysUserMapper.insertUser(user);
	}

	@Override
	public void update(SysUser user) {
		sysUserMapper.update(user);
	}

	@Override
	public void delete(Integer id) {
		sysUserMapper.delete(id);		
	}

    @Override
    public SysUser get(Integer id) {
        return sysUserMapper.get(id);
    }
	
}
