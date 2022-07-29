package cc.jktu.api.bili.service;

import cc.jktu.api.bili.config.RestTemplateContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BangumiService {

    private final RestTemplateContainer restTemplateContainer;

    public ResponseEntity<String> request(String apiPath, MultiValueMap<String, String> params) {
        String baseUrl = "https://api.bilibili.com";
        final String area = params.get("area").stream().findFirst().orElseThrow();
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + apiPath).queryParams(params);
        final RestTemplate restTemplate = restTemplateContainer.get(area);
        return restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
    }

}
