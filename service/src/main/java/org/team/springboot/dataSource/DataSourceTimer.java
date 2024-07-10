package org.team.springboot.dataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.team.springboot.configure.ApplicationContextProvider;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes 动态数据源定时器管理。长时间无访问的数据库连接关闭
 **/

public class DataSourceTimer {
    /**
     * 空闲时间周期。超过这个时长没有访问的数据库连接将被释放。默认为10分钟。
     */
    private static long idlePeriodTime = ApplicationContextProvider.getApplicationContext().getBean("metaConfig", MetaConfig.class).getIdlePeriodTime();

    /**
     * 动态数据源
     */
    private HikariDataSource dataSource;

    /**
     * 上一次访问的时间
     */
    private long lastUseTime;

    public DataSourceTimer(HikariDataSource dds) {
        this.dataSource = dds;
        this.lastUseTime = System.currentTimeMillis();
    }

    /**
     * 更新最近访问时间
     */
    public synchronized void refreshTime() {
        lastUseTime = System.currentTimeMillis();
    }

    /**
     * 检测数据连接是否超时关闭。
     *
     * @return true-已超时关闭; false-未超时
     */
    public synchronized boolean checkAndClose() {
        if (System.currentTimeMillis() - lastUseTime > idlePeriodTime) {
            dataSource.close();
            return true;
        }
        return false;
    }

    public synchronized HikariDataSource getDataSource() {
        this.lastUseTime = System.currentTimeMillis();
        return dataSource;
    }
}
