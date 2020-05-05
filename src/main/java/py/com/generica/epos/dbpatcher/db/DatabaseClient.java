/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 *
 * @author Fabio A. González Sosa
 */
public abstract class DatabaseClient {

    protected final String database;
    protected final String host;
    protected final String port;
    protected final String user;
    protected final String pass;

    private final HikariConfig poolConfig;
    private final HikariDataSource poolDataSource;
    protected final Sql2o sql2o;

    public DatabaseClient(String database, String host, String port, String user, String pass) throws Throwable {
        this.database = database;
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;

        this.poolConfig = new HikariConfig();
        this.poolConfig.setPoolName("db-pool");
        this.poolConfig.setJdbcUrl(getJdbcUrl());
        this.poolConfig.setUsername(user);
        this.poolConfig.setPassword(pass);
        this.poolConfig.addDataSourceProperty("cachePrepStmts", "true");
        this.poolConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        this.poolConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.poolDataSource = new HikariDataSource(poolConfig);

        this.sql2o = new Sql2o(poolDataSource);
    }

    protected abstract String getJdbcUrl();

    public Connection connect() throws IOException {
        try {
            Connection conn = sql2o.open();
            return conn;
        } catch (Throwable thr) {
            throw new IOException("No se pudo obtener una conexión a la base de datos indicada", thr);
        }
    }

    public void close(Connection connection) {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection beginTransaction() {
        return sql2o.beginTransaction();
    }

    public void commit(Connection connection) {
        connection.commit(false);
    }

    public void rollback(Connection connection) {
        connection.rollback(false);
    }

    public boolean tableExists(String table) {
        return tableExists(null, table);
    }

    public abstract boolean tableExists(String schema, String table);

}
