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
import java.util.Map;

@Configuration
@Data
@ConfigurationProperties("bili")
public class RestTemplateConfig {

    private final Map<String, String> proxies;

    @Bean
    RestTemplate noProxyRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestTemplate hkRestTemplate() {
        return createRestTemplate("hk");
    }

    @Bean
    RestTemplate twRestTemplate() {
        return createRestTemplate("tw");
    }

    @SneakyThrows
    private RestTemplate createRestTemplate(String key) {
        final String proxyUri = proxies.get(key);
        if (proxyUri != null) {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            final URI u = new URI(proxyUri);
            final Proxy proxy = new Proxy(Proxy.Type.valueOf(u.getScheme()), new InetSocketAddress(u.getHost(), u.getPort()));
            requestFactory.setProxy(proxy);
            return new RestTemplate(requestFactory);
        } else {
            return new RestTemplate();
        }
    }

}
