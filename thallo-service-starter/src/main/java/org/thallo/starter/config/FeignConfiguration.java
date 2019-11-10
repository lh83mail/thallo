package org.thallo.starter.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfiguration {
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }
//@Bean
//@Scope("prototype")
//public Feign.Builder feignBuilder() {
//    return Feign.builder();
//}
    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(),
//                clientCredentialsResourceDetails());
        return (template) -> {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                String x = request.getHeader("Authorization");
                if (x != null) {
                    template.header("Authorization", x);
                }
            }
        };
    }

//    @Bean
//    @ConfigurationProperties(prefix = "security.oauth2.client")
//    private OAuth2ProtectedResourceDetails clientCredentialsResourceDetails() {
//        return new ClientCredentialsResourceDetails();
//    }
//
//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate() {
//        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//    }
}
