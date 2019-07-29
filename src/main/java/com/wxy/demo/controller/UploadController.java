package com.wxy.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author wxy
 * @Date 19-7-29 下午5:52
 * @Description TODO
 **/
@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        File filePath = new File("upload");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File dest = new File(filePath.getPath() + File.separator + filename);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}