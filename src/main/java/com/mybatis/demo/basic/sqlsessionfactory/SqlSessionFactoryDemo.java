package com.mybatis.demo.basic.sqlsessionfactory;

import java.io.InputStream;

import javax.sql.DataSource;

import com.mybatis.demo.basic.model.BlogDataSourceFactory;
import com.mybatis.demo.basic.model.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author: heartaway
 * @create: 2018-04-02 下午11:11
 */
public class SqlSessionFactoryDemo {

    /**
     * 记住：XMLConfigBuilder
     *
     * @throws Exception
     */
    @Test
    public void testLoadByXml() throws Exception {
        String resource = "basic/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(inputStream);
        Assert.notNull(sqlSessionFactory);
    }

    /**
     * 使用API方式加载Mapper，默认还是与寻找与类名称相同的xml配置文件
     */
    @Test
    public void testLoadWithJavaAPI() {
        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
        TransactionFactory transactionFactory =
            new JdbcTransactionFactory();
        Environment environment =
            new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(configuration);
    }

}
