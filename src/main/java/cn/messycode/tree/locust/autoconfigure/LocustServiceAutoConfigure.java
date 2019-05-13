package cn.messycode.tree.locust.autoconfigure;

import cn.messycode.tree.locust.Constants;
import cn.messycode.tree.locust.LocustProperties;
import cn.messycode.tree.locust.api.LocustService;
import cn.messycode.tree.locust.service.LocustServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author simon.zhao
 */
@Configuration
@ConditionalOnClass(LocustService.class)
@EnableConfigurationProperties(LocustProperties.class)
@Slf4j
public class LocustServiceAutoConfigure {
    @Autowired
    private LocustProperties locustProperties;

    @Bean
    @ConditionalOnMissingBean
    public LocustService locustService(){
        log.info("[Locust] AutoConfigure LocustService Start");
        return new LocustServiceImpl();
    }
}
