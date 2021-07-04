package cch.com.example.demo.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import cch.com.example.demo.request.VO.UserRequestVO;

public class UserProvider {
    private final String TABLE = "public.user";

    public final String addUser(UserRequestVO user) {
        return new SQL().INSERT_INTO(TABLE)
                .VALUES("name", "#{name}")
                .VALUES("age", "#{age}")
                .VALUES("email", "#{email}")
                .VALUES("tel", "#{tel}")
                .toString() + " RETURNING * ";
    }


    public final String getById(@Param("id") String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * " +
                "FROM public.user " +
                "WHERE id = #{id}");

        return sql.toString();
    }

    public final String isExistUser(@Param("name") String name) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
        .append("EXISTS ( SELECT 1 FROM ")
        .append(TABLE)
        .append(" WHERE name = #{name} )");

        return sql.toString();
    }

    public final String delete(@Param("id") String id) {
        return new SQL().DELETE_FROM(TABLE).WHERE("id = #{id}").toString();
    }

    public final String update(UserRequestVO user) {
        SQL sql = new SQL()
            .UPDATE(TABLE)
            .SET("name = #{name}")
            .SET("age = #{age}")
            .SET("email = #{email}")
            .SET("tel = #{tel}")
            .WHERE("id = #{id}");
            return sql.toString() + " RETURNING * ";
    }

}
