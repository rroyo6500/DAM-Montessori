package com.rroyo.ficherosxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class LeerFicherosXML {

    private static String path = "acceso_a_datos/unidad1/FicherosXML/src/com/rroyo/ficherosxml/";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(path + "productos.xml");

        NodeList productos = document.getElementsByTagName("producto");
        for (int i = 0; i < productos.getLength(); i++) {
            Node producto = productos.item(i);
            Element element = (Element) producto;
            System.out.println(element.getElementsByTagName("nombre").item(0)
                    .getChildNodes().item(0)
                    .getNodeValue());
            System.out.println(element.getElementsByTagName("precio").item(0)
                    .getChildNodes().item(0)
                    .getNodeValue());
        }
    }

}
