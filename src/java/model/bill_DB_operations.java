package model;

import static com.oracle.jrockit.jfr.ContentType.Address;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.hibernate.*;

public class bill_DB_operations {

    String message;

    public void insert(bill b) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(b);
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

    public void deleteBill(int userId) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("delete from cart where userId=?");
            q.setParameter(0, userId);
            q.executeUpdate();
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

    public String getUserEmail(int userId) {
        String result = "";
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            Query q=session.createQuery("select email from User where id=?");
            q.setParameter(0, userId);
            result=q.list().toString();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    
    public void sendConfirmationEmail(String clientEmail){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        javax.mail.Session session = javax.mail.Session.getInstance(props,
                 new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication("khalid.mahmoud.basuiny@gmail.com", "01003123911");
                     }
                 }
         );

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("khalid.mahmoud.basuiny@gmail.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(clientEmail));
            message.setSubject("AK.com");
            message.setText(" thank you for using AK.com ... we will call you when your order is delivered.");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
}
