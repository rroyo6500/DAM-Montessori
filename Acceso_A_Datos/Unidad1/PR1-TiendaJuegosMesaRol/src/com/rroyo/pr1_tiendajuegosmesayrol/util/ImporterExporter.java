package com.rroyo.pr1_tiendajuegosmesayrol.util;

import com.google.gson.Gson;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.LogLevel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;

public class ImporterExporter {

    public static GameStore importData(File file) throws ParserConfigurationException, IOException, SAXException {
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);

        GameStore gameStore = null;
        switch (fileExtension) {
            case "xml": {
                gameStore = importXML(file);
            } break;
            case "json": {
                gameStore = importJSON(file);
            } break;
        }
        return gameStore;
    }

    public static GameStore importXML(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        GameStore gameStore = new GameStore();

        builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList nodeList = document.getElementsByTagName("Game");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String gameName = "", gameDescription = "";
            GameTypes gameType;
            LocalDate gameDate;
            double price;
            try {
                gameName = element.getElementsByTagName("Name")
                        .item(0).getChildNodes().item(0).getNodeValue();
                gameDescription = element.getElementsByTagName("Description")
                        .item(0).getChildNodes().item(0).getNodeValue();
                gameType = GameTypes.valueOf(element.getElementsByTagName("GameType")
                        .item(0).getChildNodes().item(0).getNodeValue());
                gameDate = LocalDate.parse(element.getElementsByTagName("GameDate")
                        .item(0).getChildNodes().item(0).getNodeValue());
                price = Double.parseDouble(element.getElementsByTagName("GamePrice")
                        .item(0).getChildNodes().item(0).getNodeValue());
            } catch (Exception ignored) {
                Util.log("Error al leer la lista de Juegos. (" + gameName + ", " + gameDescription + ")", LogLevel.WARNING);
                continue;
            }

            gameStore.addGame(
                    new Game(gameName, gameDescription, gameType, gameDate, price)
            );
        }

        return gameStore;
    }

    public static GameStore importJSON(File file) throws IOException {
        Gson gson = new Gson();

        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            json.append(line);
        }
        reader.close();

        return gson.fromJson(json.toString(), GameStore.class);
    }

    public static void exportData(File file, GameStore gameStore) throws ParserConfigurationException, TransformerException, IOException {
        String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);

        switch (fileExtension) {
            case "xml": {
                exportXML(file, gameStore);
            } break;
            case "json": {
                exportJSON(file, gameStore);
            } break;
        }
    }

    public static void exportXML(File file, GameStore gameStore) throws TransformerException, ParserConfigurationException {
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

            gameData = document.createElement("GameDate");
            gameNode.appendChild(gameData);
            text = document.createTextNode(game.getGameDate().toString());
            gameData.appendChild(text);

            gameData = document.createElement("GamePrice");
            gameNode.appendChild(gameData);
            text = document.createTextNode(String.valueOf(game.getPrice()));
            gameData.appendChild(text);
        }

        Source source = new DOMSource(document);
        Result result = new StreamResult(file);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

    public static void exportJSON(File file, GameStore gameStore) throws IOException {
        Gson gson = new Gson();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        String json = gson.toJson(gameStore);
        bw.write(json);
        bw.close();
    }

}
