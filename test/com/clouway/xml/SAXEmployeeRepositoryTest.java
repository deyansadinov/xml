package com.clouway.xml;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class SAXEmployeeRepositoryTest {
  @Test
  public void happyPath() throws Exception{
    SAXParserFactory factory = SAXParserFactory.newInstance();

    SAXParser saxParser = factory.newSAXParser();
    SaxEmployeesParser saxEmployeeParser = new SaxEmployeesParser();
    File f = new File("src/com/clouway/xml/Emlpoyee.xml");
    saxParser.parse(f,saxEmployeeParser);

    List result = saxEmployeeParser.getList();

    assertThat(result.size(),is(1));

    Object object = saxEmployeeParser.getRoot();

    assertThat(object,is(notNullValue()));




  }
}