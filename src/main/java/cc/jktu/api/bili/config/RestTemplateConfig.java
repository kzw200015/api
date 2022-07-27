package cc.jktu.api.bili.config;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
@ConfigurationProperties("bili")
public class RestTemplateConfig {

    private final Map<String, String> proxies;

    @Bean("restTemplateMap")
    Map<String, RestTemplate> restTemplateMap() {
        final Map<String, RestTemplate> restTemplateMap = new HashMap<>();
        proxies.forEach((area, proxyUri) -> restTemplateMap.put(area, createRestTemplate(proxyUri)));
        return Map.copyOf(restTemplateMap);
    }

    @SneakyThrows
    private RestTemplate createRestTemplate(String proxyUri) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        final URI u = new URI(proxyUri);
        final Proxy proxy = new Proxy(Proxy.Type.valueOf(u.getScheme().substring(0, 4).toUpperCase()), new InetSocketAddress(u.getHost(), u.getPort()));
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }

}
