package cc.jktu.api.controller;

import cc.jktu.api.model.vo.IpInfo;
import cc.jktu.api.service.IpService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/ip")
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

    @GetMapping
    public IpInfo getIpInfo(@RequestParam(value = "ip", required = false) String ip, HttpServletRequest request) throws IOException, GeoIp2Exception {
        return ipService.getIpInfo(ip != null ? ip : ipService.getRealIp(request));
    }

}
