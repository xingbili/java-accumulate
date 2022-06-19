/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.java8;

import lombok.Data;

import java.util.Optional;

/**
 *
 *
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
        System.out.println( Optional.empty());


        Animal animal1 = new Animal();
        animal1.setAge(2);
        System.out.println(  Optional.ofNullable(animal1).filter(p->p.getAge()==2).get());
    }

}

@Data
class Animal {
    private Integer age;
    private String name;

}
