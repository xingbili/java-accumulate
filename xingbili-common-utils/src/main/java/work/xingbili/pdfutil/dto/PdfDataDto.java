/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.pdfutil.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @des: PDF 填充数据
 * @author xinghuolin
 * @date 2021/9/9 9:24
 */
@Data
public class PdfDataDto {
    /**
    * 表单数据
    */
    Map<String,Object> formData ;
    /**
    * 附件表格
    */
    List<AttachTableDto> attachTableList;
}
