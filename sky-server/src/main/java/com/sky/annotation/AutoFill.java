package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//一些固定的要加的注解，来定义aop
@Target(ElementType.METHOD)
@Retention( RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // 定义一个枚举类型，来表示具体的操作类型
    OperationType  value();
}
