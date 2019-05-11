package cn.messycode.tree.locust;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = Constants.PREFIX)
@Data
public class LocustProperties {
    /**
     * 是否开启
     */
    private Boolean enabled = true;
}
