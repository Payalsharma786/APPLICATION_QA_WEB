package com.globallogic.test.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class configuration {

    private static configuration config = null;
    private static List<ConfigurationDetails> configurationDeatilsList;

    public static configuration getInstance() {
        if (config == null) {
            config = new configuration();
        }
        return config;
    }

    public static List<ConfigurationDetails> getConfigurationDetailsList() {
        return configurationDeatilsList;
    }

    public static void getXMLFile() {
        String filePath = "src\\main\\java\\com\\globallogic\\test\\config\\configurationDetails.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("ConfigurationDetails");

            configurationDeatilsList = new ArrayList<ConfigurationDetails>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                configurationDeatilsList.add(getConfigurationDetails(nodeList.item(i)));
            }
            for (ConfigurationDetails configurationDetailsObject : configurationDeatilsList) {
                System.out.println(configurationDetailsObject.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

    }


    private static ConfigurationDetails getConfigurationDetails(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        ConfigurationDetails configurationDetailsObject = new ConfigurationDetails();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            configurationDetailsObject.setBrowser(getTagValue("browser", element));
        }

        return configurationDetailsObject;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}



