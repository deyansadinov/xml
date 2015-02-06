package com.clouway.joro;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class Employee {

  private String firstName;
  private String lastName;

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
