package com.ilee.elecshop.exception;


import com.ilee.elecshop.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GloBalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(HttpServletRequest request, Exception e) {
        System.out.println("进入全局异常处理器");
        return R.error("系统异常,请联系管理员" + "<br>" + e.getMessage());
    }
}
