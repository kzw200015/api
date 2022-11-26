package cc.jktu.api.config;

import com.github.jarod.qqwry.QQWry;
import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class IpConfig {

    @Bean
    QQWry qqWry() throws IOException {
        final ClassPathResource resource = new ClassPathResource("/ip/qqwry.dat");
        return new QQWry(resource.getInputStream().readAllBytes());
    }

    @Bean
    DatabaseReader databaseReader() throws IOException {
        final ClassPathResource resource = new ClassPathResource("/ip/asn.mmdb");
        return new DatabaseReader.Builder(resource.getInputStream()).withCache(new CHMCache()).build();
    }

}
