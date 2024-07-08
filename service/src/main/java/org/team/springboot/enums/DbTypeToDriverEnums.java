package org.team.springboot.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/

public enum DbTypeToDriverEnums {

    MYSQL("mysql", "com.mysql.cj.jdbc.Driver"),
    ORACLE("oracle", "oracle.jdbc.driver.OracleDriver"),
    SQLITE("sqlite", "org.sqlite.JDBC"),
    PRESTO("presto", "com.facebook.presto.jdbc.PrestoDriver");

    private final String dbType;
    private final String driverClass;

    DbTypeToDriverEnums(String dbType, String driverClass) {
        this.dbType = dbType;
        this.driverClass = driverClass;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    private static final Map<String, DbTypeToDriverEnums> dbTypeMap = Stream.of(values())
            .collect(Collectors.toMap(DbTypeToDriverEnums::getDbType, type -> type));

    public static DbTypeToDriverEnums findByDbType(String dbType) {
        DbTypeToDriverEnums type = dbTypeMap.get(dbType.toLowerCase());
        if (type == null) {
            throw new IllegalArgumentException("Unknown database type: " + dbType);
        }
        return type;
    }

}
