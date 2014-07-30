package ru.nemiroff;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class FirmConfigParser {

    private final static String FIRM_CONFIG_XML = "config/firm-config.xml";
    private final static String FIRM_CONFIG_XSD = "config/firm-config.xsd";

    public static Document getDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(FIRM_CONFIG_XSD));
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(FIRM_CONFIG_XML);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            return document;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null;
    }

}
