package com.hcmus.chatserver.entities.user;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEachMapper implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
        final User user = new User();
        if (rs.isBeforeFirst()) {
            rs.next();
            user.setId(rs.getInt("userid"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("user_password"));
            user.setName(rs.getString("fullname"));
            user.setEmail(rs.getString("email"));
            user.setSex(rs.getString("sex"));
            user.setAddress(rs.getString("user_address"));
            user.setBirthday(rs.getLong("birthday"));
            user.setCreatedTime(rs.getLong("createdtime"));
            user.setOnline(rs.getBoolean("isonline"));
            user.setBlocked(rs.getBoolean("isblocked"));
        }
        return user;
    }
}
