package com.clouway.joro;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class XMLHandler extends DefaultHandler {

  private List<Employee> employees;
  private Employee employee;
  private boolean readingFirstName = false;
  private boolean readingLastName = false;
//  private MyElementHandler currentHandler ;
//
//  private Map<String,MyElementHandler> handlers = new HashMap<String,MyElementHandler>(){{
//    this.put("employee",new EmployeeHandler);
//  }};


  @Override
  public void startDocument() throws SAXException {
//    employees = new ArrayList<>();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//    currentHandler = handlers.get(qName);
//    handler.element(qName);
  }

  @Override
  public void characters(char[] chars, int start, int length) throws SAXException {
    StringBuilder buffer = new StringBuilder();

    buffer.append(chars, start, length);

    if (readingFirstName) {
      employee.setFirstName(buffer.toString());
    }

    if (readingLastName) {
      employee.setLastName(buffer.toString());
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
//    handler.data()
    if (qName.equals("employee")) {
      employees.add(employee);
      employee = null;
    }

    if (qName.equals("firstName")) {
      readingFirstName = false;
    }
    if (qName.equals("lastName")) {
      readingLastName = false;
    }
  }

  @Override
  public void endDocument() throws SAXException {
    System.out.println(employees);
  }

}
