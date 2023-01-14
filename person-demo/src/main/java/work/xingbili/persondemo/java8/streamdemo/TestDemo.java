/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.java8.streamdemo;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @author xinghuolin
 * @date 2022/8/27 11:27
 */
public class TestDemo {

  @Test
  public void testPeekForeach() {
    List<String> sentences = Arrays.asList("hello world", "Jia Gou Wu Dao");
    // 演示点1： 仅peek操作，最终不会执行
    System.out.println("----before peek----");
    sentences.stream().peek(System.out::println);
    System.out.println("----after peek----");
    // 演示点2： 仅foreach操作，最终会执行
    System.out.println("----before foreach----");
    sentences.forEach(System.out::println);
    System.out.println("----after foreach----");
    // 演示点3： peek操作后面增加终止操作，peek会执行
    System.out.println("----before peek and count----");
    sentences.stream().peek(System.out::println).count();
    System.out.println("----after peek and count----");
  }

}