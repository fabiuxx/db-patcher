/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db.clients;

import java.io.IOException;
import org.sql2o.Connection;
import py.com.generica.epos.dbpatcher.db.DatabaseClient;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Client_MySQL extends DatabaseClient {

    public Client_MySQL(String database, String host, String port, String user, String pass) throws Throwable {
        super(database, host, port, user, pass);
    }

    @Override
    protected String getJdbcUrl() {
        String url = String.format("jdbc:mariadb://%s:%s/%s", host, port, database);
        return url;
    }

    @Override
    public boolean tableExists(String schema, String table) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT count(*) ");
        builder.append("FROM information_schema.tables ");
        builder.append("WHERE 1 = 1 ");
        builder.append("and table_schema = '").append(database).append("' ");
        builder.append("and table_name = '").append(table).append("' ");
        builder.append("LIMIT 1");
        builder.append(";");

        Connection conn = null;
        try {
            conn = connect();
            String query = builder.toString();
            Integer count = conn.createQuery(query).executeScalar(Integer.class);
            return (count != null && count > 0);
        } catch (IOException ex) {
            return false;
        } finally {
            close(conn);
        }
    }

}
