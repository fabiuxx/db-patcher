/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.gui;

import javax.swing.JFrame;
import py.com.generica.epos.dbpatcher.gui.utils.Launch;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Main {

    public static void main(String[] args) {
        Launch.setNativeLookAndFeel();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Connection();
                Launch.launch(frame);
            }
        });
    }

}
