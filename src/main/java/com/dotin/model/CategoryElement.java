package com.dotin.model;

import com.dotin.model.enums.CategoryElementType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorianElement")
public class CategoryElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "code")
    private String code;

    @Column(name = "dType")
    private CategoryElementType dType;

    @OneToMany(mappedBy = "role")
    private List<Employee> employees = new ArrayList<Employee>();

//    @OneToMany(mappedBy = "state")
//    private List<Vacation> vacations = new ArrayList<Vacation>();

    public CategoryElement() {
    }

    public CategoryElement(String value, String code, CategoryElementType dType) {
        this.value = value;
        this.code = code;
        this.dType = dType;
    }

    public CategoryElement(Long id, String value, String code, CategoryElementType dType) {
        this.id = id;
        this.value = value;
        this.code = code;
        this.dType = dType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return code;
    }

    public void setName(String name) {
        this.code = name;
    }

    public CategoryElementType getdType() {
        return dType;
    }

    public void setdType(CategoryElementType dType) {
        this.dType = dType;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

/*    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }*/
}
