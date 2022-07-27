package cc.jktu.api.bili.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
public class RestTemplateContainer {

    private final Map<String, RestTemplate> restTemplateMap;

    public RestTemplate get(String area) {
        return restTemplateMap.getOrDefault(area, new RestTemplate());
    }

}