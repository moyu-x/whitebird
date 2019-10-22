package top.idwangmo.whitebird.demoservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * jwt 配置类.
 *
 * @author idwangmo
 */
@Configuration
public class JwtConfiguration {

    public static final String PUBLIC_CERT = "public.cert";

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource(PUBLIC_CERT);

        try {
            String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
            converter.setVerifierKey(publicKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return converter;
    }
}
