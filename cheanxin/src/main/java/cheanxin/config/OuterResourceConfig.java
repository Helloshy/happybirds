package cheanxin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 外部资源类
 * Created by 向麒 on 2017/4/19.
 */
@Configuration
public class OuterResourceConfig {
    @Value("${outerResource.brandUrl}")
    public String carBrandUrl;
    @Value("${outerResource.serieUrl}")
    public String serieUrl;
    @Value("${outerResource.typeUrl}")
    public String typeUrl;
}
