/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.factorymethod;



/**
 * @author xinghuolin
 * @des: TODO
 * @date 2021/12/22 8:29
 */
interface Food {
    void eat();
}

class Noodle implements Food {
    @Override
    public void eat() {
        System.out.println("吃面条");
    }
}

class Rice implements Food {
    @Override
    public void eat() {
        System.out.println("吃米饭");
    }
}

interface FoodFactory{
    Food getFood();
}

class NoodleFactory implements FoodFactory{

    @Override
    public Food getFood() {
        return new Noodle();
    }
}


class RiceFactory implements FoodFactory{

    @Override
    public Food getFood() {
        return new Rice();
    }
}

/**
 * @author xinghuolin
 */
public class AppTest {
    public static void main(String[] args) {
        FoodFactory foodFactory = new NoodleFactory();
        Food food = foodFactory.getFood();
        food.eat();
    }
}
