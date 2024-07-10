package org.team.springboot.configure;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.team.springboot.aysncTask.GoodsService;
import org.team.springboot.booster.BootTempApp;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangds
 * @date 2024/7/10
 * @Notes
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootTempApp.class)
@WebAppConfiguration
@Slf4j
public class TestAsyncTask {

    @Resource
    GoodsService goodsService;

    @Test
    public void testAsyncGoodsServiceDemo() throws InterruptedException, ExecutionException {
        long starTime = System.currentTimeMillis();
        System.out.println("开始获取商品的各种信息");
        long goodsId = 1L;
        Future<String> goodsInfoFuture = goodsService.getGoodsInfo(goodsId);
        Future<String> goodsDescFuture = goodsService.getGoodsDesc(goodsId);
        Future<List<String>> goodsCommentsFuture = goodsService.getGoodsComments(goodsId);
        System.out.println(goodsInfoFuture.get());
        System.out.println(goodsDescFuture.get());
        System.out.println(goodsCommentsFuture.get());

        System.out.println("商品信息获取完毕,总耗时(ms)：" + (System.currentTimeMillis() - starTime));

        //休眠一下，防止@Test退出
        TimeUnit.SECONDS.sleep(3);
    }
}
