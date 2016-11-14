/**
 * 
 */
package com.elevenstyle.model.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 页面查询模型
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月14日 下午2:35:16
 */
public class QueryModel implements ClearBean{
	
	private String id;
	private Integer pageNo;
	private Integer offset;
	private Integer pageSize;
	private Date startTime;
	private Date endTime;
	private List list = new ArrayList(); // 分页集合列表
	private Integer total; // 总数
	private String pageUrl;
	private Integer pagerSize; //总页数
	
	@Override
	public void clear() {
		id=null;
		pageNo=null;
		offset=null;
		pageSize=null;
		startTime=null;
		endTime=null;
		list.clear();
		total=null;
		pageUrl=null;
		pagerSize=null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List getList() {
		return list == null ? new LinkedList() : list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Integer getPagerSize() {
		return pagerSize;
	}
	public void setPagerSize(Integer pagerSize) {
		this.pagerSize = pagerSize;
	}
	
}
