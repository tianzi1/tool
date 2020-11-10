package top.wy.tool.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : wdz
 * @date :
 */
@Mapper
public interface Database {
    @Select("show tables ")
    public List<String >getAllTables();
}
