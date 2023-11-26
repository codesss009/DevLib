package com.luv2code.springbootlibrary.config;

import com.luv2code.springbootlibrary.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
        private String allowedOrigins = "http://localhost:3000";


        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry){
            HttpMethod[] unsupportedActions = {HttpMethod.DELETE, HttpMethod.PATCH,
                                                HttpMethod.POST, HttpMethod.PUT};
            config.exposeIdsFor(Book.class);
            disableHttpMethods(Book.class, config, unsupportedActions);

            corsRegistry.addMapping(config.getBasePath() + "/**")
                    .allowedOrigins(allowedOrigins);
        }

   private void disableHttpMethods(Class bookClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions){
            config.getExposureConfiguration()
                    .forDomainType(bookClass)
                    .withItemExposure((metdata, httpMethods) ->
                            httpMethods.disable(unsupportedActions));
    }
}
