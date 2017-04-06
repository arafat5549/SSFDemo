package com.ssf.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 
 * 注解是一种”被动“的信息。也就是说，必须由编译器或虚拟机来“主动”解析它，它才能发挥自己的作用。
 * @author wyy
 * 2017年4月6日
 *
 */

//元注解
@Target({ElementType.METHOD})       //描述你可在哪些使用这个这个注解
@Retention(RetentionPolicy.RUNTIME) //生命周期
@Documented                         //JAVADOC生成文档注解
//@Inherited                        //该注解被自动继承(一般不使用它)
public @interface TestAnno {//定义注解使用@interface关键字

}
