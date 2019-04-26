## 项目简介

#### 本demo基于tobato的API实现
````
        <dependency>
            <groupId>com.github.tobato</groupId>
            <artifactId>fastdfs-client</artifactId>
            <version>1.26.5</version>
        </dependency>
````
fastdfs 搭建在centos7 环境中，搭建参考文章：  
https://yq.aliyun.com/articles/319069?spm=a2c4e.11153940.blogcont18092.13.90bb4adeqZRO6J

项目启动后，访问路径<br/>
http://localhost:8080/helloDfs  
页面较为简单，实现上传和下载两个功能  
其他功能直接访问相关路径：

文件信息<br/>
http://localhost:8080/fileInfo<br/>
组信息<br/>
http://localhost:8080/queryGroup<br/>
删除文件<br/>
http://localhost:8080/deleteFile<br/>
