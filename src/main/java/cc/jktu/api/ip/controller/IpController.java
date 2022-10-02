package cc.jktu.api.ip.controller;

import cc.jktu.api.ip.model.dto.IpInfo;
import cc.jktu.api.ip.service.IpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ip")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IpController {

    private final IpService ipService;

    @GetMapping
    public IpInfo getIpInfo(@RequestParam(value = "ip", required = false) String ip, HttpServletRequest request) {
        return ipService.getIpInfo(ip != null ? ip : ipService.getRealIp(request));
    }

}
