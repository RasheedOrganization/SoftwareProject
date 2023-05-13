package com.example;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDatabase {
    private static final Logger LOGGER = Logger.getLogger(ConnectionDatabase.class.getName());
    private static ConnectionDatabase Connect;
    private Connection ConnectData;
    private static boolean status = true;

    public Connection getConnectData() {
        return ConnectData;
    }

    public void setConnectData(Connection connectData) {
        ConnectData = connectData;
    }

    private ConnectionDatabase() {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("mohammad");
            ods.setPassword("123456");
            ConnectData = ods.getConnection();
        }catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception");
        }
    }

    public static ConnectionDatabase getInstance() {
        if(Connect == null) {
            Connect = new ConnectionDatabase();
        }
        return Connect;
    }

    public boolean getConnection() {
        return status;
    }
}
