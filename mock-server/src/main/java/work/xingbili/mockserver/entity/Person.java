/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.mockserver.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author xinghuolin
 * @date 2023/2/15 15:27
 */
public class Person {

  @JSONField
  private String name;
  private String job;
}