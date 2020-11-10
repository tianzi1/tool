package top.wy.tool.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : wdz
 * @date :
 */
public class Utils {
    public static void TabledownFile(HttpServletResponse response, HSSFWorkbook talbe) throws IOException {
        response.setHeader("content-disposition","attachment;fileName="+"test.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        talbe.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
