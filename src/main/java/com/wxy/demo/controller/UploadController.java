package com.wxy.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author wxy
 * @Date 19-7-29 下午5:52
 * @Description TODO
 **/
@RestController
@Slf4j
public class UploadController {

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String filename = file.getOriginalFilename();
        // 相对路径：相对当前项目
        File path = new File("temp");
        log.info("path:{}", path.getAbsolutePath());
        if (!path.exists()) {
            path.mkdirs();
        }
        File dest = new File(path.getPath() + File.separator + filename);
        try {
            OutputStream os = new FileOutputStream(dest);
            os.write(file.getBytes());
            return "上传成功";
        } catch (IOException e) {
            log.error("上传失败:{}", e.getMessage());
            return "上传失败";
        }
    }
}
