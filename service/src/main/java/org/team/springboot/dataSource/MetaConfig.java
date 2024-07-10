package org.team.springboot.dataSource;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangds
 * @date 2024/7/9
 * @Notes
 **/
@Configuration
@ConfigurationProperties(prefix="spring.datasource.primary")
@Data
public class MetaConfig {

    String jdbcUrl;
    String driverClassName;
    String userName;
    String password;
    @Value("${dataSource.idlePeriodTime:800000}")
    long idlePeriodTime;


}
