package com.mybatis.demo.config.plugins;

import com.mybatis.demo.basic.model.BlogMapper;
import com.mybatis.demo.util.SqlSessionFactoryWapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * plugin 的工作原理：
 * 在openSession的时候，会根据配置新建Executor，然后将配置中注册的Interceptor应用到插件环境中（Configuration@581）
 *
 * <p>
 * 当定义了多个拦截器时，采用动态代理 责任链模式（层层代理）；DefaultSqlSession@148
 * </p>
 *
 * <p>
 * 过程：
 * 1. 所有可能被拦截的处理类都会生成一个代理
 * 2. 处理类代理在执行对应方法时，判断要不要执行插件中的拦截方法
 * 3. 执行插接中的拦截方法后，推进目标的执行
 * </p>
 *
 * @author: heartaway
 * @create: 2018-04-05 下午6:52
 */
public class PluginDemo {

    @Test
    public void testQueryWithPlugins() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryWapper.getInstance("config/mybatis-config.xml");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            System.out.println(blogMapper.selectBlogFromAnnotaion(1L));
        }
    }
}
