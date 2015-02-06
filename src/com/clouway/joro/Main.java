package com.clouway.joro;

import com.clouway.xml.SAXEmployeeRepository;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Main {
  public static void main(String[] args) {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    try {
      SAXParser saxParser = spf.newSAXParser();
      saxParser.parse(new File("src/com/clouway/xml/Emlpoyee.xml"),new XMLHandler());
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}
