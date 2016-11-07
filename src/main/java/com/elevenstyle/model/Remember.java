/**
 * 
 */
package com.elevenstyle.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午3:09:09
 */
public class Remember implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String series;
	private Date date; 
	private String tokenValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

}
