
package model;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class productsListBean {
    private product pro=new product();
    products_DB_operations p=new products_DB_operations();  
    private List<product> productsList;
    
    public productsListBean() {
    }
    
    public List get(){
        productsList=p.getList();
        return this.productsList;
    }
    
}
