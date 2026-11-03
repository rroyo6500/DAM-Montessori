package com.rroyo.pr1_tiendajuegosmesayrol;

import com.rroyo.pr1_tiendajuegosmesayrol.app.controller.GameStoreController;
import com.rroyo.pr1_tiendajuegosmesayrol.app.gui.Window;
import com.rroyo.pr1_tiendajuegosmesayrol.app.model.GameStoreModel;

public class Main {

    public static void main(String[] args) {
        GameStoreModel gameStoreModel = new GameStoreModel();
        Window window = new Window();
        GameStoreController controller = new GameStoreController(gameStoreModel, window);

    }

}
