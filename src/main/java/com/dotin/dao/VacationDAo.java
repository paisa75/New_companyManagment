package com.dotin.dao;

import com.dotin.model.Vacation;
import com.dotin.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VacationDAo {

    ///saveVacation

    public void saveVacation(Vacation vacation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(vacation);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Vacation> getPersonVacations(Long id) {

        Transaction transaction = null;
        List<Vacation> vacation = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            //session.createQuery("from Vacation ")
            //vacation = session.createNativeQuery("select * from vacation v where v.person = 1;", Vacation.class).getResultList();
            vacation = session.createNativeQuery("select * from vacation v where v.person = :myId", Vacation.class).setParameter("myId", id).getResultList();
//            q.setParameter("id", id);
//            vacation = q.getResultList();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return vacation;
    }

    ///getEmployee
    public Vacation getVacation(Long id) {

        Transaction transaction = null;
        Vacation vacation = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an employee object
            vacation = session.get(Vacation.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return vacation;
    }


    ///updateEmployee

    public void updateVacation(Vacation vacation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(vacation);
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
