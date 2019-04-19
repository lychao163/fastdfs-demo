package com.lychao163.fastdfsdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.lychao163.fastdfsdemo.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Description TODO
 * @Date 2019/4/17 14:24
 * @Author lychao
 */
@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @RequestMapping("test")
    public String test(){
        return "test";
    }

    @RequestMapping("uploadFile")
    public String uploadFile(){
        try {
            documentService.uploadFile();
        }catch(Exception e){
            log.error(e.getMessage());
            return "文件上传失败";
        }
        return "上传成功";
    }

    @RequestMapping("downloadFile")
    public String downloadFile(@RequestParam String fileName){
        try {
            documentService.download(fileName);
        }catch(Exception e){
            log.error(e.getMessage());
            return "文件下载失败";
        }
        return "文件下载成功，文件路径E:\\dfs下载\\"+fileName+".doc";
    }

    @RequestMapping("/fileInfo")
    public JSONObject fileInfo(){
        FileInfo fileInfo=documentService.fileInfo();
        System.out.println(fileInfo.getCreateTime());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("校验码",fileInfo.getCrc32());
        jsonObject.put("创建日期",new Date(fileInfo.getCreateTime()));
        jsonObject.put("文件大小",fileInfo.getFileSize());
        jsonObject.put("存储ip",fileInfo.getSourceIpAddr());
        return jsonObject;
    }
    @RequestMapping("/deleteFile")
    public String deleteFile(){
        documentService.deleteFile();
        return "文件删除成功";
    }

    @RequestMapping("/queryGroup")
    public JSONObject queryGroup(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("Storage信息",documentService.queryStorage()) ;
        jsonObject.put("Group信息",documentService.queryGroup()) ;
        return jsonObject;
    }

    @RequestMapping("/appendFile")
    public String appendFile() {
        try {
            documentService.appendFile();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "文件拼接失败";
        }
        return "文件拼接成功";
    }

    @PostMapping("/uploadFileForPage")
    public String uploadFileForPage(@RequestParam(value="file") MultipartFile file){
        String filepath;
        try {
            filepath=documentService.uploadFile(file);
        }catch(Exception e){
            log.error(e.getMessage());
            return "文件上传失败";
        }
        return "上传成功,文件path:"+filepath;
    }
}
