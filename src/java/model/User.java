package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "User")
@SessionScoped
@Entity
public class User implements Serializable{

    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @Id
    @TableGenerator(name = "g", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "g")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clear() {
        id = 0;
        name = "";
        email = "";
        phone = "";
        password = "";

    }

    public void insertUser() {
        user_DB_operations db = new user_DB_operations();
        db.insert(this);
        clear();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login");

    }

    public void login() throws IOException {
        user_DB_operations db = new user_DB_operations();
        List<User> ls = db.selectUser(email, password);
        for (User x : ls) {
            id = x.id;
            name = x.name;
            email = x.email;
            password = x.password;
        }
        if (ls.isEmpty()) {
            return;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", id);
            session.setAttribute("userEmail", email);
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/products");
        }
    }

    public void validatePassword(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent component = event.getComponent();

        //get password
        UIInput uiInputPassword = (UIInput) component.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        //get confirm password
        UIInput uiInputConfirmPassword = (UIInput) component.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage message = new FacesMessage("password must match confirm password");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(passwordId, message);
            context.renderResponse();
        }
    }
}
