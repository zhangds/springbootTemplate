package org.team.springboot.mapper.primarySource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.team.springboot.dao.DataBaseDao;
import org.team.springboot.service.DataBaseListService;

import java.util.List;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/

@Mapper
public interface DataBaseListMapper extends DataBaseListService {

    @Select("select db_id, db_cnname, driver_classname,url, user_name, password, db_type from sys_dblist")
    @Results({ @Result(property = "dbName", column = "db_id"),
            @Result(property = "cnName", column = "db_cnname"),
            @Result(property = "driverClass", column = "driver_classname"),
            @Result(property = "url", column = "url"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "dbType", column = "db_type") })
    List<DataBaseDao> getAllDbList();

    @Select("select db_id, db_cnname, driver_classname,url, user_name, password, db_type from sys_dblist where db_id=#{dbId}")
    @Results({ @Result(property = "dbName", column = "db_id"),
            @Result(property = "cnName", column = "db_cnname"),
            @Result(property = "driverClass", column = "driver_classname"),
            @Result(property = "url", column = "url"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "dbType", column = "db_type") })
    DataBaseDao getDbInfo(String dbId);

}
