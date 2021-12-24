/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.factorymethod;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/12/22 8:29
 */
interface Food{
   public void eat();
}
class Noodle implements Food {
    @Override
    public  void eat(){
        System.out.println("吃面条");
    }
}

class Rice implements Food {
    @Override
    public  void eat(){
        System.out.println("吃米饭");
    }
}

class FoodFactory {
    Food food;
    public void eat(int type)
    {
        switch (type){
            case 1:
                food = new Noodle();
                food.eat();
                break;
            case 2:
                food=  new Rice();
                food.eat();
                break;
        }
    }
}
public class AppTest {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        foodFactory.eat(1);
    }
}
