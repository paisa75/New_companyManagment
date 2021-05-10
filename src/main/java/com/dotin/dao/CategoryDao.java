package com.dotin.dao;

import com.dotin.model.CategoryElement;
import com.dotin.model.Employee;
import com.dotin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryDao {

    public List<CategoryElement> getAllRoles() {

        Transaction transaction = null;
        List<CategoryElement> roles = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            roles = session.createNativeQuery("select * from categorianelement where dType=1", CategoryElement.class).list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return roles;
    }



    public List<CategoryElement> getAllVacationStates() {

        Transaction transaction = null;
        List<CategoryElement> states = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            states = session.createNativeQuery("select * from categorianelement where dType=2", CategoryElement.class).list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return states;
    }



    public CategoryElement getCategoryElement(Long id) {

        Transaction transaction = null;
        CategoryElement categoryElement = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an CategoryElement object
            categoryElement = session.get(CategoryElement.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return categoryElement;
    }

    public void saveCategoryElement(CategoryElement categoryElement) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(categoryElement);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
