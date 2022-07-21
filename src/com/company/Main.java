package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {             //parser pliku xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("currencies.xml");
        NodeList currencylist = doc.getElementsByTagName("Cube");
        HashMap<String, Double> dane = new HashMap<String, Double>();
        for(int i=2;i<currencylist.getLength();i++){
            Node c = currencylist.item(i);
            if(c.getNodeType()==Node.ELEMENT_NODE){
                Element currency = (Element) c;
                String currencyName = currency.getAttribute("currency");
                String currencyValue = currency.getAttribute("rate");
                double currencyValueD = Double.parseDouble(currencyValue);
                dane.put(currencyName, currencyValueD);
            }
        }

        Calc object = new Calc();                                                                                       //stworzenie obiektu klasy Calc i wywołanie menu
        object.menu(dane);
    }
}
