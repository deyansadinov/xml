package com.clouway.xml;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class SAXEmployeeRepositoryTest {
  @Test
  public void happyPath() throws Exception{
    SAXParserFactory factory = SAXParserFactory.newInstance();
//    factory.setValidating(true);
    SAXParser saxParser = factory.newSAXParser();
    SAXEmployeeRepository saxEmployeeRepository = new SAXEmployeeRepository();
    File f = new File("src/com/clouway/xml/Emlpoyee.xml");
    saxParser.parse(f,saxEmployeeRepository);

//    Object object = saxEmployeeRepository.getRoot();

//    assertThat(object,is(notNullValue()));


  }
}