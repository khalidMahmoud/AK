package model;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;

@ManagedBean
@SessionScoped
@Entity
public class cart implements Serializable{

    private int id;
    private int userId;
    private String productName;
    private String productPrice;

    public cart() {
    }

    @Id
    @TableGenerator(name = "g", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //insert product to cart
    public void insertCart(int userId, String productName, String productPrice) {
        cart_DB_operations db = new cart_DB_operations();
        setUserId(userId);
        setProductName(productName);
        setProductPrice(productPrice);
        db.insert(this);
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/bill");
    }
    //remove method
}
