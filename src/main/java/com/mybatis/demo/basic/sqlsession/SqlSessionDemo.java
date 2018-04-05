package com.mybatis.demo.basic.sqlsession;

import com.mybatis.demo.basic.model.BlogMapper;
import com.mybatis.demo.domain.BlogDO;
import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: heartaway
 * @create: 2018-04-02 下午11:33
 */
public class SqlSessionDemo {

    @Test
    public void testGetSqlSession() {
        SqlSession session = SqlSessionFactoryWapper.getInstance().openSession();
        try {
            BlogDO blogDO = session.selectOne("com.mybatis.demo.basic.model.BlogMapper.selectBlogFromXml", 1);
            Assert.assertNotNull(blogDO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    /**
     * 优势:
     * 1.不是基于字符串常量;
     * 2.IDE中的方法自动提示;
     * 学习：
     * 配置接口化
     */
    @Test
    public void testGetSqlSession2() {
        try (SqlSession session = SqlSessionFactoryWapper.getInstance().openSession();) {
            BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            BlogDO blogDO = blogMapper.selectBlogFromAnnotaion(1L);
            Assert.assertNotNull(blogDO);
        }
    }
}
