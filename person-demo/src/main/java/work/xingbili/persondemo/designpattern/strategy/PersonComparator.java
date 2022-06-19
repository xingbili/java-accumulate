/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy;

import java.util.Comparator;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/6/19 11:24
 */
public class PersonComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getName().length()>o2.getName().length()){
            return 1;
        }else {
            return -1;
        }
    }
}
