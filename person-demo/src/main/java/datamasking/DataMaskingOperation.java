/*
 *  版权信息: © 聚均科技
 */

package datamasking;

/**
 * @author xinghuolin
 * @date 2022/8/27 10:07
 */
public interface DataMaskingOperation {

  String MASK_CHAR = "*";

  String mask(String content, String maskChar);
}