/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.gui.utils;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Launch {

    public static void setNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable thr) {
            ;
        }
    }

    public static void launch(JFrame frame) {
        launch(null, frame);
    }

    public static void launch(JFrame parent, JFrame child) {
        // Cerrar padre.
        if (parent != null) {
            parent.setVisible(false);
            parent.dispose();
        }

        // Desplegar hijo.
        child.pack();
        child.setLocationRelativeTo(null);
        child.setVisible(true);
    }

}
