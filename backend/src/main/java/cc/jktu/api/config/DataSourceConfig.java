package cc.jktu.api.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    DataSource dataSource() {
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setValidationQuery("SELECT 1");
        return druidDataSource;
    }

}
