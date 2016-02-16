/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class logoutBean {

    /**
     * Creates a new instance of logoutBean
     */
    public logoutBean() {
    }
    
    
    public void logout(int userId){
        bill_DB_operations db=new bill_DB_operations();
        db.deleteBill(userId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userId");
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index");
        
    }
    
}
