/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db.workers;

import java.security.SecureRandom;
import java.util.Random;
import org.sql2o.Connection;
import py.com.generica.epos.dbpatcher.db.DatabaseClient;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Test extends AbstractDatabaseWorker<Long> {

    public static Random rnd = new SecureRandom();

    @Override
    public Long execute(DatabaseClient client, Object... args) throws Throwable {
        long time = System.nanoTime();
        String sql = String.format("INSERT INTO epos.tablita (B) values(%d) ;", time);
        System.out.println(sql);

        Connection c = client.connect();
        Object key = c.createQuery(sql).executeUpdate().getKey();
        client.close(c);

        boolean abort = rnd.nextBoolean();
        System.out.printf("KEY: %s (%s)\n", key, abort ? "ROLLBACK" : "COMMIT");

        if (abort) {
            throw new RuntimeException("D:");
        }

        return (Long) key;
    }

}
