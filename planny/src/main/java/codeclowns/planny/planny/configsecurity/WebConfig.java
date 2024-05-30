package codeclowns.planny.planny.configsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/js/**")
                .addResourceLocations("classpath:/static/user/js/")
                .setCachePeriod(3600)
                .resourceChain(true);
    }
}
