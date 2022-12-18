package cc.jktu.api.config;

import cc.jktu.api.advice.AuthInterceptor;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.html", "/*/*.css", "/*/*.js").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures();
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(0, converter);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
