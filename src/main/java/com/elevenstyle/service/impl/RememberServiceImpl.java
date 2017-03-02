/**
 * 
 */
package com.elevenstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.mapper.RememberMapper;
import com.elevenstyle.model.entity.Remember;
import com.elevenstyle.service.RememberService;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午3:14:53
 */
@Service
@Transactional
@Scope("prototype")
public class RememberServiceImpl implements RememberService{
	
	@Autowired
	private RememberMapper rememberMapper;
	
	@Override
	public int insert(Remember e) {
		return rememberMapper.insert(e);
	}

	@Override
	public int updateById(Remember e) {
		return rememberMapper.updateById(e);
	}
	
	@Override
	public Remember selectById(String seriesId) {
		return rememberMapper.selectById(seriesId);
	}
	
	@Override
	public int deleteByEmail(String email) {
		return rememberMapper.deleteByEmail(email);
	}

}
