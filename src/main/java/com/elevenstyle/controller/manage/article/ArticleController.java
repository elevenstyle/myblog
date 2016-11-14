/**
 * 
 */
package com.elevenstyle.controller.manage.article;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.model.Article;
import com.elevenstyle.model.util.QueryModel;
import com.elevenstyle.service.ArticleService;

/**
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月9日 上午9:50:30
 */
@Controller
@RequestMapping("/manage/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	private static final String page_add = "/manage/article/add";
	private static final String page_list = "/manage/article/list";
	
	//我的文章页面
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response, Article e, HttpSession session) throws Exception {
		String stUserId = session.getAttribute("userId").toString();
		Long userId = Long.valueOf(stUserId);
		e.setUserId(userId);
		e.setOffset(0);
		e.setPageSize(10);
		QueryModel page = articleService.selectPageList(e);
		request.setAttribute("pager", page);
		return page_list;
	}
	//新增页面跳转
	@RequestMapping("/toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		List<String> articleType = new ArrayList<>();
		articleType.add("教程");
		articleType.add("原创");
		model.addAttribute("articleType", articleType);
		return page_add;
	}
	
	//新增文章
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response, Article e, RedirectAttributes redirectAttributes) throws Exception {
		String contents = e.getContents();
		if(contents.length()>200) {
			String abstracts = e.getContents().substring(0, 200);
			e.setAbstracts(abstracts);
		} else {
			e.setAbstracts(contents);
		}
		int a = articleService.insert(e);
		if(a>0) {
			redirectAttributes.addAttribute("msg", "新增成功");
			return "redirect:list";
		}
		redirectAttributes.addAttribute("msg", "新增失败");
		return "redirect:toAdd";
	}
}
