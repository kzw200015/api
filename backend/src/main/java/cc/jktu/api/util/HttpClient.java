package cc.jktu.api.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HttpClient {

    public final RestTemplate restTemplate;

    public <T> T get(String url, Map<String, String> header, Map<String, String> queryParams, ParameterizedTypeReference<T> responseType) {
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url);
        queryParams.forEach(urlBuilder::queryParam);
        final HttpHeaders httpHeaders = new HttpHeaders();
        header.forEach(httpHeaders::add);
        final HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
        final ResponseEntity<T> responseEntity = restTemplate.exchange(urlBuilder.build().toUriString(), HttpMethod.GET, httpEntity, responseType);
        return responseEntity.getBody();
    }

    public <T> T get(String url, Map<String, String> queryParams, ParameterizedTypeReference<T> responseType) {
        return get(url, Map.of(), queryParams, responseType);
    }

    public <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        return get(url, Map.of(), Map.of(), responseType);
    }

    public <T, U> T post(String url, Map<String, String> header, Map<String, String> queryParams, U body, ParameterizedTypeReference<T> responseType) {
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url);
        queryParams.forEach(urlBuilder::queryParam);
        final HttpHeaders httpHeaders = new HttpHeaders();
        header.forEach(httpHeaders::add);
        final HttpEntity<U> httpEntity = new HttpEntity<>(body, httpHeaders);
        final ResponseEntity<T> responseEntity = restTemplate.exchange(urlBuilder.build().toUriString(), HttpMethod.POST, httpEntity, responseType);
        return responseEntity.getBody();
    }

    public <T, U> T post(String url, Map<String, String> queryParams, U body, ParameterizedTypeReference<T> responseType) {
        return post(url, Map.of(), queryParams, body, responseType);
    }

    public <T, U> T post(String url, U body, ParameterizedTypeReference<T> responseType) {
        return post(url, Map.of(), Map.of(), body, responseType);
    }

}
