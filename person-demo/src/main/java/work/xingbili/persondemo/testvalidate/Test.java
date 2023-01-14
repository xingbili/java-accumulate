/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.testvalidate;

import javax.validation.Valid;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/5/27 9:38
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student();

        method1(student);

    }

    public static void method1(@Valid Student student){

        System.out.println(student);
    }

}
