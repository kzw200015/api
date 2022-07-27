package cc.jktu.api.bili.service;

import cc.jktu.api.bili.config.RestTemplateContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BangumiService {

    @Qualifier("restTemplateMap")
    private final RestTemplateContainer restTemplateContainer;

    public ResponseEntity<String> parse(String apiPath, @RequestParam MultiValueMap<String, String> params) {
        String baseUrl = "https://api.bilibili.com";
        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl + apiPath).queryParams(params).build().encode().toUriString();
        final String area = params.get("area").stream().findFirst().orElseThrow();
        final RestTemplate restTemplate = restTemplateContainer.get(area);
        return restTemplate.getForEntity(url, String.class);
    }

}
