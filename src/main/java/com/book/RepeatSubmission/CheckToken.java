package com.book.RepeatSubmission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ${罗显} on 2018/10/22
 * 解决接口幂等性问题,支持网络延迟和表单提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {

    //区分请求来源
    String type();
}
