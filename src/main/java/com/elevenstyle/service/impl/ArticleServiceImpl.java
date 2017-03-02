package com.elevenstyle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.mapper.ArticleMapper;
import com.elevenstyle.model.entity.Article;
import com.elevenstyle.model.util.QueryModel;
import com.elevenstyle.service.ArticleService;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月9日 下午3:20:41
 */
@Service
@Transactional
@Scope("prototype")
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public int insert(Article e) {
		// TODO Auto-generated method stub
		return articleMapper.insert(e);
	}

	@Override
	public int delete(Article e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Article e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Article selectOne(Article e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryModel selectPageList(Article e) {
		QueryModel model = new QueryModel();
		List<Article> list =  articleMapper.selectPageList(e);
		Integer total = articleMapper.selectPageCount(e);
		model.setList(list);
		model.setTotal(total);
		return model;
	}

	@Override
	public List<Article> selectList(Article e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
