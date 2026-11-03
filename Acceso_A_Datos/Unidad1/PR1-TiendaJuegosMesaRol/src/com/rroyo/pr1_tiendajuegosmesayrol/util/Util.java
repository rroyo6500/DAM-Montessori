package com.rroyo.pr1_tiendajuegosmesayrol.util;

import com.rroyo.pr1_tiendajuegosmesayrol.enums.LogLevel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {

    public static JFileChooser createFileChooser(File defaultRoute, String fileType, String... fileExtension) {
        JFileChooser chooser = new JFileChooser();
        if (defaultRoute != null) {
            chooser.setCurrentDirectory(defaultRoute);
        }
        if (fileExtension != null) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(fileType, fileExtension);
            chooser.setFileFilter(filter);
        }
        return chooser;
    }

    public static int confirmMessage(String message, String title) {
        return JOptionPane.showConfirmDialog(null,message,
                title,JOptionPane.YES_NO_OPTION);
    }

    public static void log(String message, LogLevel logLevel) {
        System.err.println("[" + logLevel.toString() + "] " + message);
        JOptionPane.showMessageDialog(null, message, logLevel.toString(), JOptionPane.ERROR_MESSAGE);
    }
}
