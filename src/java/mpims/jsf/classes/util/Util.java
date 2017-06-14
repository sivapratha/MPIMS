package mpims.jsf.classes.util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Util {

    public Util() {
    }

    public static HttpSession getSession() {
        ExternalContext extCon = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) extCon.getSession(true);
        return session;
    }

    public static FacesContext getFacesContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

    public static Object getSessionObject(String managedBeanName) {
        Object obj = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + managedBeanName + "}").getValue(FacesContext.getCurrentInstance());
        //Object object = (Object)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(managedBeanName);
        return obj;
    }

    public static Map getParameterMap() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        return requestMap;
    }

     public static HttpServletRequest getHttpServeletRequest() {
        ExternalContext extCon = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletRequest) extCon.getRequest();
    }

    /**
     *
     * @return
     */
    public static HttpServletResponse getHttpServeletResponse() {
        ExternalContext extCon = FacesContext.getCurrentInstance().getExternalContext();
        return (HttpServletResponse) extCon.getResponse();
    }
}
