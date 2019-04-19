package com.lychao163.fastdfsdemo;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FdfsClientConfig.class)
public class FastdfsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsDemoApplication.class, args);
    }

}
