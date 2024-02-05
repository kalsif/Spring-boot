package com.example.esercizio13.configurations;

import com.example.esercizio13.interceptors.APILoggingInterceptor;
import com.example.esercizio13.interceptors.LegacyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MVCConfiguration implements WebMvcConfigurer {

    @Autowired
    private APILoggingInterceptor apiLoggingInterceptor;
    @Autowired
    private LegacyInterceptor legacyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor);
        registry.addInterceptor(legacyInterceptor);
    }
}
