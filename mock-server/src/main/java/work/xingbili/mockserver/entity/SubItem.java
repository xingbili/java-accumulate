/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.mockserver.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author xinghuolin
 * @date 2022/7/15 14:01
 */
@Data
public class SubItem extends Item implements Serializable {

    @NotEmpty(message = "name不能为空", groups = {First.class})
    private String strName;
}