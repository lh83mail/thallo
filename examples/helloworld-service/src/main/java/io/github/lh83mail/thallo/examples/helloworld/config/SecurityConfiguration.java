package io.github.lh83mail.thallo.examples.helloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Configuration
public class SecurityConfiguration {
    private final OAuth2ResourceServerProperties.Jwt properties;

    @Autowired
    public SecurityConfiguration(OAuth2ResourceServerProperties properties) {
        this.properties = properties.getJwt();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.security.oauth2.resourceserver.jwt.jwk-set-uri")
    JwtDecoder jwtDecoderByJwkKeySetUri() {
        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withJwkSetUri(this.properties.getJwkSetUri())
                .jwsAlgorithm(SignatureAlgorithm.from(this.properties.getJwsAlgorithm())).build();
        String issuerUri = this.properties.getIssuerUri();
        if (issuerUri != null) {
            nimbusJwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuerUri));
        }
        nimbusJwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
        return nimbusJwtDecoder;
    }

    public class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {
        private final MappedJwtClaimSetConverter delegate =
                MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

        public Map<String, Object> convert(Map<String, Object> claims) {
            Map<String, Object> convertedClaims = this.delegate.convert(claims);

            String username = (String) convertedClaims.get("user_name");
            convertedClaims.put("sub", username);
            if (convertedClaims.containsKey("authorities")) {
                convertedClaims.get("authorities");
            }
            return convertedClaims;
        }
    }

    @Configuration
    public static class InnerWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
            http.oauth2ResourceServer( oauth2 -> oauth2
                .jwt(jwt -> {
                    JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                    JwtGrantedAuthoritiesConverter xrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                    xrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
                    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
                    converter.setJwtGrantedAuthoritiesConverter(s -> {
                        Collection<GrantedAuthority>  collection = grantedAuthoritiesConverter.convert(s);
                        collection.addAll(xrantedAuthoritiesConverter.convert(s));
                        return collection;
                    });
                    jwt.jwtAuthenticationConverter(converter);
                })
            );
        }
    }
}
