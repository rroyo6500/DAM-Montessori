package com.rroyo.pr1_tiendajuegosmesayrol.util;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;

import javax.swing.*;
import java.io.File;

public class Util {

    // Importer

    public static void importData() {
        JFileChooser fileChooser = new JFileChooser();
        int selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == JFileChooser.APPROVE_OPTION) {
            File importedFile = fileChooser.getSelectedFile();
            Const.window.setData(Importer.getData(importedFile));
        }
    }

}
