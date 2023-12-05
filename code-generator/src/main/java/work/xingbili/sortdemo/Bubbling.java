/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.sortdemo;

/**
 * @author xinghuolin
 * @date 2023/7/19 9:42
 */
public class Bubbling {

  /**
   * 冒泡排序
   *
   * @param array
   * @return
   */
  public static int[] bubbleSort(int[] array) {
    if (array.length == 0) {
      return array;
    }
    boolean exchange;
    for (int i = 0; i < array.length; i++) {
      exchange = false;
      System.out.println("i="+i);
      for (int j = 1; j < array.length - 1 - i; j++) {
        System.out.println("j="+j);
        if (array[j + 1] < array[j]) {
          System.out.println("a"+":"+array[j+1]+","+array[j]);
          int temp = array[j + 1];
          array[j + 1] = array[j];
          array[j] = temp;
          exchange = true;
        }
      }
      if(!exchange) {
        return array;
      }
    }
    return array;
  }

  public static void main(String[] args) {
    int[] array = {1,4,5,6,3,8,11,29,32};
    bubbleSort(array);

//    Arrays.stream(bubbleSort(array)).forEach(System.out::println);
  }

}