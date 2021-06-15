package com.data.collect;

import com.core.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.core", "com.machine","com.security"})
@MapperScan("com.machine.collect.mapper")
public class DataCollectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DataCollectApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
        System.out.println("start success！");
    }

}
