package com.jeecms.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;


/**
* @author ljw
* @version 1.0
* @date 2018-07-21
* @Copyright:  江西金磊科技发展有限公司  All rights reserved. 
* Notice 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
*/
public class SysUser  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id ;
    /** 用户名 */
	@Excel(name = "用户名", isImportField = "true", width = 14)
    private  String username ;
    /** 密码 */
	@Excel(name = "密码",isImportField = "true", width = 14, orderNum = "1")
	private String password ;
    /** 加密码 */
    private  String key ;
	/** 创建时间 */
	protected Date createTime;
	/** 创建用户名 */
	protected String createUser;
	/** 修改时间 */
	protected Date updateTime;
	/** 修改用户名 */
	protected String updateUser;
    
	public SysUser (){}
	
    public Integer getId () {
        return this.id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    
    public String getKey () {
        return key;
    }

    public void setKey (String key) {
        this.key = key;
    }
    

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}