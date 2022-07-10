package cc.jktu.api.ip.controller;

import cc.jktu.api.ip.model.vo.IpInfo;
import cc.jktu.api.ip.service.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ip")
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

    @GetMapping
    public IpInfo getIpInfo(@RequestParam(value = "ip", required = false) String ip, HttpServletRequest request) {
        return ipService.getIpInfo(ip != null ? ip : ipService.getRealIp(request));
    }

}
