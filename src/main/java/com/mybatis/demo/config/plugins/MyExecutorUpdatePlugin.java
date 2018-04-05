package com.mybatis.demo.config.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * type 的类型可为：Executor、ParameterHandler、ResultSetHandler、StatementHandler
 *
 * 一般不建议使用自定义插件
 *
 * @author: heartaway
 * @create: 2018-04-05 下午6:55
 */
@Intercepts({@Signature(
    type = Executor.class,
    method = "update",//对应type类的方法名称
    args = {MappedStatement.class, Object.class}
)})
public class MyExecutorUpdatePlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
