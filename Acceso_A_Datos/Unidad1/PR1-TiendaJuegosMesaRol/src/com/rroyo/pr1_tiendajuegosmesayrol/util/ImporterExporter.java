package com.rroyo.pr1_tiendajuegosmesayrol.util;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ImporterExporter {

    public static GameStore importData(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        GameStore gameStore = new GameStore();

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

        return gameStore;
    }

    public static void exportData(File file, GameStore gameStore) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();

        Document document = dom.createDocument(null, "xml", null);

        Element root = document.createElement("GameStore");
        document.getDocumentElement().appendChild(root);

        Element gameNode;
        Element gameData;
        Text text;

        for (Game game : gameStore.getGameList()) {
            gameNode = document.createElement("Game");
            root.appendChild(gameNode);

            gameData = document.createElement("Name");
            gameNode.appendChild(gameData);
            text = document.createTextNode(game.getGameName());
            gameData.appendChild(text);

            gameData = document.createElement("Description");
            gameNode.appendChild(gameData);
            text = document.createTextNode(game.getDescription());
            gameData.appendChild(text);

            gameData = document.createElement("GameType");
            gameNode.appendChild(gameData);
            text = document.createTextNode(game.getGameType().toString());
            gameData.appendChild(text);
        }

        Source source = new DOMSource(document);
        Result result = new StreamResult(file);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

}
