/**
 * 
 */
package com.elevenstyle.common.exception;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月14日 下午3:59:06
 */
public class PrivilegeException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 */
	public PrivilegeException(String arg0) {
		super(arg0);
	}
}
