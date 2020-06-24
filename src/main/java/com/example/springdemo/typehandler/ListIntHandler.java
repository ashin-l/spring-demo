package com.example.springdemo.typehandler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Integer[].class)
@MappedJdbcTypes(JdbcType.ARRAY)
public class ListIntHandler extends BaseTypeHandler<Integer[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Integer[] parameter, JdbcType jdbcType)
            throws SQLException {
        // TODO Auto-generated method stub
        Connection conn = ps.getConnection();
        Array array = conn.createArrayOf("integer", parameter);
        ps.setArray(i, array);
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        return getArray(rs.getArray(columnName));
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return getArray(rs.getArray(columnIndex));
    }

    @Override
    public Integer[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return getArray(cs.getArray(columnIndex));
    }

    private Integer[] getArray(Array array) {
        if (array == null) {
            return null;
        }
        try {
            return (Integer[]) array.getArray();
            // array.getArray();
            // return (Object[]) array.getArray();
        } catch (Exception e) {
        }
        return null;
    }

}