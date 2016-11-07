/**
 * 
 */
package com.elevenstyle.service;

import com.elevenstyle.model.Remember;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午3:13:36
 */
public interface RememberService {
	int insert(Remember e);
	int updateById(Remember e);
	Remember selectById(String seriesId);
	int deleteByEmail(String email);
	
}
