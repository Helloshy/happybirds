package cheanxin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jawinton on 16/12/13.
 * 不扫描@EnableWebMvc注解的类：@EnableWebMvc已经交给WebApplicationInitializer加载，即WebConfig类。
 */
@Configuration
@ComponentScan(basePackages = {"cheanxin"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}
