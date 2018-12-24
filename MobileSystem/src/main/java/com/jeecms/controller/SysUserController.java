package com.jeecms.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeecms.annotation.Json;
import com.jeecms.annotation.ValidUserName;
import com.jeecms.domain.SysUser;
import com.jeecms.quartz.QuartzManager;
import com.jeecms.quartz.ScheduledTask;
import com.jeecms.service.SysUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Description: 用户的控制器
 * @author: ljw
 * @date:   2018年7月21日 下午4:50:50     
 * @Copyright:  江西金磊科技发展有限公司  All rights reserved. 
 * Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@RestController
@RequestMapping(value="user")
public class SysUserController {

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
        System.out.println(sometime);
    }
    
    @RequestMapping(value="get",method=RequestMethod.GET)
    @Json(type = SysUser.class, includes = {"id"})
    public SysUser get(Integer id) {
        return sysUserService.get(id);
    }


	@RequestMapping(value="export",method=RequestMethod.GET)
	public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<SysUser> datas = new ArrayList<SysUser>(10);
		String title = "退换货数据";
		String sheetName = "退换货信息";
		String fileName = "大西瓜";
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), SysUser.class, datas);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(".xls","UTF-8"));
		workbook.write(response.getOutputStream());
	}
    
}
