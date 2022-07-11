package cc.jktu.api.ip.service;

import cc.jktu.api.ip.exception.IpNotFoundException;
import cc.jktu.api.ip.model.vo.IpInfo;
import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.AsnResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

@Service
@RequiredArgsConstructor
public class IpService {

    private final DatabaseReader maxmind;
    private final QQWry qqWry;

    public String getRealIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        final String ipHeader = request.getHeader("X-Forwarded-For");
        if (ipHeader != null) {
            final String[] proxyIps = ipHeader.split(",");
            if (proxyIps.length >= 1) {
                ip = proxyIps[0];
            }
        }

        return ip;
    }

    @SneakyThrows
    public IpInfo getIpInfo(String ip) {
        final IpInfo ipInfo = new IpInfo();
        try {
            final IPZone ipZone = qqWry.findIP(ip);
            final AsnResponse asn = maxmind.asn(InetAddress.getByName(ip));
            ipInfo.setIp(ip);
            ipInfo.setLoc(ipZone.getMainInfo());
            ipInfo.setOrg(asn.getAutonomousSystemOrganization());
            ipInfo.setAsn("AS" + asn.getAutonomousSystemNumber());
        } catch (AddressNotFoundException e) {
            throw new IpNotFoundException(ip);
        }

        return ipInfo;
    }

}
