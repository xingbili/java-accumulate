package work.xingbili.pdfutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xinghuolin
 * @des: TODO
 * @date 2021/9/6 16:59
 */
public class pdfUtil {


    public static void main(String[] args) throws Exception {

//        genericFile();
        //模板内所需要的数据（测试数据）
        //字段过长 会不显示
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("ID", "单号");
        stringObjectHashMap.put("CreateTime", "创建时间");
        stringObjectHashMap.put("Account", "账户");
        stringObjectHashMap.put("Bank", "开户行");
        stringObjectHashMap.put("BankAddr", "开户地址");
        stringObjectHashMap.put("AccountTime", "开户时间");
        stringObjectHashMap.put("BusinessName", "业务名称业务名称业务");
        stringObjectHashMap.put("AccountName", "账户名称");
        stringObjectHashMap.put("Content", "备注");
    }


    /**
     *@des:
     *@param:
     *@return:
     *@author: xinghuolin
     *@date: 17:25
     */
    public static void genericFile(Map<String,Object> dataMap,String templatePdfPath, String outPdfPath) throws Exception {

        //pdf模板
        String ret = templatePdfPath;
        String tempPath = "D:\\file\\temp.pdf";
        // 1.读取pdf模板并写入数据
        fillPdfTemplate(ret, tempPath, dataMap);
        // String type = "create";
        generateFinalPdf(tempPath,  outPdfPath);
        File file = new File(tempPath);
        file.delete();
    }

    /**
     * 填充PDF模板
     *
     * @param pdfTemplate pdf模板文件路径 eg: "D:/source/pdfTemplate.pdf"
     * @param outputFile  目标文件路径 eg: "D:/target/outputFile.pdf"
     * @param dataMap     填充的数据 key 字段名称  value 填充的值
     * @throws IOException
     * @throws com.itextpdf.text.DocumentException
     * @author xinghuolin
     * @date 2021/8/27 10:30
     */
    public static void fillPdfTemplate(String pdfTemplate, String outputFile, Map<String, Object> dataMap)
            throws Exception {
        //初始化itext
        PdfStamper pdfStamper = null;
        try {
            BaseFont baseFont = BaseFont.createFont
                    ("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

            PdfReader pdfReader = new PdfReader(pdfTemplate);
            pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outputFile));
            AcroFields form = pdfStamper.getAcroFields();
            form.addSubstitutionFont(baseFont);

            //写入数据
            for (String key : dataMap.keySet()) {
                String value = dataMap.get(key).toString();
                //key对应模板数据域的名称
                form.setField(key, value);
            }

        } catch (DocumentException e) {
            throw new Exception(e.getMessage(), e);
        } catch (IOException e) {
            throw new Exception(e.getMessage(), e);
        } finally {
            if (pdfStamper != null) {
                try {
                    //设置不可编辑
                    pdfStamper.setFormFlattening(true);
                    pdfStamper.close();
                } catch (DocumentException e) {
                    throw new Exception(e.getMessage(), e);
                } catch (IOException e) {
                    throw new Exception(e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 生成pdf表格
     *
     * @return
     * @see
     */

    private static <T> Paragraph generatePdfATATable() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        // 五号
        Font fontChinese = new Font(bfChinese, 10.5F, Font.BOLD);

        Paragraph ret = new Paragraph("附表1： 附件表格1", fontChinese);
        PdfPTable tableBox = new PdfPTable(3);
        // 每个单元格占多宽
        tableBox.setWidths(new float[]{0.6f, 0.2f, 0.2f});
        tableBox.setWidthPercentage(100f);
        // 获取Array
        List<DataObj> ataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DataObj DataObj = new DataObj();
            DataObj.setAta("" + i);
            DataObj.setFileNumber(i);
            DataObj.setDMNumber(i);
            ataList.add(DataObj);
        }

        // 创建表格格式及内容
        tableBox.addCell(getCell(new Phrase("测试表格标题", fontChinese), false, 3, 1));
        tableBox.addCell(getCell(new Phrase("测试", fontChinese), false, 1, 1));
        tableBox.addCell(getCell(new Phrase("第二列", fontChinese), false, 1, 1));
        tableBox.addCell(getCell(new Phrase("第三列", fontChinese), false, 1, 1));
        // 遍历查询出的结果
        for (DataObj ata : ataList) {
            tableBox.addCell(getCell(new Phrase(ata.getAta(), fontChinese), false, 1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(ata.getFileNumber()), fontChinese), false, 1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(ata.getDMNumber()), fontChinese), false, 1, 1));
        }
        ret.add(tableBox);
        return ret;
    }


    private static PdfPCell getCell(Phrase phrase, boolean yellowFlag, int colSpan, int rowSpan) {

        PdfPCell cells = new PdfPCell(phrase);

        cells.setUseAscender(true);
        cells.setMinimumHeight(20f);
        cells.setHorizontalAlignment(1);
        cells.setVerticalAlignment(5);
        cells.setColspan(colSpan);
        cells.setRowspan(rowSpan);
        cells.setNoWrap(false);
        return cells;
    }


    /**
     * 生成最终版本的pdf
     *
     * @param
     * @param newPath   已写入数据的pdf模板路径
     * @param finalPath 最终版本的pdf生成路径
     * @throws Exception
     */
    private static void generateFinalPdf(String newPath, String finalPath) throws Exception {

        FileOutputStream outputStream = new FileOutputStream(finalPath);
        // 读取pdf模板
        PdfReader reader = new PdfReader(newPath);
        Rectangle pageSize = reader.getPageSize(1);
        Document document = new Document(pageSize);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cbUnder = writer.getDirectContentUnder();
        PdfImportedPage pageTemplate = writer.getImportedPage(reader, 1);
        cbUnder.addTemplate(pageTemplate, 0, 0);
        //新创建一页来存放后面生成的表格
        document.newPage();
        Paragraph paragraph = generatePdfATATable();
        document.add(paragraph);
        document.close();
        reader.close();

    }
}
