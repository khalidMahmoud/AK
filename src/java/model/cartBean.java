
package model;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class cartBean {
    private cart c=new cart();
    cart_DB_operations cDB=new cart_DB_operations();
    private List<cart> productsList;
    
    public cartBean() {
    }
    
    public List get(int userId) {
       productsList=cDB.getList(userId);
       return this.productsList;
    }
    
}
