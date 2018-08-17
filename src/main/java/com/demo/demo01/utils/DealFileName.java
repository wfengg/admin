package com.demo.demo01.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class  DealFileName {

    //获取文件名
    public static String getFileName(MultipartFile file){
        return file.getOriginalFilename();
    }
    //获取文件后缀名
    public static String checkFile(MultipartFile file){
        String filename = DealFileName.getFileName(file);
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
    //添加文件
    public static boolean insertImg(MultipartFile file,String local){
        String filename = DealFileName.getFileName(file);
        try {
            file.transferTo(new File(local+filename));
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取文件大小
    public static Long getImgSize(MultipartFile file){
        return file.getSize();
    }
}
