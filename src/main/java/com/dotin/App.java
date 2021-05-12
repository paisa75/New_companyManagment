package com.dotin;

import com.dotin.dao.CategoryDao;
import com.dotin.dao.EmployeeDao;
import com.dotin.model.CategoryElement;
import com.dotin.model.Employee;
import com.dotin.model.enums.CategoryElementType;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        List<Employee> employees = employeeDao.getAllEmployee();
        employees.forEach(e -> System.out.println(e.getName()));

        CategoryDao categoryDao = new CategoryDao();
        CategoryElement categoryElement = new CategoryElement("مدیر", "manager", CategoryElementType.ROLE);
        CategoryElement categoryElement1 = new CategoryElement("برنامه نویس", "developer", CategoryElementType.ROLE);
        CategoryElement categoryElement2 = new CategoryElement("تستر", "tester", CategoryElementType.ROLE);
        CategoryElement categoryElement3 = new CategoryElement("تایید مرخصی", "Vacation confirmation", CategoryElementType.CONFIRMATION);
        CategoryElement categoryElement4 = new CategoryElement("رد کردن مرخصی", "Reject vacation", CategoryElementType.CONFIRMATION);
        CategoryElement categoryElement5 = new CategoryElement("لغو  مرخصی", "Cancellation of vacation", CategoryElementType.CONFIRMATION);
        categoryDao.saveCategoryElement(categoryElement);
        categoryDao.saveCategoryElement(categoryElement1);
        categoryDao.saveCategoryElement(categoryElement2);
        categoryDao.saveCategoryElement(categoryElement3);
        categoryDao.saveCategoryElement(categoryElement4);
        categoryDao.saveCategoryElement(categoryElement5);

    }
}
