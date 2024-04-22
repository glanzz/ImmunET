package com.immunet.immunet.model;

import java.util.Date;

/**
 * Represents a generic person with basic properties such as name and address.
 * This class serves as a base class from which other specific person types can inherit.
 */
public class Person {
    private String name;
    private String address;

    /**
     * Constructor for creating a Person instance with specified name and address.
     *
     * @param name the name of the person
     * @param address the address of the person
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Default constructor for creating a Person instance without initial properties.
     */
    public Person() {
    }

    /**
     * Retrieves the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the person.
     *
     * @return the address of the person
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the person.
     *
     * @param address the new address of the person
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
