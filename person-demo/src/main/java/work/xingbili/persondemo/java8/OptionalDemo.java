/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.java8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author xinghuolin
 * @date 2022/2/25 12:30
 */
public class OptionalDemo {

  public static void main(String[] args) {
    // 不用Optional
    //List<Animal> zoo = new ArrayList<>();
    //Animal animal = manCatch();
    //if(animal != null){
    //    //zoo.add(animal);
    //}

    // optional.empty.
    System.out.println(Optional.empty());

    Animal animal1 = new Animal();
    animal1.setAge(2);
    animal1.setName("小狗");
    Animal animal2 = new Animal();
    animal2.setAge(3);
    animal2.setName("小猫");
    List zoo = new ArrayList<>();
    zoo.add(animal1);
    zoo.add(animal2);
    zoo.add(1);
    changeList(zoo);

    System.out.println(zoo);
    System.out.println(Optional.ofNullable(animal1).filter(p -> p.getAge() == 2).get());
  }

  public static void changeList(List<Animal> list) {
    list.forEach((item) -> {
      item.setAge(2);
      item.setName(item.getName() + "hahah");
    });
  }
}

@Data
class Animal {

  private Integer age;
  private String name;

}