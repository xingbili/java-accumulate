/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * @author code generator
 * @since 2021-12-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_api_response")
public class TbApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private  Long   id;

    /**
     * api请求路径关联 api信息表
     */
    @TableField("api_path")
    private  String   apiPath;

    /**
     * 返回信息body
     */
    @TableField("api_response_body")
    private  String   apiResponseBody;

    /**
     * 接口关键字值信息
     */
    @TableField("api_keyword_value")
    private  String   apiKeywordValue;


}
