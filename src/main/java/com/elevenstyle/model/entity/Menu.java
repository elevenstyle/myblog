/**
 * 
 */
package com.elevenstyle.model.entity;

import java.io.Serializable;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月8日 上午9:55:20
 */
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pid;
	private String url;
	private String name;
	private Integer orderNum;
	private String type;// module：模块 ; page：页面 ; button：功能
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
