/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.common.api;

/**
 * 常用API返回对象接口
 *
 * @author xinghuolin
 * @date 2021/9/8 10:26
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}