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
public class DocumentToEntitiesMapper {

    private Document document = FirmConfigParser.getDocument();

    private ThingsManager materialsManager = new ThingsManager();

    private ThingsManager productsManager = new ThingsManager();

    private static DocumentToEntitiesMapper instance = null;

    public static DocumentToEntitiesMapper getInstance() {
        if(instance == null) {
            instance = new DocumentToEntitiesMapper();
        }
        return instance;
    }

    private DocumentToEntitiesMapper() {
        try {
            fillMaterials();
            fillProducts();
        } catch (XPathExpressionException e) {
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
            Node e = nodes.item(i);
            Product product = new Product();
            NamedNodeMap attributes = e.getAttributes();
            product.setName(attributes.getNamedItem("name").getNodeValue());
            Map<Thing, Float> proportions = new HashMap<Thing, Float>();
            NodeList proportionNodes = (NodeList)xPath.evaluate("proportions/proportion",
                    e, XPathConstants.NODESET);
            for (int j = 0; j < proportionNodes.getLength(); j++) {
                Node proportion = proportionNodes.item(j);
                NamedNodeMap proportionAttributes = proportion.getAttributes();
                Thing material = materialsManager.getThingByName(
                        proportionAttributes.getNamedItem("material").getNodeValue());
                proportions.put(material,
                        Float.parseFloat(proportionAttributes.getNamedItem("quantity").getNodeValue()));
            }
            product.setProportions(proportions);
            result.add(product);
        }
        productsManager.setThings(result);
    }

    private void fillMaterials() throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList)xPath.evaluate("/firm-config/materials/material",
                document.getDocumentElement(), XPathConstants.NODESET);
        List<Thing> materials = new ArrayList<Thing>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node e = nodes.item(i);
            Material material = new Material();
            NamedNodeMap attributes = e.getAttributes();
            material.setName(attributes.getNamedItem("name").getNodeValue());
            material.setPrice(Float.valueOf(attributes.getNamedItem("price").getNodeValue()));
            materials.add(material);
        }
        materialsManager.setThings(materials);
    }

    public ThingsManager getMaterialsManager() {
        return materialsManager;
    }

    public ThingsManager getProductsManager() {
        return productsManager;
    }

}
