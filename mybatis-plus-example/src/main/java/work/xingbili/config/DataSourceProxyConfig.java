/**
 * 版权信息: © 聚均科技
 */
package work.xingbili.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangfan
 * @date 2021/05/03
 */
@Configuration
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean("dataSourceProxy")
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean("txManager")
    public DataSourceTransactionManager txManager(@Qualifier("dataSourceProxy")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @SuppressWarnings("deprecation")
    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSourceProxy")DataSource dataSource) throws IOException {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        
        //mybtis-plus分页拆件
        //PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        ////修复分页单页限制500条问题
        //paginationInterceptor.setLimit(-1);
        //攻击SQL阻断解析器,加入解析链
        List<ISqlParser> sqlList = new ArrayList<>();
        //sqlList.add(new BlockAttackSqlParser());
        //paginationInterceptor.setSqlParserList(sqlList);
        //mybatisSqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor,new OptimisticLockerInterceptor()});
        
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mappers/*.xml"));
        
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        //globalConfig.setMetaObjectHandler(new BaseMetaObjectHandler());
//        globalConfig.setMetaObjectHandler(new CreateAndUpdateHandler());
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return mybatisSqlSessionFactoryBean;
    }
}