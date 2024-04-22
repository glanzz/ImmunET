package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.service.DoctorService;

/**
 * Represents a doctor in the system, extending the {@code User} class. This class includes additional
 * information specific to doctors, such as clinic address and service cost, and interacts with the {@code DoctorService}
 * for persistence.
 */
@Component
public class Doctor extends User {

    @Autowired
    DoctorService doctorService;

    private Integer id = null;
    private String clinicAddress;
    private Double serviceCost = 0.0;

    /**
     * Default constructor for creating a new Doctor instance with default values.
     */
    public Doctor() {
        super();
    }

    /**
     * Constructor for creating a new Doctor instance with specified details and service cost.
     *
     * @param name the name of the doctor
     * @param address the address of the clinic, which is also used as the billing address
     * @param username the username for the doctor's account
     * @param password the password for the doctor's account
     * @param serviceCost the cost of services provided by the doctor
     */
    public Doctor(String name, String address, String username, String password, Double serviceCost) {
        super(name, address, username, password);
        setClinicAddress(address);
        setServiceCost(serviceCost);
    }

    /**
     * Constructor for creating a new Doctor instance without specifying service cost.
     *
     * @param name the name of the doctor
     * @param address the address of the clinic
     * @param username the username for the doctor's account
     * @param password the password for the doctor's account
     */
    public Doctor(String name, String address, String username, String password) {
        super(name, address, username, password);
    }

    /**
     * Gets the doctor's unique identifier.
     *
     * @return the identifier of the doctor
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the clinic address of the doctor.
     *
     * @return the clinic address
     */
    public String getClinicAddress() {
        return clinicAddress;
    }

    /**
     * Sets the clinic address of the doctor. This also updates the user's billing address.
     *
     * @param clinicAddress the new clinic address
     */
    public void setClinicAddress(String clinicAddress) {
        this.setAddress(clinicAddress);
        this.setBillingAddress(clinicAddress);
        this.clinicAddress = clinicAddress;
    }

    /**
     * Gets the service cost associated with the doctor.
     *
     * @return the service cost
     */
    public Double getServiceCost() {
        return serviceCost;
    }

    /**
     * Sets the service cost for the doctor. If the service cost is less than 1, it does not raise an error but should be handled.
     *
     * @param serviceCost the new service cost
     */
    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    /**
     * Checks if the doctor's record is persisted in the database.
     *
     * @return true if the doctor is persisted, otherwise false
     */
    private boolean isPersisted() {
        return this.id != null;
    }

    /**
     * Saves the doctor's data to the database using {@code DoctorService}. If the doctor is already persisted, it returns without saving.
     */
    public void save() {
        if (isPersisted()) {
            return;
        }
        this.id = doctorService.save(this);
    }

    /**
     * Loads the doctor's data from a {@code DoctorEntity} instance. This method populates the doctor's fields with data from the database.
     *
     * @param doctor the entity containing the doctor's data
     */
    public void load(DoctorEntity doctor) {
        super.load(doctor.getUserDetails());
        this.id = doctor.getId();
        setClinicAddress(doctor.getClinicAddress());
        setServiceCost(doctor.getServiceCost());
    }
}
