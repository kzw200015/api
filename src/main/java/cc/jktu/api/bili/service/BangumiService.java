package cc.jktu.api.bili.service;

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

    @Qualifier("hkRestTemplate")
    private final RestTemplate hkRestTemplate;
    @Qualifier("twRestTemplate")
    private final RestTemplate twRestTemplate;

    @Qualifier("noProxyRestTemplate")
    private final RestTemplate noProxyRestTemplate;

    public ResponseEntity<String> parse(String apiPath, @RequestParam MultiValueMap<String, String> params) {
        String baseUrl = "https://api.bilibili.com";
        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl + apiPath).queryParams(params).build().encode().toUriString();

        RestTemplate restTemplate;
        final String area = params.get("area").stream().findFirst().orElse("");

        if (area.equals("hk")) {
            restTemplate = hkRestTemplate;
        } else if (area.equals("tw")) {
            restTemplate = twRestTemplate;
        } else {
            restTemplate = noProxyRestTemplate;
        }

        return restTemplate.getForEntity(url, String.class);
    }

}
