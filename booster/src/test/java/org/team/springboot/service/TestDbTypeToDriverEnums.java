package org.team.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.team.springboot.booster.BootTempApp;
import org.team.springboot.enums.DbTypeToDriverEnums;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTempApp.class)
@WebAppConfiguration
@Slf4j
public class TestDbTypeToDriverEnums {

    @Test
    public void testFindType(){
        DbTypeToDriverEnums mysqlType = DbTypeToDriverEnums.findByDbType("mysql");
        Assert.assertNotNull(mysqlType);
        Assert.assertNotNull(mysqlType.getDriverClass());
        System.out.println(mysqlType.getDbType() + ":" + mysqlType.getDriverClass());
    }

}
