/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class Connector {

    private static Logger logger = Logger.getLogger(Connector.class);

    /**
     *
     * @param config
     * @return Connection
     */
    public static Connection getConnectionPostgresql(GeneratorConfig config) {

        Connection con = null;

        try {

            DriverManager.registerDriver(new org.postgresql.Driver());

            con = DriverManager.getConnection(config.getPostgresqlUrl(), config.getPostgresqlUser(), config.getPostgresqlPass());

            logger.info("Connected to: " + config.getPostgresqlUrl());

        } catch (Exception e) {

            logger.error("Error connecting to: " + config.getPostgresqlUrl(), e);
        }

        return con;
    }

    /**
     *
     * @param config
     * @return
     */
    public static Connection getConnectionMysql(GeneratorConfig config) {

        Connection con = null;

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            con = DriverManager.getConnection(config.getMysqlUrl(), config.getMysqlUser(), config.getMysqlPass());

            logger.info("Connected to: " + config.getMysqlUrl());

        } catch (Exception e) {

            logger.error("Error connecting to: " + config.getMysqlUrl(), e);
        }

        return con;
    }

    public static Connection establishConnection(GeneratorConfig config) {

        Connection con = null;

        if (config.getActiveConnection().equals(XConnEnum.Postgresql)) {

            con = getConnectionPostgresql(config);

        } else if (config.getActiveConnection().equals(XConnEnum.Mysql)) {

            con = getConnectionMysql(config);
        }

        return con;
    }

//    public static void main(String[] args) {
//
//        Connection con = establishConnection(new GeneratorConfig());
//    }
}
