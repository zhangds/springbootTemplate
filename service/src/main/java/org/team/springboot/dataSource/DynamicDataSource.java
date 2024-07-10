package org.team.springboot.dataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/

/**
 * 定义动态数据源派生类。从基础的DataSource派生，动态性自己实现
 */
@Slf4j
@Component
public class DynamicDataSource extends HikariDataSource {

    DynamicSourceManager dynamicSourceManager = DynamicSourceManager.instance();

    public synchronized JdbcTemplate getJdbcTemplate(String dbId){
        HikariDataSource ds = dynamicSourceManager.getDataSource(dbId);
        return new JdbcTemplate(ds);
    }

    @Override
    public Connection getConnection() throws SQLException {
        HikariDataSource ds = dynamicSourceManager.getDataSource(null);
        return ds.getConnection();
//        String projectCode = DBIdentifier.getProjectCode();
//        //1、获取数据源
//        HikariDataSource dds = DataSourceHolder.instance().getDDS(projectCode);
//        //2、如果数据源不存在则创建
//        if (dds == null) {
//            try {
//                HikariDataSource newDDS = initDDS(projectCode);
//                DDSHolder.instance().addDDS(projectCode, newDDS);
//            } catch (IllegalArgumentException e) {
//                log.error("Init data source fail. projectCode:" + projectCode);
//                return null;
//            }
//        }
//
//        dds = DDSHolder.instance().getDDS(projectCode);
//        try {
//            return dds.getConnection();
//        } catch (SQLException e) {
//            log.error(e.getMessage());
//            return null;
//        }
    }

    @Override
    public Connection getConnection(String username, String password) {
        return null;
    }

    /**
     * 以当前数据对象作为模板复制一份
     */
//    private HikariDataSource initDDS(String dbId) throws IllegalArgumentException {
//
//        // 2、复制PoolConfiguration的属性
//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/your_database");
//        config.setPoolName("metadb");
//        config.setUsername("reader");
//        config.setPassword("123456");
//        config.setMinimumIdle(5); // 初始连接数
//        config.setMaximumPoolSize(20); // 最大连接数
//        config.setConnectionTestQuery("SELECT 1");
//        config.setIdleTimeout(300000); // 空闲连接回收时间（5 分钟）
//        config.setMaxLifetime(900000); // 连接最大生命周期（15 分钟）
//        config.setConnectionTimeout(10000); // 连接获取超时时间（10 秒
//        HikariDataSource dds = new HikariDataSource(config);
//        //dds.getPoolName();
//        return dds;
//    }
}
