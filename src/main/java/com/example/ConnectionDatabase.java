package com.example;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDatabase {
    private static final Logger loggER = Logger.getLogger(ConnectionDatabase.class.getName());
    private static ConnectionDatabase connect;
    private Connection connectData;
    private static boolean status = true;

    public Connection getConnectData() {
        return connectData;
    }

    public void setConnectData(Connection connectData) {
        connectData = connectData;
    }

    private ConnectionDatabase() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            connectData = ods.getConnection();
        }catch (Exception e) {
            loggER.log(Level.WARNING, "Exception");
        }
    }

    public static ConnectionDatabase getInstance() {
        if(connect == null) {
            connect = new ConnectionDatabase();
        }
        return connect;
    }

    public boolean getConnection() {
        return status;
    }
}
