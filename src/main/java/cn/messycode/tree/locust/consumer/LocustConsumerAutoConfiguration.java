package cn.messycode.tree.locust.consumer;

import cn.messycode.tree.locust.Constants;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author simon
 */
@Configuration
@ConditionalOnProperty(name = Constants.ENABLED, matchIfMissing = true)
public class LocustConsumerAutoConfiguration {

    public static BeanFactoryPostProcessor locustConsumerPostProcessor() {
        return new LocustConsumerPostProcessor();
    }
}
