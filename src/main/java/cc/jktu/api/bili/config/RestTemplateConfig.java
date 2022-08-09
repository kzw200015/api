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
@ConfigurationProperties("api.bili")
public class RestTemplateConfig {

    private final Map<String, String> proxies;

    @Bean
    RestTemplateContainer restTemplateContainer() {
        final Map<String, RestTemplate> restTemplateMap = new HashMap<>();
        proxies.forEach((area, proxyUri) -> restTemplateMap.put(area, createRestTemplate(proxyUri)));
        return new RestTemplateContainer(restTemplateMap);
    }

    @SneakyThrows
    private RestTemplate createRestTemplate(String proxyUri) {
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        final URI uri = URI.create(proxyUri);
        final String scheme = uri.getScheme();
        final String host = uri.getHost();
        final int port = uri.getPort();

        Proxy proxy = Proxy.NO_PROXY;
        if (scheme.equals("socks5")) {
            proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, port));
        } else if (scheme.equals("http")) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        }
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }

}
