package cc.jktu.api;

import cc.jktu.api.dao.entity.Post;
import cc.jktu.api.dao.mapper.PostMapper;
import cc.jktu.api.util.BcryptUtil;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

@SpringBootTest
public class ApiApplicationTest {

    @Autowired
    private PostMapper postMapper;

    void updateIpDatabase() throws IOException {
        final RestTemplate restTemplate = new RestTemplate();
        final byte[] qqWryData = restTemplate.getForObject("https://raw-github.jktu.cc/out0fmemory/qqwry.dat/master/qqwry_lastest.dat", byte[].class);
        Files.write(Paths.get("src/main/resources/ip/qqwry.dat"), qqWryData);

        final Resource asnData = restTemplate.getForObject("https://download.maxmind.com/app/geoip_download?edition_id=GeoLite2-ASN&license_key=GnT0RmCSbcoQXiMg&suffix=tar.gz", Resource.class);
        try (final TarArchiveInputStream stream = new TarArchiveInputStream(new GzipCompressorInputStream(asnData.getInputStream()))) {
            ArchiveEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                if (Paths.get(entry.getName()).getFileName().toString().equals("GeoLite2-ASN.mmdb")) {
                    Files.write(Paths.get("src/main/resources/ip/asn.mmdb"), stream.readAllBytes());
                }
            }
        }
    }

    //    @Test
    void genPassword() {
        System.out.println(BcryptUtil.hashPassword("123321"));
    }

    //    @Test
    void insertTestData() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            final Post post = new Post();
            post.setId(i);
            post.setTitle("title" + i);
            post.setContent("content" + i);
            post.setUpdateTime(Instant.now().getEpochSecond());
            post.setCreateTime(Instant.now().getEpochSecond());
            post.setUserId(1);
            postMapper.insert(post);
            Thread.sleep((long) (1000 * 0.5));
        }
    }

}
