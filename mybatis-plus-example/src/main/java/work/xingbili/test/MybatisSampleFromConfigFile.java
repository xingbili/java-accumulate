/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/2/20 20:44
 */
public class MybatisSampleFromConfigFile {
    public static void main(String[] args) throws IOException {
        String resource ="src/main/resources/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }
}
