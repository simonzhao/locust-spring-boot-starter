package cn.messycode.tree.locust.provider;

import cn.messycode.tree.locust.Constants;
import cn.messycode.tree.locust.LocustProperties;
import cn.messycode.tree.locust.api.LocustService;
import cn.messycode.tree.locust.consumer.LocustConsumerAutoConfiguration;
import cn.messycode.tree.locust.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@ConditionalOnProperty(name = Constants.ENABLED, matchIfMissing = true)
@EnableConfigurationProperties(LocustProperties.class)
@AutoConfigureBefore(LocustConsumerAutoConfiguration.class)
@Slf4j
public class LocustProviderAnnotationRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanClassLoaderAware {
    private ClassLoader classLoader;

    private Environment environment;

    @Autowired
    private ApplicationContext context;

    @Resource
    private LocustService locustServiceImpl;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        log.info("[Locust] Provider Registrar START");
        String n1 = annotationMetadata.getClassName();

        Set<String> types = annotationMetadata.getAnnotationTypes();
        if(types.size() != 1){
            log.info("[Locust] 实现的接口数量不等于1");
            return;
        }
        String annotationName = types.iterator().next();
        Map<String, Object> attr = annotationMetadata.getAnnotationAttributes(annotationName);
        String interfaceName = ((Class) attr.get("serviceInterface")).getName();
        String version = (String)attr.get("serviceVersion");

        String beanName = "";
        String[] beanNameList = beanDefinitionRegistry.getBeanDefinitionNames();
        for(String bean : beanNameList){
            String beanClassName = beanDefinitionRegistry.getBeanDefinition(bean).getBeanClassName();
            if(n1.equals(beanClassName)){
                beanName = bean;
                break;
            }
        }

        LocustProviderBeanRegistrar.registrar(interfaceName, beanName);

        log.info("[Locust] Service:[{}] Version:[{}] bean:[{}]", interfaceName, version, beanName);
    }
}
