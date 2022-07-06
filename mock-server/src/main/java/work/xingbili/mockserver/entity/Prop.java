/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.mockserver.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xinghuolin
 * @date 2022/5/27 10:12
 */
@Data
public class Prop {

    @NotNull(message = "pid不能为空")
    @Min(value = 1, message = "ipd必须为正整数")
    private Long pid;

    @NotNull(message = "vid不能为空")
    @Min(value = 1, message = "vid必须为正整数")
    private Long vid;

    @NotBlank(message = "pidName不能为空")
    private String pidName;

    @NotBlank(message = "vidName不能为空")
    private String vidName;
}
