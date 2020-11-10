package top.wy.tool.util;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : wdz
 * @date :
 */
public class ExcelUtils {
    public static void TabledownFile(HttpServletResponse response, HSSFWorkbook talbe,String name) throws IOException {
        response.setHeader("content-disposition","attachment;fileName="+name+".xls");
        ServletOutputStream outputStream = response.getOutputStream();
        talbe.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    /**
     * 设置表头信息
     * @param secumain
     * @param col
     * @param cellStyle
     */
    public static void createTableHead(HSSFSheet secumain, int col, HSSFCellStyle cellStyle) {
        HSSFCellStyle headCellStyle = setHeadCellStyle(cellStyle);
        HSSFRow row = secumain.createRow(col);
        createcells(0,"列名",row,headCellStyle);
        createcells(1,"数据类型",row,headCellStyle);
        createcells(2,"字段类型",row,headCellStyle);
        createcells(3,"长度",row,headCellStyle);
        createcells(4,"是否为空",row,headCellStyle);
        createcells(5,"默认值",row,headCellStyle);
        createcells(6,"备注",row,headCellStyle);
    }

    /**
     * 设置文档作者信息
     * @param table
     */
    public static void setAuthor(HSSFWorkbook table){
        //创建文档信息
        table.createInformationProperties();
        //获取DocumentSummaryInformation对象
        DocumentSummaryInformation documentSummaryInformation = table.getDocumentSummaryInformation();
        //类别
        documentSummaryInformation.setCategory("类别:Excel文件");
        //管理者
        documentSummaryInformation.setManager("管理者:吴代庄");
        //公司
        documentSummaryInformation.setCompany("公司:wy.top");
    }
    /**
     * 设置单元格
     * @param i
     * @param colname
     * @param row
     */
    public static void createcells(int i, String colname, HSSFRow row,HSSFCellStyle cellStyle) {
        HSSFCell cell = row.createCell(i);
        cell.setCellValue(colname);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置表格整体样式
     * @param cellStyle
     */
    public static HSSFCellStyle setCellStyle(HSSFCellStyle cellStyle){
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
    public static HSSFCellStyle setHeadCellStyle(HSSFCellStyle cellStyle){
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment( VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        return cellStyle;
    }
}
