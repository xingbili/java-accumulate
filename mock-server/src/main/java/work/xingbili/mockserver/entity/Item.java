/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.mockserver.entity;


import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/5/27 10:08
 */
@Data
public class Item implements Serializable {

    @NotNull(message = "id不能为空",groups = {First.class})
    @Min(value=1,message = "id必须为正整数")
    private Long id;

    @Valid
    @NotEmpty(message = "props 不能为空")
    private List<Prop> props;
}