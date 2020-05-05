/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.gui.utils;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Widgets {

    public static void text(final JLabel label, String fmt, Object... args) {
        final String msg = String.format(fmt, args);
        Async.run(new Async.Runner<Void>() {
            @Override
            public Void run() throws Throwable {
                label.setText(msg);
                return null;
            }
        });
    }

    public static void progress(final JProgressBar bar, final int value) {
        Async.run(new Async.Runner<Void>() {
            @Override
            public Void run() throws Throwable {
                bar.setValue(value);
                return null;
            }
        });
    }

}
