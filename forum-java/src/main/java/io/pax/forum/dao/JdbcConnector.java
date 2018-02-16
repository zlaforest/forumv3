package io.pax.forum.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zolie on 15/02/2018.
 */

public class JdbcConnector {

    DataSource dataSource = this.createDataSource();


    DataSource createDataSource() {
        DataSource dataSource;

        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/forum");

        } catch (NamingException e) {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("");
            mysqlDataSource.setServerName("localhost");
            mysqlDataSource.setDatabaseName("forum");
            mysqlDataSource.setPort(3306);
            dataSource = mysqlDataSource;
        }


        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
