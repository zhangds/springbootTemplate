package org.team.springboot.dataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.team.springboot.configure.ApplicationContextProvider;
import org.team.springboot.dao.DataBaseDao;
import org.team.springboot.service.DataBaseListService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangds
 * @date 2024/7/9
 * @Notes
 **/

public class DynamicSourceManager {

    private static Map<String, DataSourceTimer> dataSourceMap = new HashMap<String, DataSourceTimer>(10);
    private static Map<String, HikariConfig> configMap = new HashMap<String, HikariConfig>(10);

    public Map<String, DataSourceTimer> getDataSourceMap() {
        return this.dataSourceMap;
    }

    public Map<String, HikariConfig> getConfigMap() {
        return this.configMap;
    }

    private DynamicSourceManager() {
        MetaConfig metaInfo = ApplicationContextProvider.getApplicationContext().getBean("metaConfig", MetaConfig.class);
        HikariConfig config = this.initHikariConfig(metaInfo);
        HikariDataSource hds = this.initHikariDataSource(config);
        configMap.put(config.getPoolName(), config);
        dataSourceMap.put(config.getPoolName(), new DataSourceTimer(hds));

    }

    public HikariDataSource getDataSource(String key) {
        key = StringUtils.isEmpty(key) ? "default" : key.trim();
        key = StringUtils.equals(key, "") ? "default" : key;
        if (ObjectUtils.isNotEmpty(this.getDataSourceMap().get(key))) {
            return this.getDataSourceMap().get(key).getDataSource();
        }
        // 自动注入新的dbId
        DataBaseDao dbInfo = ApplicationContextProvider.getApplicationContext().getBean("dataBaseListMapper", DataBaseListService.class).getDbInfo(key);
        HikariConfig config = this.initHikariConfig(key, dbInfo);
        HikariDataSource hds = this.initHikariDataSource(config);
        configMap.put(config.getPoolName(), config);
        dataSourceMap.put(config.getPoolName(), new DataSourceTimer(hds));
        return hds;
    }

    private HikariConfig initHikariConfig(String driverCls, String uri,
                                          String key, String user, String pwd){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverCls);
        config.setJdbcUrl(uri);
        config.setPoolName(key);
        config.setUsername(user);
        config.setPassword(pwd);
        // 初始连接数
        config.setMinimumIdle(5);
        // 最大连接数
        config.setMaximumPoolSize(20);
        config.setConnectionTestQuery("SELECT 1");
        // 空闲连接回收时间（5 分钟）
        config.setIdleTimeout(300000);
        // 连接最大生命周期（15 分钟）
        config.setMaxLifetime(900000);
        // 连接获取超时时间（10 秒）
        config.setConnectionTimeout(10000);
        return config;
    }

    private HikariConfig initHikariConfig(String key, DataBaseDao info) {
        if (ObjectUtils.isNotEmpty(info) && StringUtils.isNotEmpty(info.getDriverClass()) &&
                StringUtils.isNotEmpty(info.getUserName()) && StringUtils.isNotEmpty(key) &&
                StringUtils.isNotEmpty(info.getUrl()) && StringUtils.isNotEmpty(info.getPassword())) {
            return this.initHikariConfig(info.getDriverClass(), info.getUrl(), key, info.getUserName(), info.getPassword());
        }
        return null;
    }

    private HikariConfig initHikariConfig(MetaConfig metaInfo) throws IllegalArgumentException {
        return this.initHikariConfig(StringUtils.isNotEmpty(metaInfo.getDriverClassName()) ? metaInfo.getDriverClassName() : "com.mysql.cj.jdbc.Driver",
                metaInfo.getJdbcUrl(), "default", metaInfo.getUserName(), metaInfo.getPassword());
    }

    private HikariDataSource initHikariDataSource(HikariConfig config) throws IllegalArgumentException {
        return new HikariDataSource(config);
    }

    public static DynamicSourceManager instance() {
        return DynamicSourceManager.DynamicSourceManagerBuilder.instance;
    }

    private static class DynamicSourceManagerBuilder {
        private static DynamicSourceManager instance = new DynamicSourceManager();
    }
}
