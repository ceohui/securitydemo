package com.whale.web.controller;

import com.whale.model.FileInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.controller
 * @Description: TODO
 * @date 2019/2/12 22:40
 */
@RestController
@RequestMapping("/file")
public class FileController  {

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String folder = "D:\\workspace\\whale-security\\whale-security-demo\\src\\main\\java\\com\\whale\\web\\controller";
        File localFile = new File(folder,new Date().getTime()+".txt");

        //file.getInputStream()  也可以获取到文件流用阿里so上传

        file.transferTo(localFile);

        return  new FileInfo(localFile.getAbsolutePath());
    }
}
