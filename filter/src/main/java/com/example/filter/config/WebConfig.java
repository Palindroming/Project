    package com.example.filter.config;

    import com.example.filter.Inteceptor.OpenAPIInteceptor;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Configurable;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

    @Autowired
     private OpenAPIInteceptor openAPIInteceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(openAPIInteceptor)
//                    .addPathPatterns("/**");
        }
    }
