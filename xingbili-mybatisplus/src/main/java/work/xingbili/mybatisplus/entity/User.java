/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author code generator
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private  Long   id;

    /**
     * 姓名
     */
    @TableField("name")
    private  String   name;

    /**
     * 年龄
     */
    @TableField("age")
    private  Integer   age;

    /**
     * 邮箱
     */
    @TableField("email")
    private  String   email;


}
