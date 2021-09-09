/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.pdfutil.dto;

import lombok.Data;

import java.util.List;

/**
 * @des: 附件表格
 * @author xinghuolin
 * @date 2021/9/9 9:15
 */
@Data
public class AttachTableDto<T> {
    /**
    * 附件表格标题
    */
    String tittle;
    /**
     * 表格的列名
     */
    String[] colsName;
    /**
    * 表格的数据
    */
    List<T> listData;

}
