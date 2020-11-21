package com.cqut.stu.pai.util;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author 石益然
 * @program: pai
 * @description: 下载文件
 * @date 2020-11-19 20:56:18
 */
public class FileDownload {
    public static void logDownload(String name, HttpServletResponse response) throws Exception {
        File file = new File(name);

        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
       // response.setContentType("application/force-download");
        //response.addHeader("Content-Disposition", "attachment;fileName=" + name);
        String realName = name.substring(name.lastIndexOf("_")+1);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realName,"utf-8"));
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }
}