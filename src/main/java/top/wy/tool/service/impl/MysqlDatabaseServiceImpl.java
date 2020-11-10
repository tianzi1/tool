package top.wy.tool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.wy.tool.dao.Database;
import top.wy.tool.service.DatabaseService;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Component
public class MysqlDatabaseServiceImpl implements DatabaseService {
    @Autowired
    Database database;
    @Override
    public List<String> getAllTables() {
        return database.getAllTables();
    }
}
