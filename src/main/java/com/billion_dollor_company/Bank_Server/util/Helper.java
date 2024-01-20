package com.billion_dollor_company.Bank_Server.util;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String encodedStr) {
        return Base64.getDecoder().decode(encodedStr);
    }

    public static String jsonToXml(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return XML.toString(jsonObject);
    }

    public static String extractFromXML(String xmlCode, String tagToExtract) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCode)));

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(tagToExtract);
            if (nodeList.getLength() > 0) {
                Element nameElement = (Element) nodeList.item(0);
                return nameElement.getTextContent();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> xmlToMap(String xmlCode, String[] toExtractArr) {
        HashMap<String, String> map = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlCode)));

            doc.getDocumentElement().normalize();
            for (String toExtract : toExtractArr) {
                NodeList nodeList = doc.getElementsByTagName(toExtract);
                if (nodeList.getLength() > 0) {
                    Element nameElement = (Element) nodeList.item(0);
                    map.put(toExtract, nameElement.getTextContent());
                } else {
                    map.put(toExtract, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static String mapToXML(Map<String, String> map, String rootName) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a new Document
            Document doc = builder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("request");
            doc.appendChild(rootElement);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                Element element = doc.createElement(key);
                element.appendChild(doc.createTextNode(value));
                rootElement.appendChild(element);
            }

            // Convert Document to XML String
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new java.io.StringWriter());
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getUserPassword(String userID) {
        return "123456";
    }

    public static String getBalance(String userID) {
        return "9999";
    }
}
