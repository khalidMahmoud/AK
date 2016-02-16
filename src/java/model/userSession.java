package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class userSession {
    private int userId;

    public int getUserId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession sesion=request.getSession(false);
        userId=(int) sesion.getAttribute("userId");
        setUserId(userId);
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
