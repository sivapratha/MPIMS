/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.jsf.classes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import mpims.entities.Locality;
import mpims.jpa.controller.LocalityJpaController;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class LocalityConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        LocalityJpaController controller = (LocalityJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "localityJpa");
        return controller.findLocality(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Locality) {
            Locality o = (Locality) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: mpims.entities.Locality");
        }
    }

}
