package cheanxin.config;

import cheanxin.constant.ConfigConstants;
import cheanxin.web.ProductController;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import static org.springframework.http.HttpMethod.*;

/**
 * Auth2 资源配置类
 * Created by Jawinton on 16/12/24.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 配置唯一资源ID
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ConfigConstants.RESOURCE_ID).stateless(false);
    }

    /**
     * 配置资源访问权限
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers(GET, "/users/**").hasAuthority("") // role name should start with ROLE_

                .antMatchers(GET, "/users/me").authenticated()
                .antMatchers(PATCH, "/users/password").authenticated()
                .antMatchers(DELETE, "/users/logout").authenticated()

                .antMatchers("/user_posts/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/users/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/posts/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/post_types/**").hasAuthority("ROLE_ADMIN")

                .antMatchers("/depts/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/dept_cities/**").hasAuthority("ROLE_ADMIN")

                .antMatchers(GET, "/product_templates").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_TEMPLATE_READ")
                .antMatchers(GET, "/product_templates/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_TEMPLATE_READ")
                .antMatchers(POST, "/product_templates").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_TEMPLATE_UPDATE")
                .antMatchers(PATCH, "/product_templates/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_TEMPLATE_UPDATE")
                .antMatchers(DELETE, "/product_templates/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_TEMPLATE_UPDATE")

                .antMatchers(GET, "/products").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_READ,ROLE_PRODUCT_CITY_READ,ROLE_FIRST_REVIEWER,ROLE_SECOND_REVIEWER")
                .antMatchers(GET, "/products/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_READ,ROLE_PRODUCT_CITY_READ,ROLE_FIRST_REVIEWER,ROLE_SECOND_REVIEWER")
                .antMatchers(POST, "/products").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_UPDATE")
                .antMatchers(PUT, "/products/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_UPDATE")
                .antMatchers(DELETE, "/products/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_UPDATE")
                .antMatchers(PATCH, "/products/**").hasAnyAuthority("ROLE_ADMIN,ROLE_PRODUCT_REVIEW")

                .antMatchers(POST, "/loans").hasAnyAuthority("ROLE_ADMIN,ROLE_LOAN_DRAFT_UPDATE")
                .antMatchers(PATCH, "/loans/**/release").hasAnyAuthority("ROLE_ADMIN,ROLE_FINANCE")

                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
