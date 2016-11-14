package com.elevenstyle.controller.base;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevenstyle.model.util.QueryModel;
import com.elevenstyle.service.base.BaseService;

/** 
 * @author lijie
 * @date 2016年8月15日 
 * @email lijie@6mi.com
 */
public abstract class BaseController<E extends QueryModel> {
	
	//logger
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String page_add = null;
	protected String page_edit = null;
	protected String page_list = null;
	public abstract BaseService<E> getService();
	
	/**
     * 子类必须要实现的方法当分页查询后.
     * 解决了用户先点击新增按钮转到新增页面,然后点击返回按钮返回后,再点分页控件出错的BUG.
     * 原因是分页查询后未将pageUrl重新设置为正确的URL所致
     */
	protected void selectListAfter(QueryModel page) {
        page.setPageUrl("selectList");
    }
	
	/**
     * 公共的分页方法
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("list")
    public String selectList(HttpServletRequest request, @ModelAttribute("e") E e) throws Exception {
    	if(StringUtils.isEmpty(e.getPageNo().toString())||StringUtils.isEmpty(e.getPageSize().toString())) {
			e.setOffset(0);
			e.setPageSize(10);
		} else {
			e.setOffset((e.getPageNo()-1)*e.getPageSize());
		}
    	QueryModel page = getService().selectPageList(e);
    	// 计算总页数
    	page.setPagerSize((page.getTotal() + page.getPageSize() - 1)  / page.getPageSize());
    	selectListAfter(page);
    	request.setAttribute("page", page);
        return page_list;
    }
    
    /**
     * 跳转到新增页面
     *
     * @return
     * @throws Exception
     */
  	@RequestMapping("/toAdd")
  	public String toAdd(@ModelAttribute("e") E e, ModelMap model) throws Exception{
  		e.clear();
        return page_add;
  	}
  	
  	 /**
     * 跳转到编辑页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("toEdit")
    public String toEdit(@ModelAttribute("e") E e, ModelMap model) throws Exception {
        model.addAttribute("e", getService().selectOne(e));
        return page_edit;
    }
    
    /**
     * 返回到查询页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("back")
    public String back(@ModelAttribute("e") E e, HttpServletRequest request, ModelMap model) throws Exception {
        return selectList(request, e);
    }

    /**
     * 公共的批量删除数据的方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deletes", method = RequestMethod.POST)
    public String deletes(HttpServletRequest request, String[] ids, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        getService().deletes(ids);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:list";
    }
    
    /**
     * 公共的更新数据的方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "update", method ={RequestMethod.POST,RequestMethod.GET} )
    public String update(HttpServletRequest request, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        getService().update(e);
        insertAfter(e);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:list";
    }


    /**
     * 公共的插入数据方法，子类可以通过重写此方法实现个性化的需求。
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public String insert(HttpServletRequest request, @ModelAttribute("e") E e, RedirectAttributes flushAttrs) throws Exception {
        int i=getService().insert(e);
        insertAfter(e);
        addMessage(flushAttrs, "操作成功！");
        return "redirect:list";
    }
    
    /**
     * insert之后，selectList之前执行的动作，一般需要清除添加的E，否则查询会按照E的条件进行查询.
     * 部分情况下需要保留某些字段，可以选择不清除
     *
     * @param e
     */
    protected void insertAfter(E e){
 
    }
    
	/**
	 * 获取请求ip
	 * @param session
	 * @param request
	 * @param response
	 * @return ipAddress
	 * @throws Exception
	 */
	public String getIp(HttpServletRequest request) throws Exception {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for"); 
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getHeader("Proxy-Client-IP");  
		}  
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if (ipAddress == null || ipAddress.length() == 0  
		        || "unknown".equalsIgnoreCase(ipAddress)) {  
		    ipAddress = request.getRemoteAddr();  
		          
		    //这里主要是获取本机的ip,可有可无  
		    if (ipAddress.equals("127.0.0.1") || ipAddress.endsWith("0:0:0:0:0:0:1")) {  
		        // 根据网卡取本机配置的IP  
		        InetAddress inet = null;  
		        try {  
		            inet = InetAddress.getLocalHost();  
		        } catch (UnknownHostException e) {  
		            e.printStackTrace();  
		        }  
		        ipAddress = inet.getHostAddress();  
		    }  
		}
		return ipAddress;
	}
	
	protected void addMessage(ModelMap modelMap, String message) {
        modelMap.addAttribute("message", message);
    }
    protected void addWarning(ModelMap modelMap, String warning) {
        modelMap.addAttribute("warning", warning);
    }
    protected void addError(ModelMap modelMap, String warning) {
        modelMap.addAttribute("errorMsg", warning);
    }
    protected void addMessage(RedirectAttributes flushAttrs, String message) {
        flushAttrs.addFlashAttribute("message", message);
    }
    protected void addWarning(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("warning", warning);
    }
    protected void addError(RedirectAttributes flushAttrs, String warning) {
        flushAttrs.addFlashAttribute("errorMsg", warning);
    }
}
