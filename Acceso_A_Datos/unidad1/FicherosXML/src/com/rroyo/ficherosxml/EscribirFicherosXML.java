package com.rroyo.ficherosxml;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EscribirFicherosXML {

    private static String path = "acceso_a_datos/unidad1/FicherosXML/src/com/rroyo/ficherosxml/";

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        List<Producto> listaProductos = new ArrayList<>();

        Producto p1 = new Producto("Producto1", 1);
        Producto p2 = new Producto("Producto2", 2);
        Producto p3 = new Producto("Producto3", 3);

        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;

        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        document = dom.createDocument(null, "xml", null);

        Element raiz = document.createElement("Productos");
        document.getDocumentElement().appendChild(raiz);

        Element nodoProducto;
        Element nodoDatos;
        Text texto;

        for (Producto producto : listaProductos) {
            nodoProducto = document.createElement("Producto");
            raiz.appendChild(nodoProducto);

            nodoDatos = document.createElement("nombre");
            nodoProducto.appendChild(nodoDatos);

            texto = document.createTextNode(producto.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("precio");
            nodoProducto.appendChild(nodoDatos);

            texto = document.createTextNode(String.valueOf(producto.getPrecio()));
            nodoDatos.appendChild(texto);
        }

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(path + "ficheroXML.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(source, result);

    }

}
