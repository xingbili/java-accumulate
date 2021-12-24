/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.thread;

/**
 * @des: 多线程控制输出顺序
 * @author xinghuolin
 * @date 2021/12/21 8:59
 */
class PrintNum implements Runnable{
    Foo foo;
    PrintNum(Foo foo){
        this.foo = foo;
    }
    @Override
    public void run() {
        for (int i = 0; i < 52; i++) {
            synchronized (foo){
                try {
                    if(i%2==0) {
                        foo.notifyAll();
                        foo.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
        }
    }
}
class   PrintWord implements Runnable{
    Foo foo;
    PrintWord(Foo foo){
        this.foo = foo;
    }
    @Override
    public void run() {
        for (int i = 'A'; i <= 'Z'; i++) {
            synchronized (foo){
                try {
                    foo.notifyAll();
                    foo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println((char)i);
        }
    }
}
class Foo {
    int x = 1;
}
public class A_001 {

    public static void main(String[] args) {
        Foo foo = new Foo();
        PrintNum printNum = new PrintNum(foo);
        PrintWord printWord = new PrintWord(foo);
        Thread A  = new Thread(printNum);
        Thread B = new Thread(printWord);
        A.start();
        B.start();
    }

}
