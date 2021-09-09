/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import work.xingbili.common.api.CommHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;


/**
 * Excel 处理类
 *
 * @author yangfan
 * @date 2021/04/13
 */
public class ExcelUtils {

    private static Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static final String StyleName_Cell_Font_FontName = "FontName";
    public static final String StyleName_Cell_Font_FontHeightInPoints = "FontHeightInPoints";
    public static final String StyleName_Cell_Font_Boldweight = "FontBoldweight";
    public static final String StyleName_Cell_FillForegroundColor = "FillForegroundColor";
    public static final String StyleName_Cell_FillPattern = "FillPattern";
    public static final String StyleName_Cell_Alignment = "Alignment";
    public static final String StyleName_Cell_BorderBottom = "BorderBottom";
    public static final String StyleName_Cell_BorderLeft = "BorderLeft";
    public static final String StyleName_Cell_BorderRight = "BorderRight";
    public static final String StyleName_Cell_BorderTop = "BorderTop";
    public static final String StyleName_Cell_NUMERIC = "Numeric";

    public static final String table_header = "header";
    public static final String table_contants = "contants";

    private static boolean isExcelXls(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    private static boolean isExcelXlsx(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    /**
      *导入excel文件并读取内容
      *
      * @author  yangfan
      * @param filePath: 文件路径
      * @param sheetnum: sheet页
      * @param startRow: 文开始行
      * @return List
      * @date  2021/5/17 17:54
     *
      **/
    public static List<String[]> readExcel(String filePath, int sheetnum, int startRow)
            throws Exception {
        List<String[]> result = new ArrayList<>();

        InputStream inputStream = null;
        Workbook wb = null;
        try {
            inputStream = new FileInputStream(filePath);
            result = readExcel(inputStream, filePath, sheetnum, startRow);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.close(inputStream);
            if (wb != null) {
                wb.close();
            }
        }

        return result;
    }


    /**
     *导入excel文件并读取内容
     *
     * @author  yangfan
     * @param inputStream: 流
     * @param fileName: 文件名
     * @param sheetnum: sheet页
     * @param startRow: 文开始行
     * @return List
     * @date  2021/5/17 17:54
     *
     **/
    public static List<String[]> readExcel(InputStream inputStream, String fileName, int sheetnum, int startRow)
            throws Exception {
        List<String[]> result = new ArrayList<String[]>();

        Workbook wb = null;
        try {
            if (isExcelXls(fileName)) {
                wb = new HSSFWorkbook(inputStream);
            } else if (isExcelXlsx(fileName)) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                logger.error("【{}】不是EXCEL文件,请检查文件后缀", fileName);
                throw new Exception("【" + fileName + "】不是EXCEL文件,请检查文件后缀");
            }
            // 获取公式计算器;
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            int numSheet = wb.getNumberOfSheets();
            if (sheetnum >= numSheet) {
                logger.error("EXCEL共有【{}】SHEET，无法读取第【{}】SHEET, SHEET编号从0开始", numSheet, sheetnum);
                throw new Exception(
                        "EXCEL共有【{" + numSheet + "}】SHEET，无法读取第【{" + sheetnum
                                + "}】SHEET, SHEET编号从0开始");
            }
            logger.debug("EXCEL共有【{}】个SHEET", numSheet);
            // 获取指定的sheet;
            Sheet sheet = wb.getSheetAt(sheetnum);
            // 行数;
            int rowNum = sheet.getPhysicalNumberOfRows();
            logger.debug("EXCEL共有【{}】个ROW", rowNum);
            // 取前5行中的最大列数;
            int max = 5;
            int colNum = 0;
            for (int i = 0; i < rowNum && i < max; i++) {
                Row r = sheet.getRow(i);
                if (r != null) {
                    colNum = Math.max(r.getPhysicalNumberOfCells(), colNum);
                }
            }
            // 循环遍历excel表格;
            for (int i = startRow; i < rowNum; i++) {
                String[] obj = new String[colNum];
                for (int j = 0; j < colNum; j++) {
                    // 获取单元格值;
                    obj[j] = getCellValue(evaluator, sheet, i, j);
                }
                logger.debug("EXCEL【{}】第【{}】行内容【{}】", fileName, i, obj);
                result.add(obj);
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.close(inputStream);
            if (wb != null) {
                wb.close();
            }
        }

        return result;
    }

    /**
     *写文件
     *
     * @author  yangfan
     * @param filePath: 完整的文件路径+名字
     * @param sheetName: EXCEL sheet名字，默认sheet0...
     * @param header: 表格头内容
     * @param dataList: 表格内容
     * @param userName: 用户名
     * @param tile: 主题
     * @date  2021/5/17 17:54
     *
     **/
    public static void writeExcel(
            String filePath, String sheetName, String[] header, List<String[]> dataList,
            String userName, String tile)
            throws IOException {
        // 判断文件路径是否为空
        if (StringUtils.isEmpty(filePath)) {
            logger.error("文件路径不能为空");
            throw new IOException("文件路径及名称不能为空");
        }

        if (!(isExcelXls(filePath) || isExcelXlsx(filePath))) {
            logger.error("不支持文件名【{}】,仅支持xls|xlsx", filePath);
            throw new IOException("文件名错误，系统不支持");
        }

        // 判断列表是否有数据，如果没有数据，则返回
        if (dataList == null || dataList.size() == 0) {
            logger.error("待写入的数据为空");
            throw new IOException("待写入的数据为空，写文件失败");
        }

        Map<String, List<Map<String, Object>>> styleMap = null;
        if (header != null && header.length > 0) {
            styleMap = ExcelUtils.createDefaultTableStyle(header.length);
        } else {
            styleMap = ExcelUtils.createDefaultTableStyle(dataList.get(0).length);
        }
        List<Map<String, Object>> headerStyleList = styleMap.get(table_header);
        List<Map<String, Object>> contantsStyleList = styleMap.get(table_contants);

        Workbook wb = null;
        try {
            // 判断文件是否存在
            File file = new File(filePath);
            if (file.exists()) {
                // 如果存在文件，则备份原文件
                boolean flag = file
                        .renameTo(new File(filePath + "." + System.currentTimeMillis() + ".bak"));
                if (flag) {
                    if (isExcelXls(filePath)) {
                        wb = new HSSFWorkbook();
                    } else if (isExcelXlsx(filePath)) {
                        wb = new XSSFWorkbook();
                    }
                } else {
                    logger.error("文件【{}】已存在，系统备份原文件失败", filePath);
                    throw new IOException("文件【" + filePath + "】已存在，系统备份原文件失败，写文件失败");
                }
            } else {
                if (isExcelXls(filePath)) {
                    wb = new HSSFWorkbook();
                } else if (isExcelXlsx(filePath)) {
                    wb = new XSSFWorkbook();
                }
            }

            // 将datalist的内容写到Excel中
            Sheet sheet = null;
            if (StringUtils.isEmpty(sheetName)) {
                sheet = wb.createSheet();// 在文档对象中创建一个表单..默认是表单名字是Sheet0、Sheet1....
            } else {
                sheet = wb.createSheet(sheetName);// 在创建表单的时候指定表单的名字
            }
            int t = 0;// 记录最新添加的行数
            if (!StringUtils.isEmpty(header)) {
                // 写固定头
                Row firstrow = sheet.createRow(t++);
                for (int i = 0; i < header.length; i++) {
                    firstrow.createCell(i);
                }

                sheet.addMergedRegion(
                        new CellRangeAddress(0, 0, header.length / 2, header.length - 1));
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length / 2 - 1));

                Cell tempcell1 = sheet.getRow(0).getCell(0);
                logger.debug(tempcell1.getStringCellValue());
                tempcell1.setCellValue(tile);
                tempcell1.setCellStyle(headLeftCellStyle(wb));
                Cell tempcell2 = sheet.getRow(0).getCell(header.length / 2);
                logger.debug(tempcell2.getStringCellValue());
                tempcell2.setCellValue("日期：" + DateUtil.getDate() + "  制表人：" + userName);
                tempcell2.setCellStyle(headRightCellStyle(wb));

                // 写表头
                Row row = sheet.createRow(t++);
                for (int i = 0; i < header.length; i++) {
                    Cell headCell = row.createCell(i);
                    sheet.autoSizeColumn(i);
                    headCell.setCellType(Cell.CELL_TYPE_STRING);// 设置这个单元格的数据的类型,是文本类型还是数字类型
                    // headCell.setCellStyle(defaultCellStyle(wb));// 设置表头样式
                    Map<String, Object> cellStyleMap = null;
                    if (headerStyleList != null && headerStyleList.size() > i) {
                        cellStyleMap = headerStyleList.get(i);
                    }
                    headCell.setCellStyle(createCellStyle(wb, cellStyleMap));// 设置表头样式
                    headCell.setCellValue(header[i]);// 给这个单元格设置值
                }
            }
            logger.debug("要添加的数据总条数为：" + dataList.size());

            for (int i = 0; i < dataList.size(); i++) {
                String[] tmpStr = dataList.get(i);
                if (StringUtils.isEmpty(tmpStr)) {
                    continue;
                }
                Row r = sheet.createRow(t++);
                // 循环为新行创建单元格
                for (int j = 0; j < tmpStr.length; j++) {
                    sheet.autoSizeColumn(i);
                    Cell cell = r.createCell(j);// 获取数据类型
                    cell.setCellValue(StringUtils.trimAllWhitespace(tmpStr[j]));
                    // cell.setCellStyle(contentCellStyle(wb));// 设置样式
                    Map<String, Object> cellStyleMap = null;
                    if (contantsStyleList != null && contantsStyleList.size() > j) {
                        cellStyleMap = contantsStyleList.get(j);
                    }
                    cell.setCellStyle(createCellStyle(wb, cellStyleMap));// 设置表头样式
                }
            }

            boolean f = file.createNewFile();
            logger.debug("创建新文件结果【{}】", f);
            FileOutputStream outputStream = null;
            try {
                // 重新将数据写入Excel中
                outputStream = new FileOutputStream(file);
                wb.write(outputStream);
                outputStream.flush();
            } catch (Exception e) {
                logger.error("写入Excel时发生错误！ ", e);
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     *默认EXCEL样式
     *
     * @author  yangfan
     * @param wb:
     * @return  CellStyle
     * @date  2021/5/17 17:54
     *
     **/
    public static CellStyle defaultCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 让单元格居中
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // style.setWrapText(true);//设置自动换行
        style.setFont(font);
        return style;
    }

    /**
     *创建表格样式
     *
     * @author  yangfan
     * @param wb:
     * @param  styleMap :风格Map
     * @return  CellStyle
     * @date  2021/5/17 17:54
     *
     **/
    public static CellStyle createCellStyle(Workbook wb, Map<String, Object> styleMap) {
        CellStyle style = defaultCellStyle(wb);
        if (styleMap != null) {
            if (styleMap.containsKey(StyleName_Cell_FillForegroundColor)) {
                style.setFillForegroundColor(
                        (short) styleMap.get(StyleName_Cell_FillForegroundColor));
            }
            if (styleMap.containsKey(StyleName_Cell_FillPattern)) {
                style.setFillPattern((short) styleMap.get(StyleName_Cell_FillPattern));
            }
            if (styleMap.containsKey(StyleName_Cell_Alignment)) {
                style.setAlignment((short) styleMap.get(StyleName_Cell_Alignment));// 让单元格居中
            }
            if (styleMap.containsKey(StyleName_Cell_BorderBottom)) {
                style.setBorderBottom((short) styleMap.get(StyleName_Cell_BorderBottom));// 让单元格居中
            }
            if (styleMap.containsKey(StyleName_Cell_BorderLeft)) {
                style.setBorderLeft((short) styleMap.get(StyleName_Cell_BorderLeft));// 让单元格居中
            }
            if (styleMap.containsKey(StyleName_Cell_BorderRight)) {
                style.setBorderRight((short) styleMap.get(StyleName_Cell_BorderRight));// 让单元格居中
            }
            if (styleMap.containsKey(StyleName_Cell_BorderTop)) {
                style.setBorderTop((short) styleMap.get(StyleName_Cell_BorderTop));// 让单元格居中
            }
            if (styleMap.containsKey(StyleName_Cell_Font_FontName)
                    || styleMap.containsKey(StyleName_Cell_Font_FontHeightInPoints)
                    || styleMap.containsKey(StyleName_Cell_Font_Boldweight)) {
                Font font = wb.createFont();
                font.setFontName("微软雅黑");
                font.setFontHeightInPoints((short) 10);// 设置字体大小
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

                if (styleMap.containsKey(StyleName_Cell_Font_FontName)) {
                    font.setFontName((String) styleMap.get(StyleName_Cell_Font_FontName));
                }
                if (styleMap.containsKey(StyleName_Cell_Font_FontHeightInPoints)) {
                    font.setFontHeightInPoints(
                            (short) styleMap.get(StyleName_Cell_Font_FontHeightInPoints));
                }
                if (styleMap.containsKey(StyleName_Cell_Font_Boldweight)) {
                    font.setBoldweight((short) styleMap.get(StyleName_Cell_Font_Boldweight));
                }
                style.setFont(font);
            }
        	Boolean isNum = CommHelper.getValue(styleMap, StyleName_Cell_NUMERIC, Boolean.class);
        	if(isNum == null) {
        		isNum = false;
        	}
        	if(isNum) {
        		DataFormat format= wb.createDataFormat();
        		style.setDataFormat(format.getFormat(BigDecimalUtil.DEFAULT_FORMAT_PATTERN));
        	}
        }
        return style;
    }


    /**
     *默认EXCEL样式
     *
     * @author  yangfan
     * @param wb:
     * @return  CellStyle
     * @date  2021/5/17 17:54
     *
     **/
    public static CellStyle headLeftCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 16);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 让单元格居中
        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // style.setWrapText(true);//设置自动换行
        style.setFont(font);
        return style;
    }


