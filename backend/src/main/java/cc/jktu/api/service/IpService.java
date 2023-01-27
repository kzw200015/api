package cc.jktu.api.service;

import cc.jktu.api.dto.IpInfo;
import cc.jktu.api.exception.IpNotFoundException;
import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.model.AsnResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
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
            if (proxyIps.length > 0) {
                ip = proxyIps[0];
            }
        }

        return ip;
    }

    @SneakyThrows
    public IpInfo getIpInfo(String ip) {
        final IpInfo ipInfo = new IpInfo();
        try {
            final InetAddress inetAddress = InetAddress.getByName(ip);
            ipInfo.setIp(ip);
            final AsnResponse asn = maxmind.asn(inetAddress);
            ipInfo.setOrg(asn.getAutonomousSystemOrganization());
            ipInfo.setAsn("AS" + asn.getAutonomousSystemNumber());
            if (inetAddress instanceof Inet4Address) {
                final IPZone ipZone = qqWry.findIP(ip);
                ipInfo.setLoc(ipZone.getMainInfo());
            }
        } catch (AddressNotFoundException e) {
            throw new IpNotFoundException(ip);
        }

        return ipInfo;
    }

}
