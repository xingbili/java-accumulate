/**
 * xingbili
 */

package work.xingbili.controller;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: xinghuolin
 * @create: 2021/11/30 21:10
 */
@Data
public class World {
  private   List<Person> personList;
 private    String name ;
   private String worldColor;
}
