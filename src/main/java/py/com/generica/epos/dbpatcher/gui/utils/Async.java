/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.gui.utils;

import fa.gs.result.simple.DeferredResult;
import fa.gs.result.simple.Results;
import fa.gs.result.utils.Failure;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Async {

    public static <T> DeferredResult<T> run(boolean async, final Runner<T> runner) {
        final DeferredResult<T> deferred = Results.deferred();

        if (async) {
            runInAsyncWorker(deferred, runner);
        } else {
            runInSyncWorker(deferred, runner);
        }

        return deferred;
    }

    public static <T> DeferredResult<T> run(final Runner<T> runner) {
        return run(true, runner);
    }

    private static <T> void runInAsyncWorker(final DeferredResult<T> deferred, final Runner<T> runner) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Async.execute(deferred, runner);
                return null;
            }
        };
        worker.execute();
    }

    private static <T> void runInSyncWorker(final DeferredResult<T> deferred, final Runner<T> runner) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Async.execute(deferred, runner);
            }
        };

        if (SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(runnable);
        } else {
            runnable.run();
        }
    }

    private static <T> void execute(final DeferredResult<T> deferred, final Runner<T> runner) {
        try {
            T obj = runner.run();
            deferred.resolve(obj);
        } catch (Throwable thr) {
            Failure failure = Failure.builder().message("Ocurrió un error durante ejecución").cause(thr).build();
            deferred.reject(failure);
        }
    }

    public interface Runner<T> {

        T run() throws Throwable;

    }

}
