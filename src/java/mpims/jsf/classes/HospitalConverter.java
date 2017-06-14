/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.jsf.classes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import mpims.entities.Hospital;
import mpims.jpa.controller.HospitalJpaController;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class HospitalConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        HospitalJpaController controller = (HospitalJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "hospitalJpa");
        return controller.findHospital(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Hospital) {
            Hospital o = (Hospital) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: mpims.entities.Hospital");
        }
    }

}
