package cn.messycode.tree.locust.provider;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author simon.zhao
 */
public class LocustProviderBeanRegistrar {
    private static HashMap<String, String> beans = new HashMap<>();

    public static void registrar(String serviceName, String beanName){
        beans.put(serviceName, beanName);
    }

    public static String getBeanName(String serviceName){
        return beans.get(serviceName);
    }
}
