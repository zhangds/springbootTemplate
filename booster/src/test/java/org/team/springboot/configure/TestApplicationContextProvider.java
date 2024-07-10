package org.team.springboot.configure;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.team.springboot.booster.BootTempApp;
import org.team.springboot.dataSource.MetaConfig;

/**
 * @author zhangds
 * @date 2024/7/9
 * @Notes
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTempApp.class)
@WebAppConfiguration
@Slf4j
public class TestApplicationContextProvider {

    @Test
    public void testGetBean(){
        Object o = ApplicationContextProvider.getApplicationContext().getBean("metaConfig");
        Assert.assertNotNull(o);
        MetaConfig x = ApplicationContextProvider.getApplicationContext().getBean("metaConfig", MetaConfig.class);
        Assert.assertNotNull(x);
    }
}
