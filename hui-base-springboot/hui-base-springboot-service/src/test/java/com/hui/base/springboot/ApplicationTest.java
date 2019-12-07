package com.hui.base.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <b><code>SpringApplicationTest</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/10/9 15:40.
 *
 * @author huweihui
 */
@SpringBootApplication(scanBasePackages = "com.hui.base.springboot.server")
@MapperScan("com.hui.base.springboot.mapper")
@SpringBootTest
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

}
