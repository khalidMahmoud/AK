package model;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;
@ManagedBean
@SessionScoped
@Entity
public class bill implements Serializable{
    private int id;
    private int userId;
    private String visa;
    private String address;
@Id
@TableGenerator(name = "g",allocationSize = 1)
@GeneratedValue(strategy = GenerationType.TABLE,generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void insertBill(int userId,String email){
        bill_DB_operations db=new bill_DB_operations();
        setUserId(userId);
//        db.sendConfirmationEmail(getEmail(userId));
        db.sendConfirmationEmail(email);
        db.insert(this);
        
    }
    
    public String getEmail(int userId){
        String result;
        bill_DB_operations db=new bill_DB_operations();
        result=db.getUserEmail(userId);
        return result;
    }
    
}
