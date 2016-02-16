/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
    
/**
 *
 * @author khalid
 */
@ManagedBean
@SessionScoped
public class billBean {
    private String message="done";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public billBean() {
    }
    
    
    
}
