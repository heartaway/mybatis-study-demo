package com.mybatis.demo.config.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

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
    method = "query",//对应type类的方法名称
    args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class MyExecutorQueryPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        /**
         * 判断一下目标类型，是本插件要拦截的对象才执行Plugin.wrap方法，否者直接返回目标本省，这样可以减少目标被代理的次数。
         */
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
