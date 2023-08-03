package com.ilee.elecshop.config;

import com.ilee.elecshop.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/swiper/**")
        .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\swiper\\");
        registry.addResourceHandler("/image/bigtype/**")
                .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\BigType\\");
        registry.addResourceHandler("/image/hotImage/**")
                .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\hotImage\\");
        registry.addResourceHandler("/image/productSwiperImgs/**")
                .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\productSwiperImgs\\");
        registry.addResourceHandler("/image/productIntroImgs/**")
                .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\productIntroImgs\\");
        registry.addResourceHandler("/image/productParaImgs/**")
                .addResourceLocations("file:F:\\Ilee_Projects\\weDev\\eshopResources\\productParaImgs\\");

    }

    @Bean
    public SysInterceptor sysInterceptor(){
        return new SysInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String pathPatterns[] = {"/adminLogin","/product/**","/bigtype/**","/user/wxlogin","/weixinpay/**"};
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(pathPatterns);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(CorsConfiguration.ALL)
                .maxAge(3600);
    }
}
