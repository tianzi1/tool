package top.wy.tool.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.wy.tool.entity.TableStructure;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Mapper
public interface Table {
    @Select("SELECT\n" +
            "  COLUMN_NAME ,\n" +
            "  COLUMN_TYPE ,\n" +
            "    DATA_TYPE ,\n" +
            "  CHARACTER_MAXIMUM_LENGTH ,\n" +
            "  IS_NULLABLE ,\n" +
            "  COLUMN_DEFAULT ,\n" +
            "  COLUMN_COMMENT  \n" +
            "FROM\n" +
            " INFORMATION_SCHEMA.COLUMNS\n" +
            "where\n" +
            "table_name  = 'secumain'")
    public List<TableStructure> getTableStructure();
}
