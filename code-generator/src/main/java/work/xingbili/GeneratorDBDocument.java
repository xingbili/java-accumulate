/**
 * 版权信息: © 聚均科技
 */

package work.xingbili;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/11/4 8:45
 */
public class GeneratorDBDocument {

    public static void main(String[] args) {
        // 数据源配置
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://10.30.4.53:3306/fusion-fin-mock?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);

        // 1、生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("D:\\dbdoc")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        // 忽略表名
        List<String> ignoreTableName = Arrays.asList("test");
        // 忽略表前缀
        List<String> ignorePrefix = Arrays.asList("test_", "test");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_test", "test");

        // 2、配置想要忽略的表
        ProcessConfig processConfig = ProcessConfig.builder().ignoreTableName(ignoreTableName)
                .ignoreTablePrefix(ignorePrefix).ignoreTableSuffix(ignoreSuffix).build();

        // 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder().version("1.0.0").description("数据库文档").dataSource(dataSource)
                .engineConfig(engineConfig).produceConfig(processConfig).build();

        // 4、执行生成
        new DocumentationExecute(config).execute();
    }

}
