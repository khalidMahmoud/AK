package model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.*;
import model.NewHibernateUtil;
import org.hibernate.*;

public class user_DB_operations {

    public void insert(User u) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(u);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List selectUser(String userName, String password) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        List<User> users = new ArrayList<User>();
        try {
            transaction = session.beginTransaction();
            Query q=session.createQuery("from User where email=? and password=?");
            q.setParameter(0, userName);
            q.setParameter(1, password);
            users=q.list();
            

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return users;
    }
}
