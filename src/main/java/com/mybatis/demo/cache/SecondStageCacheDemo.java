package com.mybatis.demo.cache;

import com.mybatis.demo.basic.model.BlogMapper;
import com.mybatis.demo.domain.BlogDO;
import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * 对象：MappedStatement.cache  ， 作用域：Mapper(namespace)
 * 参见：BlogMapper.xml sqlmap中的<cache />标签 ,
 * 默认cache为 org.apache.ibatis.cache.decorators.LruCache 包装的PerpetualCache，动态代理模式
 *
 * 可自定义外部缓存实现
 *
 * CachingExecutor
 *
 * setting 中cacheEnabled 全局控制，默认为true
 *
 * @author: heartaway
 * @create: 2018-04-06 上午12:01
 */
public class SecondStageCacheDemo {

    @Test
    public void testQueryByAnnotationSql() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("cache/mybatis-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            BlogDO blogDO1 = blogMapper.selectBlogFromAnnotaion(1L);
            System.out.println(blogDO1);
        }
    }

    @Test
    public void testQueryByXmlSql() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("cache/mybatis-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            //BlogDO必须实现Serializable接口
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
