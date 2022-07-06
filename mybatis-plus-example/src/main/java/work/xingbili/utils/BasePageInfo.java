/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author xiaokedamowang
 * @version 1.0
 * @date 2021/12/27 19:59
 */
@Getter
@Setter
@Accessors(chain = true)
public class BasePageInfo {

    /**
     * 创建人ID : 创建人ID
     */
    @ApiModelProperty("创建人ID")
    private Long createUser;

    /**
     * 创建人姓名 : 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 创建时间 : 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    /**
     * 更新人ID : 更新人ID
     */
    @ApiModelProperty("更新人ID")
    private Long updateUser;

    /**
     * 更新人姓名 : 更新人姓名
     */
    @ApiModelProperty("更新人姓名")
    private String updateUserName;

    /**
     * 更新时间 : 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateDate;
}
