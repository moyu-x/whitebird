package top.idwangmo.whitebird.springbootadmin.client;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * bearer 头部支持.
 *
 * @author idwangmo
 */
@AllArgsConstructor
public class BearerAuthHeaderProvider implements HttpHeadersProvider {

    private final OAuth2RestTemplate template;

    @Override
    public HttpHeaders getHeaders(Instance instance) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                template.getAccessToken().getTokenType() + " " + template.getAccessToken().getValue());
        return headers;
    }

}
