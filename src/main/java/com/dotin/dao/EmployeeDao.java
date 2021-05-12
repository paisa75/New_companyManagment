package com.dotin.dao;

import com.dotin.model.Employee;
import com.dotin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {

    ///saveEmployee

    public void saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(employee);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    ///updateEmployee

    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(employee);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    ///deleteEmployee
    public void deleteEmployee(Employee employee) {
        employee.setDisabled(true);
        updateEmployee(employee);
    }

    public void deleteEmployee(Long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a employee object
            Employee employee = session.get(Employee.class, id);
            transaction.commit();
            if (employee != null) {
                //session.delete(employee);
                employee.setDisabled(true);
                updateEmployee(employee);
                System.out.println("employee is deleted");
            }

            // commit transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    ///getEmployee
    public Employee getEmployee(Long id) {

        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            employee = session.get(Employee.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (employee.getDisabled() != null && employee.getDisabled())
            return null;
        return employee;
    }


    public List<Employee> getManagerEmployees(Long id) {
        Transaction transaction = null;
        List<Employee> employees = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            // NativeQuery q = session.createNativeQuery("SELECT * FROM mytable where username = ?username");
            NativeQuery q = session.createNativeQuery("select * from Employee e where e.manager_id= :id and (e.disabled =false OR e.disabled IS Null);", Employee.class);
            q.setParameter("id", id);
            employees = q.getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return employees;


    }

    public Employee getEmployeeByEmail(String email) {
        /*Transaction transaction = null;*/
        List<Employee> employees = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            /* transaction = session.beginTransaction();*/
            String hql = "from Employee e where e.email like :keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + email + "%");

            employees = query.list();
            /*NativeQuery q = session.createNativeQuery("select * from Employee e where e.manager_id= :id and e.disabled =false OR e.disabled IS Null;", Employee.class);
            q.setParameter("id", id);
            employees = q.getResultList();*/
            // commit transaction
            /*  transaction.commit();*/
        } catch (Exception e) {
            /*if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
        }

        return employees.get(0);


    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployee() {

        Transaction transaction = null;
        List<Employee> listOfEmployee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            listOfEmployee = session.createQuery("from Employee e where e.disabled =false OR e.disabled=null  ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfEmployee;
    }

    ///getmanager
    public List<Employee> getAllmanager() {

        Transaction transaction = null;
        List<Employee> manager = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            manager = session.createNativeQuery("select * from employee e where role=1 and (e.disabled is NULL Or e.disabled=false)", Employee.class).list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return manager;
    }

    ///getemployeeManager
    public List<Employee> getAllEmployeeManager() {

        Transaction transaction = null;
        List<Employee> EmployeeManager = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            EmployeeManager = session.createNativeQuery("select * from employee where manager_id=1 ", Employee.class).list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return EmployeeManager;
    }
}
