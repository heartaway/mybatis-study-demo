package com.mybatis.demo.cache;

import com.mybatis.demo.basic.model.BlogMapper;
import com.mybatis.demo.domain.BlogDO;
import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 一级缓存，默认开启，且无法关闭，我们可以通过localCacheScope设置缓存的作用域
 * PerpetualCache （HashMap）实现
 *
 * BaseExecutor@152
 *
 * localCacheScope=STATEMENT 时，每次执行完一次语句是都会清理缓存，BaseExecutor@167
 * <setting name="localCacheScope" value="STATEMENT"/>
 *
 * MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
 * 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。 若设置值为
 * STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。
 *
 * @author: heartaway
 * @create: 2018-04-05 下午11:14
 */
public class FirstStageCacheDemo {

    @Test
    public void testQueryByCacheScopeSession() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            BlogDO blogDO1 = blogMapper.selectBlogFromAnnotaion(1L);
            BlogDO blogDO2 = blogMapper.selectBlogFromAnnotaion(1L);
            System.out.println(blogDO1);
            System.out.println(blogDO2);
            Assert.assertTrue(blogDO1.equals(blogDO2));
        }
    }

    @Test
    public void testQueryByCacheScopeStatement() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("cache/mybatis-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            BlogDO blogDO1 = blogMapper.selectBlogFromAnnotaion(1L);
            BlogDO blogDO2 = blogMapper.selectBlogFromAnnotaion(1L);
            System.out.println(blogDO1);
            System.out.println(blogDO2);
            Assert.assertTrue(!blogDO1.equals(blogDO2));
        }
    }
}
