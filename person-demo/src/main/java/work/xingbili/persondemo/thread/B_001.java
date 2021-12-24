/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.thread;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/12/23 8:33
 */
class T implements Runnable{

    private  int count ;
    @Override
    public void run() {
            while (true) {
                synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                try {
                    this.notifyAll();
                    this.wait();
                    count++;
                    if (count == 2) {
                        this.notifyAll();
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public int getCount(){
        return this.count;
    }
}
public class B_001 {
    public static void main(String[] args) {
            T t = new T();
            Thread thread = new Thread(t);
            thread.start();
            while (true) {
                synchronized (t) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                try {
                    t.notifyAll();
                    t.wait();
                    if (t.getCount() == 2) {
                        t.notifyAll();
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(t.getCount());
    }
}
