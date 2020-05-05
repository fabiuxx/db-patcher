/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db.workers;

import org.sql2o.Connection;
import py.com.generica.epos.dbpatcher.db.DatabaseClient;

/**
 *
 * @author Fabio A. González Sosa
 * @param <T>
 */
public abstract class AbstractDatabaseWorker<T> implements DatabaseWorker<T> {

    @Override
    public T transaction(DatabaseClient client, Object... args) throws Throwable {
        Connection con = client.beginTransaction();
        try {
            T result = execute(client, args);
            client.commit(con);
            return result;
        } catch (Throwable thr) {
            client.rollback(con);
            throw thr;
        } finally {
            client.close(con);
        }
    }

}
