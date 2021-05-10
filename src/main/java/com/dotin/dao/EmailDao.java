package com.dotin.dao;

import com.dotin.model.Email;
import com.dotin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class EmailDao {
    ///saveEmail

    public void saveEmail(Email email) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(email);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Email getEmail(Long id) {

        Transaction transaction = null;
        Email email = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            email = session.get(Email.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return email;
    }

    public List<Email> getEmailByReceiver(Long id) {
        Transaction transaction = null;
        List<Email> emailList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            NativeQuery q = session.createNativeQuery("select * from Email e where e.receiver= :id ;", Email.class);
            q.setParameter("id", id);
            emailList = q.getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return emailList;


    }

    public List<Email> getEmailBySender(Long id) {
        Transaction transaction = null;
        List<Email> emailList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            NativeQuery q = session.createNativeQuery("select * from Email e where e.sender= :id ;", Email.class);
            q.setParameter("id", id);
            emailList = q.getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return emailList;


    }
   /* public Email getEmployeeEmail(String email){
        Transaction transaction = null;
        List<Email> emailList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = "from Email where description like :keyword";

            String keyword = "New";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + keyword + "%");

            List<Product> listProducts = query.list();

            emailList = session.createQuery("from Email e where e.disabled =false OR e.disabled=null  ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfEmployee;
    }*/
}
