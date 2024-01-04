/**
 * 版权信息: © 聚均科技
 */

package work.xingbili;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author xinghuolin
 * @des: TODO
 * @date 2021/9/7 9:23
 */
public class MyGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "D:/";
        String projectName = "code-generator";
        gc.setOutputDir(projectPath + "/" + projectName + "/src/main/java");
        gc.setAuthor("code generator");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setIdType(IdType.ASSIGN_ID);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.91.3:3306/db_test?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("work.xingbili");
        pc.setService("service.base");
        pc.setServiceImpl("service.base.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义模板
        List<FileOutConfig> focList = new ArrayList<>();
        // entity模板
        focList.add(new FileOutConfig("/codeTemplates/entity.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/java/work/xingbili/" + generateModulePath(pc.getModuleName()) + "/entity"
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        // mapper模板
        focList.add(new FileOutConfig("/codeTemplates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/java/work/xingbili/" + generateModulePath(pc.getModuleName()) + "/mapper"
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });

        // mapper.xml模板
        focList.add(new FileOutConfig("/codeTemplates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/resources/mappers/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });

        // service模板
        focList.add(new FileOutConfig("/codeTemplates/service.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/java/work/xingbili/" + generateModulePath(pc.getModuleName()) + "/service/base"
                        + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });

        // serviceImpl模板
        focList.add(new FileOutConfig("/codeTemplates/serviceImpl.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/java/work/xingbili/" + pc.getModuleName() + "/service/base/impl"
                        + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });

        // controller模板
        focList.add(new FileOutConfig("/codeTemplates/controller.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + projectName + "/src/main/java/work/xingbili/" + pc.getModuleName() + "/controller"
                        + "/" + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig()
                .setEntity(null)
                .setMapper(null)
                .setXml(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setVersionFieldName("version");
        strategy.setTableFillList(Arrays.asList(new TableFill("version", FieldFill.INSERT)));
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 获取模板路径
     *
     * @param moduleName
     * @return
     */
    private static String generateModulePath(String moduleName) {
        return org.springframework.util.StringUtils.replace(moduleName, ".", "/");
    }
}