package com.clouway.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      try {
        SaxEmployeesParser handler = new SaxEmployeesParser();
        parser.parse("src/com/clouway/xml/Emlpoyee.xml", handler);
       List result= handler.getList();
        System.out.println();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }
}
