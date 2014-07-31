package ru.nemiroff;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class XMLFirmConfig implements FirmConfig {

    private Document document = XMLFirmConfigParser.getDocument();

    private ThingCollection materialCollection = new ThingCollection();

    private ThingCollection productCollection = new ThingCollection();

    public XMLFirmConfig() {
        try {
            fillMaterials();
            fillProducts();
        } catch (XPathExpressionException e) {
            System.err.println("Error creating firm configuration. Program will be terminated");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void fillProducts() throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xPath.evaluate("/firm-config/products/product",
                document.getDocumentElement(), XPathConstants.NODESET);
        List<Thing> result = new ArrayList<Thing>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Product product = new Product();
            NamedNodeMap attributes = node.getAttributes();
            product.setName(attributes.getNamedItem("name").getNodeValue());
            Map<Thing, Double> proportions = new HashMap<Thing, Double>();
            NodeList proportionNodes = (NodeList)xPath.evaluate("proportions/proportion",
                    node, XPathConstants.NODESET);
            for (int j = 0; j < proportionNodes.getLength(); j++) {
                Node proportion = proportionNodes.item(j);
                NamedNodeMap proportionAttributes = proportion.getAttributes();
                Thing material = materialCollection.getThingByName(
                        proportionAttributes.getNamedItem("material").getNodeValue());
                proportions.put(material,
                        Double.parseDouble(proportionAttributes.getNamedItem("quantity").getNodeValue()));
            }
            product.setProportions(proportions);
            result.add(product);
        }
        productCollection.setThings(result);
    }

    private void fillMaterials() throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xPath.evaluate("/firm-config/materials/material",
                document.getDocumentElement(), XPathConstants.NODESET);
        List<Thing> materials = new ArrayList<Thing>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Material material = new Material();
            NamedNodeMap attributes = node.getAttributes();
            material.setName(attributes.getNamedItem("name").getNodeValue());
            material.setPrice(Double.valueOf(attributes.getNamedItem("price").getNodeValue()));
            materials.add(material);
        }
        materialCollection.setThings(materials);
    }

    public ThingCollection getMaterialCollection() {
        return materialCollection;
    }

    public ThingCollection getProductCollection() {
        return productCollection;
    }

}
