package com.mybatis.demo.basic.model;

import com.mybatis.demo.domain.BlogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: heartaway
 * @create: 2018-04-02 下午11:27
 */
@Mapper
public interface BlogMapper {

    @Select("select * from mybatis_demo_blog where id = #{id}")
    BlogDO selectBlogFromAnnotaion(Long id);

    BlogDO selectBlogFromXml(Long id);
}
