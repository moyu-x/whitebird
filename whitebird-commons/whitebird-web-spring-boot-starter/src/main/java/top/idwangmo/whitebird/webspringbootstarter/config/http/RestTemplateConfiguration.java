package top.idwangmo.whitebird.webspringbootstarter.config.http;

import lombok.AllArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.commons.httpclient.DefaultOkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * whitebird resttemplate 的配置，使用okhhtp进行增加配置.
 *
 * @author idwangmo
 */
@Configuration
@AllArgsConstructor
@ConditionalOnClass(OkHttpClient.class)
@EnableConfigurationProperties({FeignHttpClientProperties.class})
public class RestTemplateConfiguration {

    @Autowired
    private FeignHttpClientProperties feignHttpClientProperties;

    @Bean
    public OkHttpClientConnectionPoolFactory okHttpClientConnectionPoolFactory() {
        return new DefaultOkHttpClientConnectionPoolFactory();
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory();
    }

    /**
     * OkHttp3 链接池配置
     *
     * @return okhttp3.ConnectionPool
     */
    @Bean
    @ConditionalOnMissingBean(ConnectionPool.class)
    public okhttp3.ConnectionPool connectionPool() {
        int maxTotalConnections = feignHttpClientProperties.getMaxConnections();
        long timeToLive = feignHttpClientProperties.getTimeToLive();
        TimeUnit timeUnit = feignHttpClientProperties.getTimeToLiveUnit();
        return new DefaultOkHttpClientConnectionPoolFactory().create(maxTotalConnections, timeToLive, timeUnit);
    }

    /**
     * 配置 OkHttpClient.
     *
     * @return 连接配置
     */
    @Bean
    @ConditionalOnMissingBean(OkHttpClient.class)
    public okhttp3.OkHttpClient httpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(feignHttpClientProperties.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(feignHttpClientProperties.isFollowRedirects())
                .connectionPool(connectionPool())
                .build();
    }

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient()));
    }

}
