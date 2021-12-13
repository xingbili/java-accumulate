/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 通用返回对象
 * </p>
 *
 * @author xinghuolin
 * @date 2021/04/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class GeneralResponse<T> implements Serializable {

    /**
     * 响应码
     */
    private String rspCode;

    /**
     * 响应描述
     */
    private String rspDesc;

    /**
     * 报文体
     */
    private T data;
}
