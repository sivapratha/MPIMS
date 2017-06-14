/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.jsf.classes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import mpims.entities.Login;
import mpims.jpa.controller.LoginJpaController;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class LoginConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        LoginJpaController controller = (LoginJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "loginJpa");
        return controller.findLogin(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Login) {
            Login o = (Login) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: mpims.entities.Login");
        }
    }

}
