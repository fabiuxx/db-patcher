/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.db.workers;

import py.com.generica.epos.dbpatcher.db.DatabaseClient;

/**
 *
 * @author Fabio A. González Sosa
 * @param <T>
 * @param <C>
 */
public interface DatabaseWorker<T> {

    /**
     * Metodo principal que ejecuta una operacion en la base de datos.
     *
     * @param client Cliente para base de datos.
     * @param args Datos de entrada.
     * @return Datos de salida.
     * @throws Exception Si ocurre algun error durante la ejecucion.
     */
    public abstract T execute(DatabaseClient client, Object... args) throws Throwable;

    /**
     * Permite encapsular la operacion definida por
     * {@link py.com.generica.epos.dbpatcher.db.workers.DatabaseWorker#execute(py.com.generica.epos.dbpatcher.db.workers.DatabaseWorker.Context, java.lang.Object...) execute}
     * dentro de una transaccion de base de datos.
     *
     * @param client Cliente para base de datos.
     * @param args Datos de entrada.
     * @return Datos de salida.
     * @throws Exception Si ocurre algun error durante la ejecucion.
     */
    public abstract T transaction(DatabaseClient client, Object... args) throws Throwable;

}
