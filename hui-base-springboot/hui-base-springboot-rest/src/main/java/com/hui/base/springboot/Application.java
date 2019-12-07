package com.hui.base.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b><code>SpringBootApplication</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/9/30 16:51.
 *
 * @author huweihui
 * @since nile-cmszbs-szcst-be 0.1.0
 */
@SpringBootApplication(scanBasePackages = "com.hui.base.springboot")
@MapperScan("com.hui.base.springboot.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
