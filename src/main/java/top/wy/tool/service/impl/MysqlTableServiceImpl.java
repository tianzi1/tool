package top.wy.tool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TableStructure> getStructure() {
        return table.getTableStructure();
    }
}
