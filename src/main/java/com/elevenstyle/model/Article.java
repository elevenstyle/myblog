/**
 * 
 */
package com.elevenstyle.model;

import java.io.Serializable;
import java.util.Date;

import com.elevenstyle.model.util.QueryModel;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月9日 下午3:02:29
 */
public class Article extends QueryModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String contents;
	private String image;
	private String abstracts;
	private String typeName;
	private Integer top;
	private Date createTime;
	private Long userId;
	private Integer readCount;
	private Integer shareCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getReadCount() {
		return readCount;
	}
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

}
