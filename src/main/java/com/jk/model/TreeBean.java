/** 
 * <pre>项目名称:ssm-user-wdd 
 * 文件名称:TreeBean.java 
 * 包名:com.jk.wdd.pojo 
 * 创建日期:2019年9月3日上午9:21:04 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;



import java.io.Serializable;
import java.util.List;

/** 
 * <pre>项目名称：ssm-user-wdd    
 * 类名称：TreeBean    
 * 类描述：    
 * 创建人：wdd   
 * 创建时间：2019年9月3日 上午9:21:04    
 * 修改人：wdd 
 * 修改时间：2019年9月3日 上午9:21:04    
 * 修改备注：       
 * @version </pre>    
 */

public class TreeBean{


	
	private Integer id;
	private String text;
	private String url;
	private Integer pid;
	private List<TreeBean> children;
	private Boolean checked;
	private String flag;
	private String permission;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
