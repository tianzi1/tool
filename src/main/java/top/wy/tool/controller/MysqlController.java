package top.wy.tool.controller;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wy.tool.entity.TableStructure;
import top.wy.tool.entity.vo.TableStructureVo;
import top.wy.tool.service.TableService;
import top.wy.tool.util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : wdz
 * @date :
 */
@Controller
public class MysqlController {
    @Autowired
    private TableService tableService;

    @RequestMapping("/gettablestruct")
    @ResponseBody
    public List<TableStructureVo> getTableStruct(){
        List<TableStructure> structure = tableService.getStructure();
        List<TableStructureVo> collect = structure.parallelStream().map(talbeStruce -> {
            TableStructureVo table = new TableStructureVo();
            BeanUtils.copyProperties(talbeStruce, table);
            return table;
        }).collect(Collectors.toList());
        return collect;
    }
    @RequestMapping("/getTableStructToExcel")
    @ResponseBody
    public void  getTableStructToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TableStructure> structure = tableService.getStructure();

        HSSFWorkbook table = new HSSFWorkbook();
        HSSFSheet secumain = table.createSheet("secumain");
        secumain.setDefaultColumnWidth(15);
        HSSFCellStyle cellStyle = table.createCellStyle();
        HSSFCellStyle headCellStyle = setHeadCellStyle(cellStyle);
        int col=0;

        HSSFRow row = secumain.createRow(col++);
        createcells(0,"列名",row,headCellStyle);
        createcells(1,"数据类型",row,headCellStyle);
        createcells(2,"字段类型",row,headCellStyle);
        createcells(3,"长度",row,headCellStyle);
        createcells(4,"是否为空",row,headCellStyle);
        createcells(5,"默认值",row,headCellStyle);
        createcells(6,"备注",row,headCellStyle);

        HSSFCellStyle hssfCellStyle = setCellStyle(cellStyle);
        for (TableStructure st :
                structure) {
            HSSFRow rows = secumain.createRow(col++);
            int mol=0;
            createcells(mol++,st.getCOLUMN_NAME(),rows,hssfCellStyle);
            createcells(mol++,st.getCOLUMN_TYPE(),rows,hssfCellStyle);
            createcells(mol++,st.getDATA_TYPE(),rows,hssfCellStyle);
            createcells(mol++,st.getCHARACTER_MAXIMUM_LENGTH(),rows,hssfCellStyle);
            createcells(mol++,st.getIS_NULLABLE(),rows,hssfCellStyle);
            createcells(mol++,st.getCOLUMN_DEFAULT(),rows,hssfCellStyle);
            createcells(mol++,st.getCOLUMN_COMMENT(),rows,hssfCellStyle);

        }

        //创建文档信息
        table.createInformationProperties();
        //获取DocumentSummaryInformation对象
        DocumentSummaryInformation documentSummaryInformation = table.getDocumentSummaryInformation();
        documentSummaryInformation.setCategory("类别:Excel文件");//类别
        documentSummaryInformation.setManager("管理者:吴代庄");//管理者
        documentSummaryInformation.setCompany("公司:wy.top");//公司
        //文档输出
        FileOutputStream out = new FileOutputStream(  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
        table.write(out);
        out.close();
        Utils.TabledownFile(response,table);
    }

    /**
     * 设置单元格
     * @param i
     * @param colname
     * @param row
     */
    private void createcells(int i, String colname, HSSFRow row,HSSFCellStyle cellStyle) {
        HSSFCell cell = row.createCell(i);
        cell.setCellValue(colname);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置表格整体样式
     * @param cellStyle
     */
    public HSSFCellStyle setCellStyle(HSSFCellStyle cellStyle){
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment( VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        return cellStyle;
    }

    /**
     * 设置表头整体样式
     * @param cellStyle
     */
    public HSSFCellStyle setHeadCellStyle(HSSFCellStyle cellStyle){
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment( VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        return cellStyle;
    }
}
