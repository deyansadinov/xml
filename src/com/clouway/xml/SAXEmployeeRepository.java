package com.clouway.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SAXEmployeeRepository<T> extends DefaultHandler {

  private String temp;
  private List<Employee> employees;
  private Employee employee;
  private Object currentObject;

  public SAXEmployeeRepository() {
    employees = new ArrayList<>();
  }

  public List<Employee> employeesList(InputStream xmlStream){
    SAXParserFactory spf = SAXParserFactory.newInstance();
    try {
      SAXParser  sp = spf.newSAXParser();
      sp.parse(xmlStream,this);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return employees;
  }


  @Override
  public void startDocument() throws SAXException {
    System.out.println("begin parsing document...");
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    System.out.println("<" + qName + ">");

    temp = "";

    try {
      Class clazz = Class.forName("com.clouway.xml." + qName);
      currentObject = clazz.newInstance();
      employee = (Employee) currentObject;
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }


  }


  private void setParameterToParent(Object currentObject, Object parent, String qName) {
    Field[] parentField = parent.getClass().getDeclaredFields();
    for (Field each : parentField) {
      each.setAccessible(true);
      if (each.getName().equalsIgnoreCase(qName)) {
        try {
          each.set(currentObject, temp);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private Object createNewInstance(String clazzName) {
    try {
      Class<?> clazz = Class.forName("com.clouway.xml." + clazzName);
      return clazz.newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//      e.printStackTrace();
    }
    return null;
  }


  private String capitalizeWord(String tag) {
    return tag.substring(0, 1).toUpperCase() + tag.substring(1);
  }


  @Override
  public void characters(char[] ch, int start, int length) {
    temp = new String(ch, start, length);

    System.out.println("characters: " + temp);


  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    System.out.println("</" + qName + ">");

    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    try {
      Field field = Class.forName(currentObject.getClass().getName()).getDeclaredField(qName);
      field.setAccessible(true);
      if (field.getType().equals(String.class)){
        field.set(currentObject,temp);
      }else if (field.getType().equals(Date.class)){
        field.set(currentObject,date.parse(temp));
      }else {
        field.set(currentObject,Integer.parseInt(temp));
      }
    } catch (ClassNotFoundException | IllegalAccessException | ParseException e) {
      e.printStackTrace();
    }catch (NoSuchFieldException e){
      try {
        Method method = Class.forName(employee.getClass().getName()).getDeclaredMethod("set" + qName,
                currentObject.getClass());
        method.invoke(employee,currentObject);

      } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException e1) {
        e1.printStackTrace();
      } catch (NoSuchMethodException e1) {
        employees.add(employee);
      }
    }
  }

  @Override
  public void endDocument() throws SAXException {
    System.out.println("\nend parsing document... ");
  }
}
