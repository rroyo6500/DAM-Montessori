package com.rroyo.pr1_tiendajuegosmesayrol.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {

    public static JFileChooser createFileChooser(File defaultRoute, String fileType, String fileExtension) {
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

}
