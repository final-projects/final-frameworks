package org.ifinalframework.xml;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author iimik
 * @version 1.2.4
 **/
class DomXmlParserTest {

    @Test
    @SneakyThrows
    void parse() {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document document = builder.parse(getClass().getResourceAsStream("/parse.xml"));
        DomXmlParser parser = new DomXmlParser();

        Element element = parser.parse(document);
        assertNotNull(element);

    }
}