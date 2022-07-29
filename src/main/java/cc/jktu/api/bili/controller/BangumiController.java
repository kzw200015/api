package cc.jktu.api.bili.controller;

import cc.jktu.api.bili.service.BangumiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bili")
@RequiredArgsConstructor
@CrossOrigin(value = "https://www.bilibili.com", allowCredentials = "true")
public class BangumiController {

    private final BangumiService bangumiService;
    private final String bangumiParsePathForWeb = "/pgc/player/web/playurl";
    private final String bangumiParsePathForApi = "/pgc/player/api/playurl";

    @GetMapping(bangumiParsePathForWeb)
    public ResponseEntity<String> parseWeb(@RequestParam MultiValueMap<String, String> params) {
        return bangumiService.request(bangumiParsePathForWeb, params);
    }

    @GetMapping(bangumiParsePathForApi)
    public ResponseEntity<String> parseApi(@RequestParam MultiValueMap<String, String> params) {
        return bangumiService.request(bangumiParsePathForApi, params);
    }

}
