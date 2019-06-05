package com.hui.base.thread.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>TestController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/4/30 14:31.
 *
 * @author Hu-Weihui
 * @since hui-base-thread ${PROJECT_VERSION}
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
