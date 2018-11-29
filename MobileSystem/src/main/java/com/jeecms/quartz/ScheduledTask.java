package com.jeecms.quartz;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.jeecms.domain.SysUser;
import com.jeecms.service.SysUserService;
/**
 * 任务类
 * @author jinlei
 *
 */
@Service
@PersistJobDataAfterExecution //此标记说明在执行完Job的execution方法后保存JobDataMap当中固定数据
@DisallowConcurrentExecution  //不允许并发执行
public class ScheduledTask extends QuartzJobBean{

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		SysUser user=new SysUser();
		user.setUpdateUser(null);
		user.setUpdateTime(null);
		user.setCreateTime(new Date());
		user.setCreateUser("ljw");
		user.setKey("46546");
		user.setPassword("123456");
		user.setUsername("name");
		sysUserService.insertUser(user);
		
		System.out.println("显示的当前时间:"+System.currentTimeMillis());
		
	}

}
