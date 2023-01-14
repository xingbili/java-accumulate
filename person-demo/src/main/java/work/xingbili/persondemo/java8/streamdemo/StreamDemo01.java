/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.java8.streamdemo;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xinghuolin
 * @date 2022/8/22 10:30
 */
public class StreamDemo01 {
//    public static void main(String[] args) {
////        List<String> l = new ArrayList(Arrays.asList("one", "two"));
////        Stream<String> sl = l.stream();
////        sl.forEach(s -> l.add("three"));
//
//
////        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
////        // 使用流操作
////        List<String> results = sentences.stream()
////            .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
////            .collect(Collectors.toList());
////        System.out.println(results);
//   List<Person> list = new ArrayList<>();
//   list.add(new Person("zhangsan",9));
//        Tuple2<List<Person>, Integer> java8 = Tuple.of(list, 8);
//        System.out.println(java8._1);
//
//    }

    public static void main(String[] args){
//        Map<String,String> map =null;// new HashMap<>();
////        map.put("1","你好");
////        map.put("2","haha");
//        List<Long> ss = Collections.emptyList();
//        System.out.println(ss);


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",19));
        personList.add(new Person("李四",20));
        personList.forEach((item)->{
            if("张三".equals(item.getName())){
                item.setAge(99);
            }
        });
        System.out.println(personList);
    }

}
@Data
@AllArgsConstructor
class Person{
    private String name ;
    private Integer age;
}