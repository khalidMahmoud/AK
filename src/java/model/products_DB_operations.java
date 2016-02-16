package model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class products_DB_operations {

    private List<product> list;

    public List<product> getList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from product");
        this.list = q.list();
        return list;
    }

}
