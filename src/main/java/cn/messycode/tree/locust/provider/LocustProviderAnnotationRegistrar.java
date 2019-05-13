package cn.messycode.tree.locust.provider;

import cn.messycode.tree.locust.Constants;
import cn.messycode.tree.locust.LocustProperties;
import cn.messycode.tree.locust.api.LocustService;
import cn.messycode.tree.locust.consumer.LocustConsumerAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
@ConditionalOnProperty(name = Constants.ENABLED, matchIfMissing = true)
@EnableConfigurationProperties(LocustProperties.class)
@AutoConfigureBefore(LocustConsumerAutoConfiguration.class)
@Slf4j
public class LocustProviderAnnotationRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanClassLoaderAware {
    private ClassLoader classLoader;

    private Environment environment;

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
        log.info("n1:[{}]", n1);

        Set<String> types = annotationMetadata.getAnnotationTypes();
        for(String name : types){
            log.info("Name:[{}]", name);
        }
    }
}