    /**
     *默认EXCEL样式
     *
     * @author  yangfan
     * @param wb:
     * @return  CellStyle
     * @date  2021/5/17 17:54
     *
     **/
    public static CellStyle headRightCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 让单元格居中
        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // style.setWrapText(true);//设置自动换行
        style.setFont(font);
        return style;
    }

    /**
     *创建金额格式化的样式(excel)
     *
     * @author  yangfan
     * @return  Map
     * @date  2021/5/17 17:54
     *
     **/
    public static Map<String, Object> defaultAmtExcelStyle() {
        Map<String, Object> styleMap = new HashMap<>();
        styleMap.put(StyleName_Cell_Font_Boldweight, HSSFFont.BOLDWEIGHT_NORMAL);
        styleMap.put(ExcelUtils.StyleName_Cell_Alignment, HSSFCellStyle.ALIGN_RIGHT);
        styleMap.put(StyleName_Cell_NUMERIC, true);
        return styleMap;
    }


    /**
     *字符串样式
     *
     * @author  yangfan
     * @return  Map
     * @date  2021/5/17 17:54
     *
     **/
    public static Map<String, Object> defaultStrExcelStyle() {
        Map<String, Object> styleMap = new HashMap<>();
        styleMap.put(StyleName_Cell_Font_Boldweight, HSSFFont.BOLDWEIGHT_NORMAL);
        styleMap.put(ExcelUtils.StyleName_Cell_Alignment, HSSFCellStyle.ALIGN_LEFT);
        return styleMap;
    }

    /**
     *默认样式
     *
     * @author  yangfan
     * @return  Map
     * @date  2021/5/17 17:54
     *
     **/
    public static Map<String, Object> defaultContantsExcelStyle() {
        Map<String, Object> styleMap = new HashMap<>();
        styleMap.put(StyleName_Cell_Font_Boldweight, HSSFFont.BOLDWEIGHT_NORMAL);
        styleMap.put(ExcelUtils.StyleName_Cell_Alignment, HSSFCellStyle.ALIGN_CENTER);
        return styleMap;
    }


    /**
     *表头默认样式
     *
     * @author  yangfan
     * @return  Map
     * @date  2021/5/17 17:54
     *
     **/
    public static Map<String, Object> defaultHeadExcelStyle() {
        Map<String, Object> styleMap = new HashMap<>();
        styleMap.put(StyleName_Cell_Font_Boldweight, HSSFFont.BOLDWEIGHT_BOLD);
        styleMap.put(StyleName_Cell_Alignment, HSSFCellStyle.ALIGN_CENTER);
        return styleMap;
    }


    /**
     *按默认格式创建表格样式
     * @param  colNum
     * @author  yangfan
     * @return  Map
     * @date  2021/5/17 17:54
     *
     **/
    public static Map<String, List<Map<String, Object>>> createDefaultTableStyle(int colNum) {
        Map<String, List<Map<String, Object>>> styleMap = new HashMap<>();
        List<Map<String, Object>> headerStyleList = new ArrayList<>();
        for (int i = 0; i < colNum; i++) {
            headerStyleList.add(defaultHeadExcelStyle());
        }
        List<Map<String, Object>> contantsStyleList = new ArrayList<>();
        for (int i = 0; i < colNum; i++) {
            contantsStyleList.add(defaultContantsExcelStyle());
        }
        styleMap.put(table_header, headerStyleList);
        styleMap.put(table_contants, contantsStyleList);
        return styleMap;
    }


    /**
     *复制一个单元格样式到目的单元格样式
     * @param  fromStyle
     * @param  toStyle
     * @author  yangfan
     * @date  2021/5/17 17:54
     *
     **/
    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());
        // 边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        // 背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        // 数据格式
        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
        // toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());// 首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());// 旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());

    }


    /**
     *合并单元格
     * @param  sheet
     * @author  yangfan
     * @date  2021/5/17 17:54
     *
     **/
    public void setMergedRegion(Sheet sheet) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            // 获取合并单元格位置
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            int mergeRows = lastRow - firstRow;// 合并的行数
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            // 根据合并的单元格位置和大小，调整所有的数据行格式，
            for (int j = lastRow + 1; j <= sheet.getLastRowNum(); j++) {
                // 设定合并单元格
                sheet.addMergedRegion(
                        new CellRangeAddress(j, j + mergeRows, firstColumn, lastColumn));
                j = j + mergeRows;// 跳过已合并的行
            }

        }
    }


    /**
     * 获取指定的行列的cell;当行列在合并区域内时,返回该合并区域的第一个cell
     * @param  sheet
     * @param  row
     * @param  column
     * @author  yangfan
     * @return  Cell
     * @date  2021/5/17 17:54
     *
     **/
    public static Cell getCell(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return fCell;
                }
            }
        }
        Cell cell = null;
        if(sheet.getRow(row) != null) {
            cell = sheet.getRow(row).getCell(column);
        }
        return cell;
    }


    /**
     * 获取指定的行列的cell;当行列在合并区域内时,返回该合并区域的第一个cell
     * @param evaluator
     * @param sheet
     * @param row
     * @param column
     * @author  yangfan
     * @return  String
     * @date  2021/5/17 17:54
     *
     **/
    public static String getCellValue(FormulaEvaluator evaluator, Sheet sheet, int row, int column) {
        Cell cell = getCell(sheet, row, column);
        return getCellValue(cell, evaluator);
    }


    /**
     * 将给定的值设置给该cell
     * @param sheet
     * @param row
     * @param column
     * @param value
     * @author  yangfan
     * @return  String
     * @date  2021/5/17 17:54
     *
     **/
    public static void setCellValue(Sheet sheet, int row, int column, String value) {
        Cell cell = getCell(sheet, row, column);
        cell.setCellValue(StringUtils.trimAllWhitespace(value));
    }


    /**
     * 获取指定cell的值
     * @param cell
     * @param evaluator
     * @author  yangfan
     * @return  String
     * @date  2021/5/17 17:54
     *
     **/
    public static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        // CellStyle cellStyle = cell.getCellStyle();
        // logger.debug(cellStyle.getFontIndex());
        // logger.debug("{}, {}, {}, {}", cellStyle.getBorderBottom(), cellStyle.getBorderLeft(),
        // cellStyle.getBorderRight(), cellStyle.getBorderTop());
        // 当cell表明是公式时,单独处理;
        if (Cell.CELL_TYPE_FORMULA == cellType && evaluator != null) {
            CellValue cv = evaluator.evaluate(cell);
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
            	
            	Date date = cell.getDateCellValue();
                return DateUtil.format(date);
            } else {
                return getCellValue(cv);
            }
        } else {
            return getCellValue(cell);
        }
    }


    /**
     * 获取指定cell的值
     * @param obj
     * @author  yangfan
     * @return  String
     * @date  2021/5/17 17:54
     *
     **/
    public static String getCellValue(Object obj) {

        String cellValue = null;

        CellValue cv = null;
        Cell cell = null;

        if (obj instanceof CellValue) {
            cv = (CellValue) obj;
        } else if (obj instanceof Cell) {
            cell = (Cell) obj;
        } else {
            return cellValue;
        }
        int cellType = (cv != null) ? cv.getCellType() : cell.getCellType();
        switch (cellType) {
            // 文本
            case Cell.CELL_TYPE_STRING:
                cellValue = (cv != null) ? cv.getStringValue() : cell.getStringCellValue();
                break;
            // 布尔型
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = (cv != null) ? String.valueOf(cv.getBooleanValue())
                        : String.valueOf(cell.getBooleanCellValue());
                break;
            // 空白
            case Cell.CELL_TYPE_BLANK:
                cellValue = (cv != null) ? cv.getStringValue() : cell.getStringCellValue();
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            // 错误
            case Cell.CELL_TYPE_ERROR:
                cellValue = "error";
                break;
            // 数字、日期
            case Cell.CELL_TYPE_NUMERIC:
                // 日期型
                if (cell != null && HSSFDateUtil.isCellDateFormatted(cell)) {
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    cellValue = DateUtil.format(date);
                }
                // 数字型
                else {
                    cellValue = (cv != null) ? String.valueOf(cv.getNumberValue())
                            : String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                cellValue = null;
        }
        return cellValue;
    }


    /**
     * 生成excel下载
     * @param filePath
     * @param response
     * @author  yangfan
     * @throws Exception
     * @date  2021/5/17 17:54
     *
     **/
    public static void downLoadExcel(String filePath, HttpServletResponse response)
            throws Exception {
        downLoadExcel(filePath, null, response, null);
    }

    /**
     * 生成excel下载
     * @param filePath
     * @param request
     * @param response
     * @param fileName
     * @author  yangfan
     * @throws Exception
     * @date  2021/5/17 17:54
     *
     **/
    public static void downLoadExcel(
            String filePath, HttpServletRequest request, HttpServletResponse response,
            String fileName)
            throws Exception {
        BufferedInputStream bis = null;
        OutputStream out = null;
        File file = null;
        try {
            file = new File(filePath);
            if (StringUtils.isEmpty(fileName)) {
                fileName = file.getName();
            }
            String rtn =
                    "filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"";
            if (request == null) {
            } else {
                String userAgent = request.getHeader("User-Agent");
                if (userAgent != null) {
                    String new_filename = URLEncoder.encode(fileName, "utf-8");
                    userAgent = userAgent.toLowerCase();
                    // IE浏览器，只能采用URLEncoder编码
                    if (userAgent.indexOf("msie") != -1) {
                        rtn = "filename=\"" + new_filename + "\"";
                    }
                    // Opera浏览器只能采用filename*
                    else if (userAgent.indexOf("opera") != -1) {
                        rtn = "filename*=UTF-8''" + new_filename;
                    }
                    // Safari浏览器，只能采用ISO编码的中文输出
                    else if (userAgent.indexOf("safari") != -1) {
                    }
                    // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
                    else if (userAgent.indexOf("applewebkit") != -1) {
                        rtn = "filename=\"" + new_filename + "\"";
                    }
                    // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                    else if (userAgent.indexOf("mozilla") != -1) {
                        rtn = "filename*=UTF-8''" + new_filename;
                    }
                }
            }

            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;

            response.reset();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; " + rtn);
            out = response.getOutputStream();
            while ((len = bis.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } finally {
            IOUtils.close(bis);
            IOUtils.close(out);
            work.xingbili.common.utils.FileUtils.delete(file);
        }
    }


    /**
     * 建Workbook并返回
     * @param sheetName
     * @param header
     * @param dataList
     * @param styleMap
     * @param tile
     * @author  yangfan
     * @date  2021/5/17 17:54
     *
     **/
    public static Workbook writeToWorkbook(
            String sheetName, String[] header, List<String[]> dataList,
            Map<String, List<Map<String, Object>>> styleMap, String userName, String tile)
            throws IOException {
        if ((header == null || header.length <= 0) && CollectionUtils.isEmpty(dataList)) {// 都是空的
            Exceptions.throwBusinessException("待写入的数据为空，写文件失败");
        }

        // 1. 判断列表是否有数据，如果没有数据，则返回
        // if (dataList == null || dataList.size() == 0) {
        // logger.error("待写入的数据为空");
        // throw new IOException("待写入的数据为空，写文件失败");
        // }

        if (styleMap == null) {
            if (header != null && header.length > 0) {
                styleMap = ExcelUtils.createDefaultTableStyle(header.length);
            } else {
                styleMap = ExcelUtils.createDefaultTableStyle(dataList.get(0).length);
            }
        }
        List<Map<String, Object>> headerStyleList = styleMap.get(table_header);
        List<Map<String, Object>> contantsStyleList = styleMap.get(table_contants);

        // 2. 向Workbook填充内容
        Workbook wb = new XSSFWorkbook();
        // 将datalist的内容写到Excel中
        Sheet sheet = null;
        if (StringUtils.isEmpty(sheetName)) {
            sheet = wb.createSheet();// 在文档对象中创建一个表单..默认是表单名字是Sheet0、Sheet1....
        } else {
            sheet = wb.createSheet(sheetName);// 在创建爱你表单的时候指定表单的名字
        }
        int t = 0;// 记录最新添加的行数
        if (!StringUtils.isEmpty(header)) {
            // 写固定头
            Row firstrow = sheet.createRow(t++);
            for (int i = 0; i < header.length; i++) {
                firstrow.createCell(i);
            }

            sheet.addMergedRegion(new CellRangeAddress(0, 0, header.length / 2, header.length - 1));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length / 2 - 1));

            Cell tempcell1 = sheet.getRow(0).getCell(0);
            logger.debug(tempcell1.getStringCellValue());
            tempcell1.setCellValue(tile);
            tempcell1.setCellStyle(headLeftCellStyle(wb));
            Cell tempcell2 = sheet.getRow(0).getCell(header.length / 2);
            logger.debug(tempcell2.getStringCellValue());
            tempcell2.setCellValue("日期：" + DateUtil.getDate() + "  制表人：" + userName);
            tempcell2.setCellStyle(headRightCellStyle(wb));

            // 写表头
            Row row = sheet.createRow(t++);
            for (int i = 0; i < header.length; i++) {
                Cell headCell = row.createCell(i);
                sheet.autoSizeColumn(i);
                headCell.setCellType(Cell.CELL_TYPE_STRING);// 设置这个单元格的数据的类型,是文本类型还是数字类型
                Map<String, Object> cellStyleMap = null;
                if (headerStyleList != null && headerStyleList.size() > i) {
                    cellStyleMap = headerStyleList.get(i);
                }

                // headCell.setCellStyle(defaultCellStyle(wb));// 设置表头样式
                headCell.setCellStyle(createCellStyle(wb, cellStyleMap));
                headCell.setCellValue(header[i]);// 给这个单元格设置值
            }

            logger.debug("要添加的数据总条数为：" + dataList.size());

            List<CellStyle> cellStyleList = new ArrayList<>();
            if(contantsStyleList != null && contantsStyleList.size() > 0) {
                for (Map<String, Object> cellStyleMap : contantsStyleList) {
                    cellStyleList.add(createCellStyle(wb, cellStyleMap));
                }
            }

            for (int i = 0; i < dataList.size(); i++) {
                String[] tmpStr = dataList.get(i);
                if (StringUtils.isEmpty(tmpStr)) {
                    continue;
                }
                Row r = sheet.createRow(t++);
                // 循环为新行创建单元格
                for (int j = 0; j < tmpStr.length; j++) {
                    Cell cell = r.createCell(j);// 获取数据类型
                    Map<String, Object> cellStyleMap = contantsStyleList.get(j);
                    Boolean isNum = false;
                    if(cellStyleMap != null) {
                    	isNum = CommHelper.getValue(cellStyleMap, StyleName_Cell_NUMERIC, Boolean.class);
                    	if(isNum == null) {
                    		isNum = false;
                    	}
                    }
                    if(isNum) {
                    	if(work.xingbili.common.utils.StringUtils.isBlank(tmpStr[j])) {
                    		cell.setCellValue("");
                    	}else {                    		
                    		cell.setCellValue(BigDecimalUtil.formatAmtToBigDecimal(tmpStr[j]).doubleValue());
                    		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    	}
                    }else {                    	
                    	cell.setCellValue(StringUtils.trimWhitespace(tmpStr[j]));
                    }
                    if (cellStyleList != null && cellStyleList.size() > j) {
                        cell.setCellStyle(cellStyleList.get(j));
                    } else {
                        cell.setCellStyle(createCellStyle(wb, null));
                    }
                    // cell.setCellStyle(contentCellStyle(wb));// 设置样式

                    // sheet.autoSizeColumn(i);//自动跳转列宽度
                }
            }
            // 自动设置列宽
            for (int columnNum = 0; columnNum < header.length; columnNum++) {
                int columnWidth = sheet.getColumnWidth(columnNum) / 256;
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row currentRow;
                    // 当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }

                    if (currentRow.getCell(columnNum) != null) {
                        Cell currentCell = currentRow.getCell(columnNum);
                        // int length = Encoding.Default.GetBytes(currentCell.toString()).Length;
                        int length = currentCell.toString().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
                columnWidth = (columnWidth + 1) * 256;
                if(columnWidth < 255*256) {
                	sheet.setColumnWidth(columnNum, columnWidth);
                }else {
                	sheet.setColumnWidth(columnNum, 6000);
                }
                
            }
        }
        // 3. 返回完成的Workbook
        return wb;
    }

    /**
     *下载excel表格
     * @param wb
     * @param request
     * @param response
     * @param fileName
     * @author  yangfan
     * @date  2021/5/17 17:54
     *
     **/
    public static void downLoadExcel(
            Workbook wb, HttpServletRequest request, HttpServletResponse response, String fileName)
            throws Exception {
        OutputStream out = null; // 给客户端的输出流
        try {
            String rtn =
                    "filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"";
            if (request == null) {
            } else {
                String userAgent = request.getHeader("User-Agent");
                if (userAgent != null) {
                    String new_filename = URLEncoder.encode(fileName, "utf-8");
                    userAgent = userAgent.toLowerCase();
                    // IE浏览器，只能采用URLEncoder编码
                    if (userAgent.indexOf("msie") != -1) {
                        rtn = "filename=\"" + new_filename + "\"";
                    }
                    // Opera浏览器只能采用filename*
                    else if (userAgent.indexOf("opera") != -1) {
                        rtn = "filename*=UTF-8''" + new_filename;
                    }
                    // Safari浏览器，只能采用ISO编码的中文输出
                    else if (userAgent.indexOf("safari") != -1) {
                    }
                    // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
                    else if (userAgent.indexOf("applewebkit") != -1) {
                        rtn = "filename=\"" + new_filename + "\"";
                    }
                    // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
                    else if (userAgent.indexOf("mozilla") != -1) {
                        rtn = "filename*=UTF-8''" + new_filename;
                    }
                }
            }

            // 将Workbook传回客户端
            response.reset();
            response.setContentType("application/x-xls");
            response.setHeader("Content-Disposition", "attachment; " + rtn);
            out = response.getOutputStream();
            wb.write(out);
            wb.close();
            out.flush();
        } finally {
            IOUtils.close(out);
        }
    }

}
