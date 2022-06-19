/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Comparable & Comparator(类似策略模式) 介绍
 *
 * @author xinghuolin
 * @date 2022/6/19 11:00
 */
public class TestComparable {
    public static void main(String[] args) {
        Person person1 = new Person(1, "张三2");
        Person person2 = new Person(1, "李四");
        System.out.println(person1.compareTo(person2));

        PersonComparator personPersonComparator = new PersonComparator();
        System.out.println(personPersonComparator.compare(person1, person2));
    }
}

@Data
@AllArgsConstructor
class Person implements Comparable<Person> {
    private int age;
    private String name;

    @Override
    public int compareTo(Person o) {
        if (this.getAge() > o.getAge()) {
            return 1;
        } else if (this.age == o.age) {
            return 0;
        } else {
            return -1;
        }

    }
}
