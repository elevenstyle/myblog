/**
 * 
 */
package com.elevenstyle.model.util;

import com.elevenstyle.model.User;

/**	用户信息（包含Role）
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月8日 上午10:24:58
 */
public class SysUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String role_name;
	private String role_desc;
	private String role_dbPrivilege;
	private String status;
	private String role_type;
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public String getRole_dbPrivilege() {
		return role_dbPrivilege;
	}
	public void setRole_dbPrivilege(String role_dbPrivilege) {
		this.role_dbPrivilege = role_dbPrivilege;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

}
