package top.wy.tool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.wy.tool.dao.Table;
import top.wy.tool.entity.TableStructure;
import top.wy.tool.service.TableService;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Component
public class MysqlTableServiceImpl  implements TableService {
    @Autowired
    Table table;
    @Value("${spring.datasource.url}")
    private String datasouceUrl;
    @Override
    public List<TableStructure> getStructure(String name) {
         String databaseName = datasouceUrl.split("\\?")[0].split("/")[3];

        return table.getTableStructure(name,databaseName);
    }
}
