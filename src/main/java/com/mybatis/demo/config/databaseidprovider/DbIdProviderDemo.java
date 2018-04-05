package com.mybatis.demo.config.databaseidprovider;

import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author: heartaway
 * @create: 2018-04-05 下午6:33
 */
public class DbIdProviderDemo {

    /**
     * databaseIdProvider 配置中 type="DB_VENDOR" 表示采用哪一种实现类来支持DatabaseIdProvider
     * DB_VENDOR 为 typeAlias, 在typeAliasRegistry中默认值为 org.apache.ibatis.mapping.VendorDatabaseIdProvider
     * 具体参见：org.apache.ibatis.session.Configuration 188行
     */
    @Test
    public void testQueryByProviderId() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("config/mybatis-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            System.out.println(sqlSession.getConfiguration());
        }
    }
}
