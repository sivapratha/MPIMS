/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.jsf.classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import mpims.entities.Login;
import mpims.jpa.controller.LoginJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;
import mpims.jsf.classes.util.Util;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class LoginController {

    public LoginController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (LoginJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "loginJpa");
        pagingInfo = new PagingInfo();
        converter = new LoginConverter();
    }
    private Login login = null;
    private List<Login> loginItems = null;
    private LoginJpaController jpaController = null;
    private LoginConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getLoginCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getLoginItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findLoginEntities(), false);
    }

    public SelectItem[] getLoginItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findLoginEntities(), true);
    }

    public Login getLogin() {
        if (login == null) {
            login = (Login) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLogin", converter, null);
        }
        if (login == null) {
            login = new Login();
        }
        return login;
    }

    public String listSetup() {
        reset(true);
        return "login_list";
    }

    public String createSetup() {
        reset(false);
        login = new Login();
        return "login_create";
    }

    public String create() {
        try {
            jpaController.create(login);
            JsfUtil.addSuccessMessage("Login was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("login_detail");
    }

    public String editSetup() {
        return scalarSetup("login_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        login = (Login) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLogin", converter, null);
        if (login == null) {
            String requestLoginString = JsfUtil.getRequestParameter("jsfcrud.currentLogin");
            JsfUtil.addErrorMessage("The login with id " + requestLoginString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String loginString = converter.getAsString(FacesContext.getCurrentInstance(), null, login);
        String currentLoginString = JsfUtil.getRequestParameter("jsfcrud.currentLogin");
        if (loginString == null || loginString.length() == 0 || !loginString.equals(currentLoginString)) {
            String outcome = editSetup();
            if ("login_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit login. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(login);
            JsfUtil.addSuccessMessage("Login was successfully updated.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return listSetup();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String destroy() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentLogin");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Login was successfully deleted.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return relatedOrListOutcome();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Login> getLoginItems() {
        if (loginItems == null) {
            getPagingInfo();
            loginItems = jpaController.findLoginEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return loginItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "login_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "login_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        login = null;
        loginItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Login newLogin = new Login();
        String newLoginString = converter.getAsString(FacesContext.getCurrentInstance(), null, newLogin);
        String loginString = converter.getAsString(FacesContext.getCurrentInstance(), null, login);
        if (!newLoginString.equals(loginString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
 String userName;
    String password;
String errorMsg="";


    public String verifyLogin() {


        HttpSession session=Util.getSession();



        System.out.println("inside verifylogin method");
        List<Login> logins =new ArrayList<Login>();
              logins=jpaController.findAllLoginEntities();
        System.out.println("the size of the logins.size is" + logins.size());
        if (logins.size() > 0) {
            for (int i = 0; i < logins.size(); i++) {
                if (logins.get(i).getUserName().equals(userName)) {
                    if (logins.get(i).getPassword().equals(password)) {
                        session.setAttribute("id",logins.get(i).getId());
                     return "home";
                    } else {
                      errorMsg="You are a invalid user!";
                      return "";
                    }
                }

            }
        } else {
           errorMsg="You are a invalid user!";
            return "";
        }
        return null;
    }

    public String logout(){

        HttpSession session=Util.getSession();
        session.removeAttribute("id");
        if(session!=null)
            session.invalidate();
        return "main";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
