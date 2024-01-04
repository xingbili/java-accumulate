/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author xinghuolin
 * @date 2023/12/11 16:28
 */

@Data
public class Child {
  private String name;
  private Integer age;
  private Date birthDay;
  private List<String> text; //数组
}