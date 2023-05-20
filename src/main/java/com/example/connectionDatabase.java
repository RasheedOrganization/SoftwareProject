package com.example;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectionDatabase {
    private static final Logger loggER = Logger.getLogger(connectionDatabase.class.getName());
    private static connectionDatabase connect;
    private Connection connectData;
    private static final boolean STATUS = true;

    public Connection getConnectData() {
        return connectData;
    }

    public void setConnectData(Connection connectData) {
        this.connectData = connectData;
    }

    private connectionDatabase() {
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

    public static connectionDatabase getInstance() {
        if(connect == null) {
            connect = new connectionDatabase();
        }
        return connect;
    }

    public boolean getConnection() {
        return STATUS;
    }
}
