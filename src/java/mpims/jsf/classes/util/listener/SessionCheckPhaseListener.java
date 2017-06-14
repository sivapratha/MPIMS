/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpims.jsf.classes.util.listener;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shakeelstha
 */
public class SessionCheckPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("inside before phase");
        FacesContext facesCtx = event.getFacesContext();
        ExternalContext extCtx = facesCtx.getExternalContext();

        HttpSession session = (HttpSession) extCtx.getSession(false);
        HttpServletRequest request = (HttpServletRequest) extCtx.getRequest();
        HttpServletResponse httpServletResponse = (HttpServletResponse) extCtx.getResponse();

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        //String mainPage = contextPath + "/";
//        String loginPage = contextPath + "/" + "faces" + "/pages/" + "login.jsp";
        String loginPage=contextPath+"/MPIMS/faces/pages/login.jsp";
        System.out.println("login page"+loginPage);
        boolean mainLoginPage = (requestURI.equalsIgnoreCase(loginPage));
        System.out.println(""+mainLoginPage);
        boolean isJspPage = requestURI.endsWith("jsp");


        

        if (mainLoginPage) {
     
        } else {
            // System.out.println("..inside else block");
            try {
                System.out.println("session"+session.getAttribute("id"));
                if (session.getAttribute("id") == null) {
                    // System.out.println("..inside session check if block");
                    if (isJspPage) {
                        //System.out.println("..inside isJspPage if block");
                        gotoLoginPage(httpServletResponse);
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                gotoLoginPage(httpServletResponse);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public void gotoLoginPage(HttpServletResponse httpServletResponse) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext context = null;

        try {
           System.out.println("Inside goto login page try.");
            context = (ServletContext) fc.getExternalContext().getContext();
            String redirectTo = context.getContextPath()+"/";
            System.out.println("redirect to: "+redirectTo);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            httpServletResponse.sendRedirect(redirectTo);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
