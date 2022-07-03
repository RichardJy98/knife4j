package com.richard;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import java.lang.management.ManagementFactory;

/**
 * @author Jay
 * @version 1.0
 * @description TODO
 * @date 2022/4/4 23:08
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.richard.mapper")
public class KnifeApplication implements ApplicationRunner {

    @Autowired
    public Environment env;

    public static void main(String[] args) {
        SpringApplication.run(KnifeApplication.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //记录开启时间
        log.warn("【项目启动时间】: {}, 【项目PID】: {}", DateUtil.now(), ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        ////记录项目启动的环境
        log.warn("【项目环境】: {}", env.getProperty("spring.profiles.active"));
    }

    @PreDestroy
    public void destory() {
        //记录结束时间
        log.warn("【项目关闭时间】: {}, 【项目PID】: {}", DateUtil.now(), ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }
}
