package org.team.springboot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhangds
 * @date 2024/7/10
 * @Notes
 **/
@Component
public class ScheduledTaskDemo {

    /**
     * 需要启动类添加 @EnableScheduling
     * @Scheduled中的参数可以支持更多的类型
     * @throws InterruptedException
     */
    @Scheduled(cron="*/10 * * * * ?")
    public void push() throws InterruptedException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String b = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(Thread.currentThread().getName() +"模拟推送消息，" + System.currentTimeMillis() + " || " +b);
    }

}
