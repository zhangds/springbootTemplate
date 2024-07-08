package org.team.springboot.dao;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseDao {

    @JSONField(name="name")
    private String dbName;
    @JSONField(name="cnName")
    private String cnName;
    @JSONField(name="driverCls")
    private String driverClass;
    @JSONField(name="url")
    private String url;
    @JSONField(name="userName")
    private String userName;
    @JSONField(name="pwd")
    private String password;
    @JSONField(name="dbType")
    private String dbType;

}
