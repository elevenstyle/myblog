/**
 * 
 */
package com.elevenstyle.common.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import com.elevenstyle.model.Remember;
import com.elevenstyle.service.RememberService;

/**
 * 记住我 持久化方式
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午2:53:33
 */
@Component
public class CustomPersistentTokenRepository implements PersistentTokenRepository{

	@Autowired
	private RememberService rememberService;
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		Remember remember = new Remember();
		remember.setEmail(token.getUsername());
		remember.setSeries(token.getSeries());
		remember.setDate(token.getDate());
		remember.setTokenValue(token.getTokenValue());
		int a = rememberService.insert(remember);
		
	}
	
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		Remember remember = new Remember();
		remember.setEmail("");;
		remember.setSeries(series);
		remember.setTokenValue(tokenValue);
		remember.setDate(lastUsed);
		rememberService.updateById(remember);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		Remember remember = rememberService.selectById(seriesId);
		PersistentRememberMeToken persistentRememberMeToken =
				new PersistentRememberMeToken(remember.getEmail(),
						remember.getSeries(),
						remember.getTokenValue(),
						remember.getDate()
				);
		return persistentRememberMeToken;
	}

	@Override
	public void removeUserTokens(String username) {
		rememberService.deleteByEmail(username);
	}

}
