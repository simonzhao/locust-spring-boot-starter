package cn.messycode.tree.locust.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author simon.zhao
 */
@Component
@Configuration
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static void setContext(ApplicationContext context){
        SpringContextUtil.applicationContext = context;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static String getBeanName(String type) throws BeansException {
        try {
            Class tClass = Class.forName(type);
            return applicationContext.getBeanNamesForType(tClass)[0];
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
