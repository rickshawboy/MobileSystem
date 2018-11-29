package com.jeecms.aspect;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeecms.annotation.ValidUserName;
import com.jeecms.domain.SysUser;
import com.jeecms.quartz.QuartzManager;
import com.jeecms.quartz.ScheduledTask;
import com.jeecms.service.SysUserService;

/**
 * 
 * @Description: 用户的控制器
 * @author: ljw
 * @date:   2018年7月21日 下午4:50:50     
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved. 
 * Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@RestController
@RequestMapping(value="userasp")
public class SysAspController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private QuartzManager quartzManager;
	
	@RequestMapping(value="insert",method=RequestMethod.POST)
	public void insert(SysUser user) {
		user.setUpdateUser(null);
		user.setUpdateTime(null);
		user.setCreateTime(new Date());
		user.setCreateUser("ljw");
		user.setKey("46546");
		user.setPassword("123456");
		user.setUsername("name");
		sysUserService.insertUser(user);
	}
	
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	public void create(String time) {
		String cron="0 32 14 27 07 ? 2018";
		String jobName="job";
		String jobGroupName="jobGroup";
		String triggerName="name";
		String triggerGroupName="name";
		quartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ScheduledTask.class, cron);	
	}
	
	@RequestMapping(value="something",method=RequestMethod.GET)
	@ValidUserName(value="username")
	public void something(String something) {
		System.out.println(something);
	}
	
	
    @RequestMapping(value="sometime",method=RequestMethod.GET)
    @ValidUserName(value="sometime")
    public void sometime(String sometime) {
        System.out.println("ASP---"+sometime);
    }
}
