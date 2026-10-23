package com.rroyo.pr1_tiendajuegosmesayrol.util;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Importer {

    public static GameStore getData(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        GameStore gameStore = new GameStore();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList nodeList = document.getElementsByTagName("game_store");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String gameName = "", gameDescription = "";
                GameTypes gameType;
                try {
                    gameName = element.getElementsByTagName("game_name")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    gameDescription = element.getElementsByTagName("game_description")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    gameType = GameTypes.valueOf(element.getElementsByTagName("game_type")
                            .item(0).getChildNodes().item(0).getNodeValue());
                } catch (Exception ignored) {
                    System.err.println("Error al leer la lista de Juegos. (" + gameName + ", " + gameDescription + ")");
                    continue;
                }

                gameStore.addGame(
                        new Game(gameName, gameDescription, gameType)
                );
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        return gameStore;
    }

}
