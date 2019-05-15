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


window10使用docker部署fastdfs
使用delron/fastdfs镜像
```
1、自定义创建桥接模式
docker network create --driver bridge --subnet=172.16.0.0/16 --gateway=172.16.0.1 mybridge 
2、查看自定义桥接模式网络信息
docker network inspect mybridge
3、拉取镜像
docker image pull delron/fastdfs
4、启动tracker容器（执行命令前先创建路径 D:\fdfs\tracker|storage，该路径可自行指定）
docker run -dit --name tracker -d -p 22122:22122  -v D:\fdfs\tracker:/var/fdfs delron/fastdfs tracker
5、启动storage容器 10.138.30.132为自己本机（docker宿主机）实际ip,不要使用127.0.0.1，否则启动报错，固定ip地址（172.16.0.12为mybridge的任一网址）
docker run -dti --name storage  --privileged --net mybridge   --ip 172.16.0.12 -d -p 23000:23000 -e TRACKER_SERVER=10.138.30.178:22122 -v D:\fdfs\storage:/var/fdfs delron/fastdfs storage
6、添加路由 172.17.0.0为docker默认容器ip段，255.255.255.0为子网掩码;10.0.75.2为docker暴露出的ip
route -p add 172.17.0.0 MASK 255.255.255.0 10.0.75.2
route -p add 172.16.0.0 MASK 255.255.255.0 10.0.75.2
```

