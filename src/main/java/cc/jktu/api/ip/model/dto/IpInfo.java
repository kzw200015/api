package cc.jktu.api.ip.model.dto;

import lombok.Data;

@Data
public class IpInfo {

    private String ip;
    private String loc;
    private String org;
    private String asn;

}
