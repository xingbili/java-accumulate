/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.simplefactory;

/**
 * 作者
 * 用户
 * 产品，
 * 抽象产品，
 * 产品簇，
 * 产品等级
 *
 * @author xinghuolin
 * @date 2022/6/24 22:05
 */


interface Food {
    void eat();
}


class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

class Rice implements Food {

    @Override
    public void eat() {
        System.out.println("吃大米");
    }
}

class FoodFactory {
    public static Food getFood(int type) {
        if (type == 1) {
            return new Hamburger();
        } else if (type == 2) {
            return new Rice();
        }else {
            throw new RuntimeException("没有");
        }
    }
}

/**
 * @author xinghuolin
 */ // 上面作者，下面是客户调用
//+++++++++++++++++++++++==============================================
public class AppTest {
    public static void main(String[] args) {
        Food food = FoodFactory.getFood(2);
        food.eat();
    }
}