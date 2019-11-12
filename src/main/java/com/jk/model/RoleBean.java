/** 
 * <pre>项目名称:ssm-user-wdd 
 * 文件名称:RoleBean.java 
 * 包名:com.jk.wdd.pojo 
 * 创建日期:2019年9月3日下午4:42:05 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <pre>项目名称：ssm-user-wdd    
 * 类名称：RoleBean    
 * 类描述：    
 * 创建人：wdd   
 * 创建时间：2019年9月3日 下午4:42:05    
 * 修改人：wdd 
 * 修改时间：2019年9月3日 下午4:42:05    
 * 修改备注：       
 * @version </pre>    
 */

public class RoleBean  implements Serializable {

	private Integer rolesId;
	private String rolesName;
	private String rolesInfo;
	private String value;

	public Integer getRolesId() {
		return rolesId;
	}

	public void setRolesId(Integer rolesId) {
		this.rolesId = rolesId;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public String getRolesInfo() {
		return rolesInfo;
	}

	public void setRolesInfo(String rolesInfo) {
		this.rolesInfo = rolesInfo;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
