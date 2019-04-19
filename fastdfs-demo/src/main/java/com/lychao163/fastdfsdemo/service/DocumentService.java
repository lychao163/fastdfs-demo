package com.lychao163.fastdfsdemo.service;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadFileWriter;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @Date 2019/4/17 11:13
 * @Author lychao
 */
@Service
@Slf4j
public class DocumentService {
    //   主要用于操作Store Storage
    @Autowired
    private  TrackerClient trackerClient;
    //    为方便项目开发集成的简单接口
    @Autowired
    private  FastFileStorageClient fastFileStorageClient;
    //    支持文件续传操作的接口
    @Autowired
    private  AppendFileStorageClient appendFileStorageClient;

    @Autowired
    private  DefaultGenerateStorageClient defaultGenerateStorageClient;

    /**
     * 上传文件
     * @throws Exception
     */
    public void uploadFile() throws Exception{
        File file=new File("C:\\Users\\Administrator\\Desktop\\外贸1801-副本\\担保函.doc");
        StorePath storePath =fastFileStorageClient.uploadFile(new FileInputStream(file),file.length(), FilenameUtils.getExtension(file.getName()),null);
        log.error(storePath.getPath());
    }

    /**
     * 下载文件
     * @throws Exception
     */
    public void download(String fileName) {
        DownloadCallback<String> downloadCallback = new DownloadFileWriter("E:\\dfs下载\\"+fileName+".doc");
        fastFileStorageClient.downloadFile("group1", "M00/00/00/CooeOly4GmCAWQqXAAN6AMKwvmE492.doc",downloadCallback);
    }

    /**
     * @return 查看单个文件信息
     */
    public FileInfo fileInfo() {
        return fastFileStorageClient.queryFileInfo("group1","M00/00/00/CooeOly4GmCAWQqXAAN6AMKwvmE492.doc");
    }

    /**
     * 删除单个文件
     */
    public void  deleteFile(){
        fastFileStorageClient.deleteFile("group1","M00/00/00/CooeOly4GmCAWQqXAAN6AMKwvmE492.doc");
    }

    /**
     * @return storage组信息查询
     */
    public List queryGroup(){
        return trackerClient.listGroups();
    }

    /**
     * @return 查询storage信息
     */
    public StorageNode queryStorage(){
        return trackerClient.getStoreStorage("group1");
    }

    public void appendFile() throws Exception{
        File file=new File("C:\\Users\\Administrator\\Desktop\\外贸1801-副本\\拼接测试.doc");

        appendFileStorageClient.appendFile("group1","M00/00/00/CooeOly4M6yADgjPAACEAIv8A1A056.doc",new FileInputStream(file),file.length());
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return  storePath.getFullPath();
    }
}
