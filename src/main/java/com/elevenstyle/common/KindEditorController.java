package com.elevenstyle.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elevenstyle.common.upload.QiniuSimpleUpload;

/**
 * 上传模块
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月9日 下午5:05:12
 */
@Controller
@RequestMapping("/editor")
public class KindEditorController {
	
	@Autowired
	private QiniuSimpleUpload qiniuSimpleUpload;
	
	//文件下载路径
	@Value("${elevenstyle.getFilePath}")
	private String getPath;

	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject upLoad(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		Random rd = new Random();
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ (10+rd.nextInt(88));
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                //上传图片
                if(file!=null) {
                	String upResult = qiniuSimpleUpload.upload(file.getBytes(), fileName);
                	JSONObject formatUpResult = JSON.parseObject(upResult);
                	if(!StringUtils.isEmpty(formatUpResult.get("key").toString())) {
                		result.put("error", 0);
                		result.put("url", getPath+"/"+formatUpResult.get("key").toString());
                		return result;
                	}
                }
            }
        }
		result.put("error", 1);
		result.put("message", "文件上传失败！");
		return result;
	}
}