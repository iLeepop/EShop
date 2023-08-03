package com.ilee.elecshop.controller;

import com.ilee.elecshop.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/1")
    public R test() {
        return R.ok("TESTOK");
    }
}
