/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 *
 * @author julianr
 */
public final class SQLScanner {

    private static final Logger logger = Logger.getLogger(SQLScanner.class);
    private String driver;
    private String url;
    private String user;
    private String pass;
    private DataSource dataSource;
    private Connection conn;

    public SQLScanner() {
    }

    public SQLScanner(String driver, String url, String user, String pass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.dataSource = setupDataSource();
    }

    private void loadDriver() {
        try {
            Class myDbDriver = Class.forName(driver);
            DriverManager.registerDriver((Driver) myDbDriver.newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            logger.error("Error loading the driver: " + driver, e);
        }
    }

    private DataSource setupDataSource() {
        //Loading driver
        loadDriver();
        //
        // First, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
        ConnectionFactory connectionFactory
                = new DriverManagerConnectionFactory(url, user, pass);

        //
        // Next we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory
                = new PoolableConnectionFactory(connectionFactory, null);

        //
        // Now we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //
        ObjectPool<PoolableConnection> connectionPool
                = new GenericObjectPool<>(poolableConnectionFactory);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        //
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        PoolingDataSource<PoolableConnection> dataSource1
                = new PoolingDataSource<>(connectionPool);

        return dataSource1;
    }

    public Connection getConnection() {

        if (conn == null) {

            try {

                conn = dataSource.getConnection();
                logger.info("Connected to: " + url);
            } catch (Exception e) {
                logger.error("Error connecting to: " + url, e);
            }
        }

        return conn;
    }

    public List<SQLField> getSQLFields(String tableName) {
        List<SQLField> fields = new ArrayList<SQLField>(0);
        PreparedStatement psta = null;
        try {
            logger.info("Processing table: " + tableName);

            psta = getConnection().prepareStatement("select top 1 * from " + tableName);
            ResultSet rset = psta.executeQuery();
            ResultSetMetaData metadata = rset.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                SQLField field = new SQLField(metadata.getColumnName(i), metadata.getColumnType(i));
                fields.add(field);
            }

            rset.close();
            psta.close();
            

        } catch (Exception e) {
            logger.error("Error getting fields for table: " + tableName, e);
        }

        return fields;
    }

    public void closeConnection() {

        try {
            conn.close();
        } catch (Exception e) {
            logger.error("Error closing connection to: " + url, e);
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
