/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.java8;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/1/30 21:15
 */
public class Java8Tester {
    public static void main(String[] args) {

        Java8Tester java8Tester = new Java8Tester();
        MathOperation addition = (int a,int b)-> a+b;
        MathOperation subtraction=(a,b)->a-b;
        MathOperation multiplication =( a,b)->{return a*b;};
        MathOperation division =(a,b)->a/b;
    }

    interface  MathOperation{
        int operation(int a,int b);
    }
}
