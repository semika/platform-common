package iit.ase.cw.platform.common.context.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableConfigurationProperties(ThaproContextProperties.class)
public class ThaproContextAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private ThaproContextProperties thaproContextProperties;

    @Value("${spring.application.name}")
    private String moduleName;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ThaproSearchFilterBinder(thaproContextProperties, moduleName));
    }
}
