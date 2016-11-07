package com.elevenstyle.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.elevenstyle.service.CustomUserService;

/** 
 * @author lijie
 * @date 2016年9月5日 
 * @email lijie@6mi.com
 */

@Configuration
@ComponentScan("com.eleven")
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
	private CustomAccessDecisionManager customAccessDecisionManager;
	@Autowired
	private CustomPersistentTokenRepository customPersistentTokenRepository;
	@Autowired
	private CustomUserService customUserService;
	
	//用户认证
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
		auth.userDetailsService(customUserService);
	}
	
	//不需要验证权限目录
	@Override
	public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/js/**", "/css/**", "/image/**", "/fonts/**","i/**");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .headers()
        	.frameOptions().sameOrigin().disable()	//disable X-Frame-Options
            .authorizeRequests()					//开始请求权限配置
                .antMatchers("/*").permitAll()		// ""路径下不需要保护
                .antMatchers("/manage/admin/**").hasAnyRole("ROLE_SUPERADMIN","ROLE_ADMIN")
                .antMatchers("/manage/user/**").hasAnyRole("ROLE_SUPERADMIN","ROLE_ADMIN","ROLE_USER")
                .accessDecisionManager(customAccessDecisionManager)	//用注解替换,如果不使用注解，取消注释
                .anyRequest().authenticated()		//其余所有请求都需要验证
                .and()
            .formLogin()
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
            	.deleteCookies("JSESSIONID")
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/index")
                .and()
            .rememberMe()			//开启cookie
                	.tokenRepository(customPersistentTokenRepository)
                	.rememberMeServices(rememberMeServices())
                	.rememberMeParameter("remember-me").key("key")
                	.tokenValiditySeconds(86400)
                	.and()
                	.csrf().disable() //disable csrf
                	.sessionManagement().maximumSessions(1);
    }
	
	@Bean
	public RememberMeServices rememberMeServices() {
	// Key must be equal to rememberMe().key()
	PersistentTokenBasedRememberMeServices rememberMeServices =
	new PersistentTokenBasedRememberMeServices("key", customUserService, customPersistentTokenRepository);
	rememberMeServices.setCookieName("remember-me");
	rememberMeServices.setParameter("remember-me");
	rememberMeServices.setTokenValiditySeconds(864000);
	return rememberMeServices;
	}
}
