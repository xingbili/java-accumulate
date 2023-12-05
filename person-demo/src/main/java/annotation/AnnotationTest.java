/*
 *  版权信息: © 聚均科技
 */

package annotation;

import java.lang.reflect.Method;

/**
 * @author xinghuolin
 * @date 2023/3/1 15:08
 */
public class AnnotationTest {


  @SignCheck(iValue = 1,
      douValue = 1.0d,
      lonValue = 4124L,
      floValue = 451f,
      chValue = 'H',
      booValue = true,
      shoValue = 2,
      byteValue = 120,
      stringValue = "这是字符串",
      annocationValue = @ExampleAnnoation,
      classValue = String.class,//随便一个class对象
      enumValue = WeekEnum.Wednesday,
      iListValue = {1, 2, 3, 4, 5})
  public void annotationTest() {

  }


  @RunBefore(run_name = "测试注解运行")
  public void testAnnotation() {

  }

  public static void main(String[] args) throws NoSuchMethodException {
    Class<AnnotationTest> annotationTestClass = AnnotationTest.class;
    //反射获取testAnnotation方法 这里只举例Method类
    Method method = annotationTestClass.getMethod("testAnnotation");

    //判断是否存在某个注解对象
    boolean isExist = method.isAnnotationPresent(RunBefore.class);
    System.out.println("是否存在目标注解：" + isExist);

    //反射获取目标注解对象
    RunBefore runBefore = method.getAnnotation(RunBefore.class);
    System.out.println("注解对象属性：" + runBefore.run_name());
  }

}