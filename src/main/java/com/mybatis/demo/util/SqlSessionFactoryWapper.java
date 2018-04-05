package com.mybatis.demo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSessionFactory 的作用域：应用级别，建议采用单例模式构建
 * <p>
 * mybatis-spring 采用SqlSessionFactoryBean 单例bean构建 单例SqlSessionFactory
 * </p>
 *
 * @author: heartaway
 * @create: 2018-04-05 上午11:10
 */
public class SqlSessionFactoryWapper {

    private static SqlSessionFactory INSTANCE;

    public static SqlSessionFactory getInstance() {
        return getInstance(null);
    }

    public static SqlSessionFactory getInstance(String resourceLocation) {
        if (INSTANCE == null) {
            synchronized (SqlSessionFactoryWapper.class) {
                if (INSTANCE == null) {
                    INSTANCE = init(resourceLocation);
                }
            }
        }

        return INSTANCE;
    }

    private static org.apache.ibatis.session.SqlSessionFactory init(String resourceLocation) {
        try {
            String resource = resourceLocation == null ? "basic/mybatis-config.xml" : resourceLocation;
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            return null;
        }
    }
}
