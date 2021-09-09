/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.pdfutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import work.xingbili.pdfutil.dto.AttachTableDto;
import work.xingbili.pdfutil.dto.DataObj;
import work.xingbili.pdfutil.dto.PdfDataDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.*;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/9/8 15:12
 */
public class PdfTool {

    private String pdfTemplatePath;
    private String pdfTargetPath;
    private Font font;

    public static void main(String[] args) throws Exception {
        String templatePath="D:\\file\\模板.pdf";
        String outputPath="D:\\file\\最终文件.pdf";


        // 附加表格
        List<AttachTableDto> attachTableDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AttachTableDto attachTableDto = new AttachTableDto();
            attachTableDto.setTittle("附件表格1：员工信息表");
            Map colsAttr = new HashMap();
            colsAttr.put("编号",0.2f);
            colsAttr.put("姓名",0.3f);
            colsAttr.put("地址",0.8f);
            attachTableDto.setColsName(new String[]{"编号","姓名","性别","地址"});

            // 获取Array
            List<DataObj> dataObjs  = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                DataObj DataObj = new DataObj();
                DataObj.setName("第"+j+"");
                DataObj.setNo(j);
                DataObj.setAddress("测试地址测试地址测试地址测试地址测试地址测试地址测试地址测试地址" +j);
                DataObj.setSex("男");
                dataObjs.add(DataObj);
            }
            attachTableDto.setListData(dataObjs);

          attachTableDtoList.add(attachTableDto);
        }

        // 表单数据
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

        // PDF数据
        PdfDataDto pdfDataDto = new PdfDataDto();
        pdfDataDto.setFormData(stringObjectHashMap);
        pdfDataDto.setAttachTableList(attachTableDtoList);

        // 填充数据
        fillPdf(pdfDataDto,templatePath,outputPath);

    }

    /**
     * @des:
     * @param:
     * @return:
     * @author: xinghuolin
     * @date: 17:25
     */
    public static void fillPdf(PdfDataDto pdfDataDto, String templatePdfPath, String outPdfPath) throws Exception {
        //pdf模板
        String ret = templatePdfPath;
        // 有附件表格
        if (pdfDataDto.getAttachTableList()!=null) {
            String tempPath = templatePdfPath.substring(0, templatePdfPath.lastIndexOf("\\"));
            tempPath = tempPath+"\\temp.pdf";
            fillPdfTemplate(ret, tempPath, pdfDataDto.getFormData());
            generateFinalPdf(tempPath, outPdfPath, pdfDataDto.getAttachTableList());
            File file = new File(tempPath);
            file.delete();
        }else{
            // 没有附件表格 直接填充表单域
            // 1.读取pdf模板并写入数据
            fillPdfTemplate(ret, outPdfPath, pdfDataDto.getFormData());
        }

    }

    /**
     * 生成最终版本的pdf
     *
     * @param
     * @param newPath   已写入数据的pdf模板路径
     * @param finalPath 最终版本的pdf生成路径
     * @throws Exception
     */
    private static void generateFinalPdf(String newPath, String finalPath,List<AttachTableDto> listDataDto) throws Exception {

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
        for (AttachTableDto attachTableDto:listDataDto) {
            Paragraph paragraph = attachTable(attachTableDto);
            document.add(paragraph);
        }
        document.close();
        reader.close();
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
     * @return Paragraph
     * @see
     */
    private static Paragraph attachTable(AttachTableDto listDataDto) throws Exception {
        // 如果对象列表对象为空
        if(listDataDto==null){
            throw new Exception("生成合同模板的数据不正确");
        }
        int colCount = listDataDto.getColsName().length;
        // 设置字体字号
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 10.5F, Font.BOLD);
        // 绘制表格标题
        Paragraph ret = new Paragraph(listDataDto.getTittle(), fontChinese);
        // 根据字段拆分表格的列
        PdfPTable tableBox = new PdfPTable(colCount);

        // 设置表格的占pdf宽度的百分比
        tableBox.setWidthPercentage(100f);
        // 添加表头
        for (Object colName : listDataDto.getColsName()) {
            PdfPCell cell = new PdfPCell(new Phrase(colName.toString(), fontChinese));
            //垂直居中
            cell.setUseAscender(true);
            cell.setNoWrap(false);
            tableBox.addCell(cell);
        }
        // 列表数据为空默认添加两行数据
        if(listDataDto.getListData()==null||listDataDto.getListData().size()==0){
            // 添加两行空数据
            for (int i = 0; i <= 2 ; i++) {
                PdfPCell cell = new PdfPCell(new Phrase("", fontChinese));
                //垂直居中
                cell.setUseAscender(true);
                cell.setNoWrap(false);
                tableBox.addCell(cell);
            }
        }
        // 获取泛型类型的自定义的所有属性字段 填充列表数据
        Field[] fields = listDataDto.getListData().get(0).getClass().getDeclaredFields();

        //遍历泛型list
        listDataDto.getListData().stream().forEach(o->{
            Arrays.stream(fields).forEach(f->{
                f.setAccessible(true);
                //获取属性值
                Object value = null;
                try {
                    value = f.get(o);
                    PdfPCell cell = new PdfPCell(new Phrase(value.toString(), fontChinese));
                    //垂直居中
                    cell.setUseAscender(true);
                    cell.setNoWrap(false);
                    tableBox.addCell(cell);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        });
        ret.add(tableBox);
        return ret;
    }
}
