package com.cqut.stu.pai.util;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 石益然
 * @program: pai
 * @description: 上传文件
 * @date 2020-11-19 17:45:30
 */
public class FileUpload {
    public static String uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            String oldName = file.getOriginalFilename();
            String realPath = "D://upload_new/";//request.getServletContext().getRealPath("/uploadFile/");
            String newName = UUID.randomUUID().toString() + "_" + oldName;
            File folder = new File(realPath, newName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(folder);
            String netUrl = realPath +newName;
            return netUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}