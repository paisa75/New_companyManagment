package com.dotin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Employee")
@Table(name = "employee")
public class Employee extends BaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role")
    private CategoryElement role;

    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<Employee>();

    @OneToMany(mappedBy = "person")
    private List<Vacation> vacations = new ArrayList<Vacation>();

/*    @OneToMany(mappedBy = "sender")
    private List<Email> senders = new ArrayList<Email>();

    @OneToMany(mappedBy = "receiver")
    private List<Email> receivers = new ArrayList<Email>();*/

//    @Column(name = "managerId")
//    private Long managerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CategoryElement getRole() {
        return role;
    }

    public void setRole(CategoryElement role) {
        this.role = role;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

/*    public List<Email> getSenders() {
        return senders;
    }

    public void setSenders(List<Email> senders) {
        this.senders = senders;
    }

    public List<Email> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Email> receivers) {
        this.receivers = receivers;
    }*/
}
