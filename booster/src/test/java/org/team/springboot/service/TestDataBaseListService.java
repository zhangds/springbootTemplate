package org.team.springboot.service;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.team.springboot.booster.BootTempApp;
import org.team.springboot.dao.DataBaseDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTempApp.class)
@WebAppConfiguration
@Slf4j
public class TestDataBaseListService {

    @Resource
    DataBaseListService dataBaseListService;

    @Test
    public void testGetAllDbList(){
        try {
            List<DataBaseDao> all = dataBaseListService.getAllDbList();
            Assert.assertNotNull(all);
            all.forEach(value -> System.out.println(JSON.toJSONString(value)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDbInfo(){
        String dbId = "METADB";
        DataBaseDao all = dataBaseListService.getDbInfo(dbId);
        Assert.assertNotNull(all);
        Assert.assertEquals(all.getCnName(), "元数据库");
        System.out.println(all.getCnName());
        all = dataBaseListService.getDbInfo(dbId+"111");
        Assert.assertNull(all);
    }
}
