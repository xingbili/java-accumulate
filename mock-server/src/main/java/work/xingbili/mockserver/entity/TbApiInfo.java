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
@TableName("tb_api_info")
public class TbApiInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private  Long   id;

    /**
     * api请求路径
     */
    @TableField("api_path")
    private  String   apiPath;

    /**
     * api名称描述
     */
    @TableField("api_name")
    private  String   apiName;

    /**
     * 接口请求关键字
     */
    @TableField("api_keyword")
    private  String   apiKeyword;


}
