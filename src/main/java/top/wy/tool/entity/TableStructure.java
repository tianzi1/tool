package top.wy.tool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : wdz
 * @date :
 */
@ToString
@Getter
@Setter
public class TableStructure {
    //列名
    private  String COLUMN_NAME;
    //数据类型
    private String COLUMN_TYPE;
    //字段类型
    private  String DATA_TYPE ;
    //长度
    private  String CHARACTER_MAXIMUM_LENGTH ;
    //是否为空
    private  String IS_NULLABLE ;
    //默认值
    private  String COLUMN_DEFAULT ;
    //备注
    private  String COLUMN_COMMENT ;
}
