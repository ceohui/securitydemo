package com.whale.web.controller;

import com.whale.model.FileInfo;
import jdk.internal.util.xml.impl.Input;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    private String folder = "D:\\workspace\\whale-security\\whale-security-demo\\src\\main\\java\\com\\whale\\web\\controller";

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


    @GetMapping("{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        //jdk1.7语法 将流声明在try() 可以自动关闭
        try(
            InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream();
            ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);//输入流copy到输出流

            outputStream.flush();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
