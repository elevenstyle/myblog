package com.elevenstyle.mapper.base;

import java.util.List;

import com.elevenstyle.model.util.QueryModel;


/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月14日 下午2:52:09
 */
public interface BaseMapper<E extends QueryModel> {
	/**
	 * 添加
	 * 
	 * @param e
	 * @return
	 */
	public int insert(E e);

	/**
	 * 删除
	 * 
	 * @param e
	 * @return
	 */
	public int delete(E e);

	/**
	 * 修改
	 * 
	 * @param e
	 * @return
	 */
	public int update(E e);

	/**
	 * 查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectOne(E e);

	/**
	 * 分页查询
	 * 
	 * @param e
	 * @return
	 */
	public List<E> selectPageList(E e);
	
	/**
	 * 分页查询总页数
	 * 
	 * @param e
	 * @return
	 */
	public Integer selectPageCount(E e);
	
	/**
	 * 根据条件查询所有
	 * @return
	 */
	public List<E> selectList(E e);

	/**
	 * 根据ID来删除一条记录
	 * @param id
	 */
	public int deleteById(int id);

	/**
	 * 根据ID查询一条记录
	 * @param id
	 * @return
	 */
	public E selectById(String id);
}
