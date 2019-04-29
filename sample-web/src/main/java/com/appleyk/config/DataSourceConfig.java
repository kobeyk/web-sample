package com.appleyk.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
/**
 * <p>数据源配置 -- 支持多数据源配置，本项目中只配置一个主数据源</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 10:32 2019-4-27
 */
@EnableTransactionManagement
@MapperScan(basePackages = "com.appleyk.dao.mapper", sqlSessionTemplateRef  = "sqlSessionTemplate")
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "masterDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //需要导入配置
    public HikariDataSource dataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("masterDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
