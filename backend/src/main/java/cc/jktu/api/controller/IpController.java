package cc.jktu.api.controller;

import cc.jktu.api.dto.IpInfo;
import cc.jktu.api.service.IpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



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
