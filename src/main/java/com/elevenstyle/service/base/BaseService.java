/**
 * 
 */
package com.elevenstyle.service.base;

import java.util.List;

import com.elevenstyle.mapper.base.BaseMapper;
import com.elevenstyle.model.util.QueryModel;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月14日 下午2:48:47
 */
public interface BaseService<E extends QueryModel> {
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
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public int deletes(String[] ids);

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
	 * 根据ID查询一条记录
	 * 
	 * @param e
	 * @return
	 */
	public E selectById(String id);

	/**
	 * 分页查询
	 * 
	 * @param e
	 * @return
	 */
	public QueryModel selectPageList(E e);
	
	/**
	 * 根据条件查询所有
	 * @return
	 */
	public List<E> selectList(E e);
}
