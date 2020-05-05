/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db;

import fa.gs.result.simple.Result;
import fa.gs.result.simple.Results;
import java.io.IOException;
import java.sql.SQLException;
import py.com.generica.epos.dbpatcher.db.clients.Client_MySQL;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Clients {

    public static Result<DatabaseClient> mysql(String host, String port, String user, String password) throws IOException {
        Result<DatabaseClient> result;

        try {
            DatabaseClient client = new Client_MySQL(Databases.DB_EPOS, host, port, user, password);
            result = OK(client);
        } catch (Throwable thr) {
            int errno = -1;
            if (thr instanceof SQLException) {
                errno = ((SQLException) thr).getErrorCode();
            }
            result = KO(thr, errno);
        }

        return result;
    }

    private static Result<DatabaseClient> OK(DatabaseClient client) {
        return Results.ok()
                .value(client)
                .build();
    }

    private static Result<DatabaseClient> KO(Throwable cause, int errno) {
        return Results.ko()
                .message("Ocurrió un error obteniendo cliente para base de datos")
                .cause(cause)
                .errno(errno)
                .build();
    }

}
