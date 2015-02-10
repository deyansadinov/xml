package com.clouway.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SaxEmployeesParser extends DefaultHandler {

  private String temp;
  private Object currentObject;
  private boolean flag = true;
  private Object root = null;
  private Object parentObject = null;
  private List<Object> list = new ArrayList<>();


  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

    System.out.println("<" + qName + ">");

    try {
      currentObject = Class.forName("com.clouway.xml." + capitalizeWord(qName)).newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
    }
    if (root != null && flag) {
      parentObject = currentObject;
      flag = false;
    }

    if (root == null) {
      root = currentObject;
    }


  }


  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    temp = new String(ch, start, length);
    System.out.println("characters: " + temp);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {

    System.out.println("</" + qName + ">");

    if (isItAField(currentObject, qName)) {
      setParameter(currentObject, qName, temp);
    }

    if (qName.equalsIgnoreCase(currentObject.getClass().getSimpleName()) && currentObject != parentObject) {
      if (isItAField(parentObject, currentObject.getClass().getSimpleName())) {
        setParameter(parentObject, currentObject.getClass().getSimpleName(), currentObject);
      }
    }

    if (parentObject.getClass().getSimpleName().equalsIgnoreCase(qName)) {
      flag = true;
      list.add(parentObject);
    }
  }


  private void setParameter(Object object, String qName, Object value) {
    Field[] fields = object.getClass().getDeclaredFields();
    for (Field each : fields) {
      each.setAccessible(true);
      if (each.getName().equalsIgnoreCase(qName)) {
        try {
          {
            each.set(object, value);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        return;
      }
    }
  }

  private boolean isItAField(Object currentObject, String qName) {
    Field[] fields = currentObject.getClass().getDeclaredFields();
    for (Field each : fields) {
      if (each.getName().equalsIgnoreCase(qName)) {
        return true;
      }
    }
    return false;
  }

  private String capitalizeWord(String qName) {
    return qName.substring(0, 1).toUpperCase() + qName.substring(1);
  }

  public List<Object> getList() {
    return list;
  }

  public Object getRoot() {
    return root;
  }
}
