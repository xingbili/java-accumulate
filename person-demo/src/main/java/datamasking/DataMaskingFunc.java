/*
 *  版权信息: © 聚均科技
 */

package datamasking;

import org.springframework.util.StringUtils;

/**
 * @author xinghuolin
 * @date 2022/8/27 10:03
 */
public enum DataMaskingFunc {

  /**
   * 脱敏转换器
   */
  NO_MASK((str, maskChar) -> {
    return str;
  }),
  ALL_MASK((str, maskChar) -> {
    if (StringUtils.hasLength(str)) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < str.length(); i++) {
        sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
      }
      return sb.toString();
    } else {
      return str;
    }
  });

  private final DataMaskingOperation operation;

  private DataMaskingFunc(DataMaskingOperation operation) {
    this.operation = operation;
  }

  public DataMaskingOperation operation() {
    return this.operation;
  }

}