package com.clouway.xml;

import com.clouway.joro.Main;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SAXDemo {
  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

//    try {
//      XMLReader p = XMLReaderFactory.createXMLReader();
//      p.setContentHandler(new SAXEmployeeRepository());
//      try {
//        p.parse("src/com/clouway/xml/Emlpoyee.xml");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    } catch (SAXException e) {
//      e.printStackTrace();
//    }

//    SAXParserFactory spf = SAXParserFactory.newInstance();
//    spf.setNamespaceAware(true);
//    try {
//      SAXParser saxParser = spf.newSAXParser();
//      SAXEmployeeRepository saxEmployeeRepository = new SAXEmployeeRepository();
//      saxParser.parse(new File("src/com/clouway/xml/Emlpoyee.xml"),saxEmployeeRepository);
//    } catch (ParserConfigurationException | IOException | SAXException e) {
//      e.printStackTrace();
//    }

//    File file = new File("/home/clouway/workspace/incubator/xml/src/com/clouway/xml/Emlpoyee.xml");
//    SAXParserFactory factory = SAXParserFactory.newInstance();
//    SAXParser saxParser = factory.newSAXParser();
//    SAXEmployeeRepository<Employee> employeeSAXEmployeeRepository = new SAXEmployeeRepository<>();
//    saxParser.parse(file,employeeSAXEmployeeRepository);

    SAXEmployeeRepository saxEmployeeRepository = new SAXEmployeeRepository();
    List<Employee> employees = null;

    employees = saxEmployeeRepository.employeesList(Main.class.getResourceAsStream("Emlpoyee"));

    if (employees != null){
      for (Employee employee : employees){
        System.out.println(employee);
      }
    }
  }
}
