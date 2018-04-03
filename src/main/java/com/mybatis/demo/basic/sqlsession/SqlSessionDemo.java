package com.mybatis.demo.basic.sqlsession;

import java.io.IOException;
import java.io.InputStream;

import com.mybatis.demo.basic.model.BlogDO;
import com.mybatis.demo.basic.model.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: heartaway
 * @create: 2018-04-02 下午11:33
 */
public class SqlSessionDemo {

    private static SqlSessionFactory INSTANCE;

    private Object lock = new Object();

    public SqlSessionFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = init();
                }
            }
        }

        return INSTANCE;
    }

    private SqlSessionFactory init() {
        try {
            String resource = "basic/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            return null;
        }
    }

    @Test
    public void testGetSqlSession() {
        SqlSession session = getInstance().openSession();
        try {
            BlogDO blogDO = session.selectOne("BlogMapper.selectBlogById", 1);
            Assert.assertNotNull(blogDO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    /**
     * 需要手动注册 BlogMapper 到 mapperRegistry 中
     */
    @Test
    public void testGetSqlSession2() {
        getInstance().getConfiguration().addMapper(BlogMapper.class);
        SqlSession session = getInstance().openSession();
        try {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            BlogDO blogDO = blogMapper.selectBlog(1L);
            Assert.assertNotNull(blogDO);
        } finally {
            session.close();
        }
    }
}
