package com.elevenstyle.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.mapper.RoleMapper;
import com.elevenstyle.model.Role;
import com.elevenstyle.service.RoleService;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 上午10:45:39
 */
@Service
@Transactional
@Scope("prototype")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Role selectRoleById(Integer id) {
		return roleMapper.selectRoleById(id);
	}
	@Override
	public List<Role> selectByResourceURI(String uri) {
		return roleMapper.selectByResourceURI(uri);
	}

}
