package com.mybatis.demo.sqlbuilder;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

/**
 * 虽然Java代码中嵌套SQL会非常丑陋，且对于DBA Review SQL极度不方便，但是我们可以借鉴这种构建语句的思路，用于其它字符串的拼接上。
 *
 * @author: heartaway
 * @create: 2018-04-05 上午11:36
 */
public class SqlBuilderDemo {

    @Test
    public void testGeneratorSQL() {
        String sql = new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }}.toString();

        System.out.println(sql);
    }
}
