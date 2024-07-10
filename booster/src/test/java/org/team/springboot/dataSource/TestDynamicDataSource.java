package org.team.springboot.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.team.springboot.booster.BootTempApp;
import org.team.springboot.configure.ApplicationContextProvider;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangds
 * @date 2024/7/9
 * @Notes
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTempApp.class)
@WebAppConfiguration
@Slf4j
public class TestDynamicDataSource {

    @Resource
    DynamicDataSource dynamicDataSource;
    @Test
    public void test_getConnection(){
        try {
            DynamicDataSource _d = ApplicationContextProvider.getApplicationContext().getBean("dynamicDataSource", DynamicDataSource.class);
            Assert.assertNotNull(_d);
            JdbcTemplate jdbcTemplate = dynamicDataSource.getJdbcTemplate("");
            Assert.assertNotNull(jdbcTemplate);
            String sql = "select * from sys_configs WHERE keyid=?";
            jdbcTemplate.query(sql, new Object[]{"CLOUD_FTP_IP"}, rs -> {
                String id = rs.getString(1);
                String name = rs.getString(2);
                System.out.println("ID: " + id + ", Name: " + name);
            });
            jdbcTemplate = dynamicDataSource.getJdbcTemplate("test");
            sql = "select * from cj_column_rela";
            jdbcTemplate.query(sql, rs -> {
                String id = rs.getString(1);
                String name = rs.getString(2);
                System.out.println("ID: " + id + ", Name: " + name);
            });
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
