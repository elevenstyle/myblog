/**
 * 
 * @author lijie
 */
package com.elevenstyle.util;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Eleven
 *
 */
public class FileUpload {  
	  
    public static final String FILE_PATH = "/upload/";  
  
    //文件上传  
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String path) throws IOException {  
//      String fileName = file.getOriginalFilename();
        Random rd = new Random();
        int i = 100 + rd.nextInt(800);
        File tempFile = new File(path+FILE_PATH, new Date().getTime() + String.valueOf(i));  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return tempFile.getName();  
    }  
  
    public static File getFile(String fileName,String path) {  
        return new File(path+FILE_PATH, fileName);  
    }  
}  
