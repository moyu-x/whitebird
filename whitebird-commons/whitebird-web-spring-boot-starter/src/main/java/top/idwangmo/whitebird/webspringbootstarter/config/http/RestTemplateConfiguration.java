package top.idwangmo.whitebird.webspringbootstarter.config.http;

import lombok.AllArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.httpclient.DefaultOkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author idwangmo
 */
@Configuration
@AllArgsConstructor
@ConditionalOnClass(OkHttpClient.class)
@EnableConfigurationProperties({FeignHttpClientProperties.class})
public class RestTemplateConfiguration {

    @Bean
    public OkHttpClientConnectionPoolFactory okHttpClientConnectionPoolFactory() {
        return new DefaultOkHttpClientConnectionPoolFactory();
    }

    /**
     * OkHttp3 链接池配置
     *
     * @param feignHttpClientProperties         httpClient 配置
     * @param okHttpClientConnectionPoolFactory 链接池配置
     * @return okhttp3.ConnectionPool
     */
    @Bean
    @ConditionalOnMissingBean(ConnectionPool.class)
    public okhttp3.ConnectionPool connectionPool(FeignHttpClientProperties feignHttpClientProperties,
                                                 OkHttpClientConnectionPoolFactory okHttpClientConnectionPoolFactory) {
        int maxTotalConnections = feignHttpClientProperties.getMaxConnections();
        long timeToLive = feignHttpClientProperties.getTimeToLive();
        TimeUnit timeUnit = feignHttpClientProperties.getTimeToLiveUnit();
        return okHttpClientConnectionPoolFactory.create(maxTotalConnections, timeToLive, timeUnit);
    }

    /**
     * 配置 OkHttpClient.
     *
     * @param connectionPool       connectionPool
     * @param httpClientProperties httpClientProperties
     * @return 连接配置
     */
    @Bean
    @ConditionalOnMissingBean(OkHttpClient.class)
    public okhttp3.OkHttpClient httpClient(ConnectionPool connectionPool,
                                           FeignHttpClientProperties httpClientProperties) {
        return new OkHttpClient().newBuilder()
                .connectTimeout(httpClientProperties.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(httpClientProperties.isFollowRedirects())
                .connectionPool(connectionPool)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate(OkHttpClient httpClient) {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient));
    }

}
