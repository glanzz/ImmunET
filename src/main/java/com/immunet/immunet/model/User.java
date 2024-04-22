package com.immunet.immunet.model;

import com.immunet.immunet.entity.UserEntity;

/**
 * Represents a user in the system, extending the Person class with additional user-specific properties
 * such as username, password, and billing address. This class is used to manage user account details.
 */
public class User extends Person {
    private Integer id;
    private String username;
    private String password;
    private String billingAddress;

    /**
     * Constructor to create a User instance with specified details.
     *
     * @param name the user's full name
     * @param address the user's primary address
     * @param username the user's account username
     * @param password the user's account password
     */
    public User(String name, String address, String username, String password) {
        super(name, address);
        this.username = username;
        this.password = password;
        this.billingAddress = address;
    }

    /**
     * Default constructor for creating a User instance without initial details.
     */
    public User() {
        super();
    }

    /**
     * Retrieves the unique identifier for the user.
     *
     * @return the unique identifier of the user
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Retrieves the user's billing address.
     *
     * @return the billing address of the user
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the user's billing address.
     *
     * @param billingAddress the new billing address for the user
     */
    public void setBillingAddress(String billingAddress) {
        setAddress(billingAddress);
        this.billingAddress = billingAddress;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     *
     * @param username the new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Compares two passwords for equality.
     *
     * @param password1 the first password to compare
     * @param password2 the second password to compare
     * @return true if the passwords are the same, false otherwise
     */
    public static boolean comparePassword(String password1, String password2) {
        return password1.equals(password2);
    }

    /**
     * Loads user data from a UserEntity into this User instance.
     *
     * @param user the UserEntity containing the user data
     */
    public void load(UserEntity user) {
        this.id = user.getId();
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setUsername(user.getContact());
        this.setBillingAddress(user.getBillingAddress());
    }
}
