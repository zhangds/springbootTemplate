package org.team.springboot.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author zhangds
 * @date 2024/7/10
 * @Notes
 **/
@Configuration
public class SchedulerConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10); // 设置线程池大小
        scheduler.setThreadNamePrefix("scheduler-"); // 设置线程名前缀
        scheduler.setThreadFactory(new NamedThreadFactory("Scheduler-")); // 使用自定义线程工厂
        return scheduler;
    }

    public class NamedThreadFactory extends CustomizableThreadFactory {
        public NamedThreadFactory(String threadNamePrefix) {
            super(threadNamePrefix);
        }
    }
}
