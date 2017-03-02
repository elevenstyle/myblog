/**
 * 
 */
package com.elevenstyle.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.elevenstyle.model.entity.Role;
import com.elevenstyle.model.entity.User;
import com.elevenstyle.model.util.SysUser;
import com.elevenstyle.service.RoleService;
import com.elevenstyle.service.UserService;

/**获取认证用户信息
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月8日 上午10:23:23
 */
@Component
public class GetLoginUserDetail {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	//获取用户信息
	public SysUser getSysUserDetail() {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
			User user = userService.getUserInfo(userDetails.getUsername());
			Role role = roleService.selectRoleById(user.getRid());
			SysUser sysUser = new SysUser();
			sysUser.setAge(user.getAge());
			sysUser.setEmail(user.getEmail());
			sysUser.setId(user.getId());
			sysUser.setImage(user.getImage());
			sysUser.setNickname(user.getNickname());
			sysUser.setPassword(null);
			sysUser.setRid(user.getRid());
			sysUser.setRole_dbPrivilege(role.getRole_dbPrivilege());
			sysUser.setRole_desc(role.getRole_desc());
			sysUser.setRole_name(role.getRole_name());
			sysUser.setRole_type(role.getRole_type());
			sysUser.setStatus(role.getStatus());
			return sysUser;
		} catch (Exception e) {
			return null;
		}
	}

}
