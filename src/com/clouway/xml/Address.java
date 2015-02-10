package com.clouway.xml;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Address {

  private String street;
  private String streetNo;
  private String section;
  private String city;

  public Address() {

  }

  public String getStreet() {
    return street;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public String getSection() {
    return section;
  }

  public String getCity() {
    return city;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
