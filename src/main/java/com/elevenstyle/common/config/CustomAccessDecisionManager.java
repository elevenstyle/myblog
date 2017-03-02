/**
 * 
 */
package com.elevenstyle.common.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.elevenstyle.model.entity.Role;
import com.elevenstyle.service.RoleService;

/**
 * 验证资源跟角色之间的关系
 * authentication 为用户所被赋予的权限, configAttributes 为访问相应的资源应该具有的
 * @param authentication
 * @param object
 * @param configAttributes
 * @throws AccessDeniedException
 * @throws InsufficientAuthenticationException
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午12:23:46
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager{
	
	@Autowired
	private RoleService roleService;

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		Collection<GrantedAuthority> userHasRoles = (Collection<GrantedAuthority>) authentication.getAuthorities();
		//放行[管理员]角色
		Iterator<GrantedAuthority> iterator = userHasRoles.iterator();
		while (iterator.hasNext()){
		GrantedAuthority grantedAuthority = iterator.next();
			if("ROLE_ADMIN".equals(grantedAuthority.getAuthority())||"ROLE_SUPERADMIN".equals(grantedAuthority.getAuthority())){
				return;
					}
				}
				Collection<GrantedAuthority> uriHasRoles = getGrantedAuthoritys(object);
				if (uriHasRoles == null || uriHasRoles.size() == 0) {
				return;
				}
				Optional<Collection<GrantedAuthority>> grantedAuthoritiesForOptional =
				Optional.ofNullable(userHasRoles);
				try{
				grantedAuthoritiesForOptional.ifPresent(userHasRolesNotNull -> {
				userHasRolesNotNull.forEach(userHasRole -> {
				uriHasRoles.forEach(uriHasRole -> {
				if (userHasRole.getAuthority().equals(uriHasRole.getAuthority())) {
					try {
						throw new Exception("break");
					} catch (Exception e) {
						e.printStackTrace();
					}
								}
							});
						});
					});
				}catch(Exception be){
				return;
				}
			throw new AccessDeniedException("Access Denied.");

	}
	
	//根据uri获取角色资源
	private Collection<GrantedAuthority> getGrantedAuthoritys(Object object) {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String uri = new StringBuilder(filterInvocation.getRequestUrl()).deleteCharAt(0).toString();
		if("".equals(uri)){
		return null;
		}
		List<Role> uriHasRoles = roleService.selectByResourceURI(uri);
		if (uriHasRoles == null || uriHasRoles.size() == 0) {
		return null;
		}
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		uriHasRoles.forEach(item -> {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(item.getRole_name());
		grantedAuthorities.add(grantedAuthority);
		});
		return grantedAuthorities;
		}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
