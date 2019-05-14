package cn.messycode.tree.locust.annotation;

import cn.messycode.tree.locust.Constants;
import cn.messycode.tree.locust.provider.LocustProviderAnnotationRegistrar;
import cn.messycode.tree.locust.provider.LocustProviderBeanRegistrar;
import cn.messycode.tree.locust.util.SpringContextUtil;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务声明注解
 */
@Component
@Import({LocustProviderAnnotationRegistrar.class, SpringContextUtil.class, LocustProviderBeanRegistrar.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LocustProvider {
    String value() default "";

    Class<?> serviceInterface() default Object.class;

    String serviceVersion() default Constants.DEFAULT_VERSION;
}
