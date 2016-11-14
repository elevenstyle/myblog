/**
 * 
 */
package com.elevenstyle.common.upload;

import com.qiniu.util.Auth;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 七牛简单上传模块
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月10日 下午4:26:14
 */
@Component
public class QiniuSimpleUpload {
	//设置好账号的ACCESS_KEY和SECRET_KEY
	  String ACCESS_KEY = "07I7b6m2UFoOMRB-GtwA9oUNK2dAvxd40xB3Ezkv";
	  String SECRET_KEY = "B2O3CGcy1Nam0I6bb5BqFSQAPujhVvZNJNCG0FZn";
	  //要上传的空间
	  String bucketname = "images";
	  //上传到七牛后保存的文件名
//	  String key = "my-java.png";
	  //上传文件的路径
//	  String FilePath = "/blog/images";

	  //密钥配置
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  //创建上传对象
	  UploadManager uploadManager = new UploadManager();
	  
	  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	  public String getUpToken(){
	      return auth.uploadToken(bucketname);
	  }

	  public String upload(byte[] date, String key) throws IOException{
	    try {
	      //调用put方法上传
	      Response res = uploadManager.put(date, key, getUpToken());
	      //打印返回的信息
	      return res.bodyString(); 
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	        	  return r.bodyString();
	          } catch (QiniuException e1) {
	              return "{hash:"+""+",key:"+""+"}";
	          }
	      }       
	  }
	  
	  public String upload(File file, String key) throws IOException{
		    try {
		      //调用put方法上传
		      Response res = uploadManager.put(file, key, getUpToken());
		      //打印返回的信息
		      return res.bodyString(); 
		      } catch (QiniuException e) {
		          Response r = e.response;
		          // 请求失败时打印的异常的信息
		          System.out.println(r.toString());
		          try {
		              //响应的文本信息
		        	  return r.bodyString();
		          } catch (QiniuException e1) {
		              return "fail";
		          }
		      }       
		  }
}
