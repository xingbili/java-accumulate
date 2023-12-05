/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author xinghuolin
 */
public class FindMaxCount {
    public static String loadFile(String path) {
        try {
            Reader in  = new FileReader(path);
            BufferedReader br = new BufferedReader(in);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //String str =loadFile("D://test.txt");
        //System.out.println(str);

        List<Body> numbers = Arrays.asList(new Body(1,"hand1","head1"),new Body(1,"hand2","head2"));
        List<Integer> ages = numbers.stream().distinct().map(Body::getAge).collect(Collectors.toList());
        System.out.println(ages.toString());
    }
}

/**
 * @author xinghuolin
 * @date 2021/12/30 8:42
 */
@Data
@AllArgsConstructor
class Body{
    Integer age;
    String hand;
    String head;
}