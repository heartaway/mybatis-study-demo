package com.mybatis.demo.cache;

import com.mybatis.demo.basic.model.BlogMapper;
import com.mybatis.demo.domain.BlogDO;
import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * 关闭二级缓存后，executor为 BaseExecutor 中的查询方法
 *
 * @author: heartaway
 * @create: 2018-04-06 上午12:01
 */
public class CloseSecondCacheDemo {

    @Test
    public void testQueryByXmlSql() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("cache/mybatis-nocache-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            BlogDO blogDO1 = blogMapper.selectBlogFromXml(1L);
            System.out.println(blogDO1);
        }

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            BlogDO blogDO1 = blogMapper.selectBlogFromXml(1L);
            System.out.println(blogDO1);
        }
    }
}
