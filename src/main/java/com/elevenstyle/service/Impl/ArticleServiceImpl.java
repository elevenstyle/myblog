package com.elevenstyle.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elevenstyle.mapper.ArticleMapper;
import com.elevenstyle.model.Article;
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
		return articleMapper.insert(e);
	}

}
