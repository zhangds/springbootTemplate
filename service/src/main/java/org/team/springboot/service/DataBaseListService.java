package org.team.springboot.service;

import org.springframework.stereotype.Service;
import org.team.springboot.dao.DataBaseDao;

import java.util.List;

/**
 * @author zhangds
 * @date 2024/7/8
 * @Notes
 **/
@Service
public interface DataBaseListService {

    List<DataBaseDao> getAllDbList();

    DataBaseDao getDbInfo(String dbId);
}
