package cn.messycode.tree.locust.annotation;

import cn.messycode.tree.locust.Constants;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务声明注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface LocustProvider {
    String value() default "";

    Class<?> serviceInterface() default Object.class;

    String serviceVersion() default Constants.DEFAULT_VERSION;
}
