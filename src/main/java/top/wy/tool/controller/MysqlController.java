package top.wy.tool.controller;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wy.tool.entity.TableStructure;
import top.wy.tool.entity.vo.TableStructureVo;
import top.wy.tool.service.DatabaseService;
import top.wy.tool.service.TableService;
import top.wy.tool.util.ExcelUtils;

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
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/")
    public String index(Model model ){
        model.addAttribute("tables",databaseService.getAllTables());
        return "index";
    }

    /**
     * 获取所有数据库名
     * @return
     */
    @RequestMapping("/getalltables")
    @ResponseBody
    public List<String >getAllTables(){
        return databaseService.getAllTables();
    }

    /**
     * 获取表结构信息
     * @return
     */
    @RequestMapping("/gettablestruct")
    @ResponseBody
    public List<TableStructureVo> getTableStruct(){
        List<TableStructure> structure = tableService.getStructure("secuman");
        List<TableStructureVo> collect = structure.parallelStream().map(talbeStruce -> {
            TableStructureVo table = new TableStructureVo();
            BeanUtils.copyProperties(talbeStruce, table);
            return table;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 输出指定表结构信息到excel中
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getTableStructToExcel")
    @ResponseBody
    public String   getTableStructToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("table");
        if(StringUtils.isEmpty(name)){
            return "属性table不能为空";
        }
        List<TableStructure> structure = tableService.getStructure(name);

        HSSFWorkbook table = new HSSFWorkbook();
        HSSFSheet secumain = table.createSheet(name);
        secumain.setDefaultColumnWidth(15);
        HSSFCellStyle cellStyle = table.createCellStyle();
        int col=0;
        ExcelUtils.createTableHead(secumain,col,cellStyle);
        col++;
        HSSFCellStyle hssfCellStyle = ExcelUtils.setCellStyle(cellStyle);
        for (TableStructure st :
                structure) {
            HSSFRow rows = secumain.createRow(col++);
            int mol=0;
            ExcelUtils.createcells(mol++,st.getCOLUMN_NAME(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getCOLUMN_TYPE(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getDATA_TYPE(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getCHARACTER_MAXIMUM_LENGTH(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getIS_NULLABLE(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getCOLUMN_DEFAULT(),rows,hssfCellStyle);
            ExcelUtils.createcells(mol++,st.getCOLUMN_COMMENT(),rows,hssfCellStyle);
        }

        //文档输出
        String backups = request.getParameter("backups");
        if(!StringUtils.isEmpty(backups)&&backups.equals("1")){
            FileOutputStream out = new FileOutputStream(  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() +".xls");
            table.write(out);
            out.close();
        }

        ExcelUtils.TabledownFile(response,table,name);
        return "ok";
    }


}
