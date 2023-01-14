/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.seqid;

import java.util.ArrayList;

/**
 * @author xinghuolin
 * @date 2022/12/6 13:39
 */
public class TestIncrID {


  private static volatile int count = 1;
  private static volatile ArrayList<Integer> al = new ArrayList<Integer>();

  public static void main(String[] args) {

    Thread addOrder = new Thread(() -> {

      while (true) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (al.size() < 11) {

          System.out.println(count + "号,进入排队状态");
          // System.out.println(al);
          al.add(count);
          count = count + 1;
        }
      }

    });

    Thread delOrder1 = new Thread(() -> {

      while (true) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (al.size() > 0) {
          System.out.println("请第" + al.remove(0) + "号去诊室1就诊");
        }
      }

    });
    Thread delOrder2 = new Thread(() -> {

      while (true) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if (al.size() > 0) {

          // System.out.println(count);
          // System.out.println(al);
          System.out.println("请第" + al.remove(0) + "号去诊室2就诊");
          // count = count + 1;
        }
      }

    });

    addOrder.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    delOrder1.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    delOrder2.start();
  }
}