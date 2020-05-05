/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.generica.epos.dbpatcher.gui.utils;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Fabio A. González Sosa
 */
public class Alert {

    private static DialogResult alert(Level kind, String title, String fmt, Object... args) {
        return optionPane(kind, Type.ALERT, title, fmt, args);
    }

    private static DialogResult confirm(Level kind, String title, String fmt, Object... args) {
        return optionPane(kind, Type.CONFIRM, title, fmt, args);
    }

    private static DialogResult optionPane(Level level, Type type, String title, String fmt, Object... args) {
        JOptionPane optionPane = new JOptionPane(String.format(fmt, args), level.value(), type.value());
        JDialog dialog = optionPane.createDialog(title);
        dialog.setModal(true);
        dialog.setAlwaysOnTop(true);
        dialog.pack();
        dialog.setVisible(true);

        dialog.dispose();
        return adaptDialogValue(optionPane.getValue());
    }

    private static DialogResult adaptDialogValue(Object value) {
        if (value instanceof Integer) {
            int option = (Integer) value;
            if (option == JOptionPane.YES_OPTION || option == JOptionPane.OK_OPTION) {
                return DialogResult.POSITIVE;
            } else if (option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                return DialogResult.NEGATIVE;
            } else {
                return DialogResult.UNDEFINED;
            }
        } else {
            return DialogResult.UNDEFINED;
        }
    }

    public static DialogResult trace(String fmt, Object... args) {
        return alert(Level.TRACE, "", fmt, args);
    }

    public static DialogResult info(String fmt, Object... args) {
        return alert(Level.INFO, "Información", fmt, args);
    }

    public static DialogResult warning(String fmt, Object... args) {
        return alert(Level.WARN, "Advertencia", fmt, args);
    }

    public static DialogResult error(String fmt, Object... args) {
        return alert(Level.ERROR, "Error", fmt, args);
    }

    public static DialogResult traceConfirm(String fmt, Object... args) {
        return confirm(Level.TRACE, "", fmt, args);
    }

    public static DialogResult infoConfirm(String fmt, Object... args) {
        return confirm(Level.INFO, "Información", fmt, args);
    }

    public static DialogResult warningConfirm(String fmt, Object... args) {
        return confirm(Level.WARN, "Advertencia", fmt, args);
    }

    public static DialogResult errorConfirm(String fmt, Object... args) {
        return confirm(Level.ERROR, "Error", fmt, args);
    }

    private enum Level {
        TRACE(JOptionPane.PLAIN_MESSAGE),
        INFO(JOptionPane.INFORMATION_MESSAGE),
        WARN(JOptionPane.WARNING_MESSAGE),
        ERROR(JOptionPane.ERROR_MESSAGE);
        private final int value;

        Level(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    private enum Type {
        ALERT(JOptionPane.DEFAULT_OPTION),
        CONFIRM(JOptionPane.YES_NO_OPTION);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum DialogResult {
        UNDEFINED,
        POSITIVE,
        NEGATIVE
    }

}
