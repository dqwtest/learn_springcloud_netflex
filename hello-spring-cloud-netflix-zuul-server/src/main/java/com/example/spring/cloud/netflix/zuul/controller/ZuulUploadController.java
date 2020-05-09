package com.example.spring.cloud.netflix.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ZuulUploadController {

    @PostMapping("/upload")
    public String uploadFilter(@RequestParam(value = "file", required = true)
                                           MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        // 获得上传时的文件名
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
