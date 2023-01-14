/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.listhandle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghuolin
 * @date 2022/12/1 14:43
 */
public class Test {

  public static List<Integer> list = new ArrayList<>();

  static {
    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }
  }

  public static void main(String[] args) {

    System.out.println(list);

  }

}