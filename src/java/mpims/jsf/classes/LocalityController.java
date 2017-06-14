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
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import mpims.entities.Locality;
import mpims.jpa.controller.LocalityJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class LocalityController {

    public LocalityController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (LocalityJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "localityJpa");
        pagingInfo = new PagingInfo();
        converter = new LocalityConverter();
    }
    private Locality locality = null;
    private List<Locality> localityItems = null;
    private LocalityJpaController jpaController = null;
    private LocalityConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getLocalityCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getLocalityItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findLocalityEntities(), false);
    }

    public SelectItem[] getLocalityItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findLocalityEntities(), true);
    }

    public Locality getLocality() {
        if (locality == null) {
            locality = (Locality) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLocality", converter, null);
        }
        if (locality == null) {
            locality = new Locality();
        }
        return locality;
    }

    public String listSetup() {
        reset(true);
        return "locality_list";
    }

    public String createSetup() {
        reset(false);
        locality = new Locality();
        return "locality_create";
    }

    public String create() {
        try {
            jpaController.create(locality);
            JsfUtil.addSuccessMessage("Locality was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("locality_detail");
    }

    public String editSetup() {
        return scalarSetup("locality_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        locality = (Locality) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLocality", converter, null);
        if (locality == null) {
            String requestLocalityString = JsfUtil.getRequestParameter("jsfcrud.currentLocality");
            JsfUtil.addErrorMessage("The locality with id " + requestLocalityString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String localityString = converter.getAsString(FacesContext.getCurrentInstance(), null, locality);
        String currentLocalityString = JsfUtil.getRequestParameter("jsfcrud.currentLocality");
        if (localityString == null || localityString.length() == 0 || !localityString.equals(currentLocalityString)) {
            String outcome = editSetup();
            if ("locality_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit locality. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(locality);
            JsfUtil.addSuccessMessage("Locality was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentLocality");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Locality was successfully deleted.");
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

    public List<Locality> getLocalityItems() {
        if (localityItems == null) {
            getPagingInfo();
            localityItems = jpaController.findLocalityEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return localityItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "locality_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "locality_list";
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
        locality = null;
        localityItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Locality newLocality = new Locality();
        String newLocalityString = converter.getAsString(FacesContext.getCurrentInstance(), null, newLocality);
        String localityString = converter.getAsString(FacesContext.getCurrentInstance(), null, locality);
        if (!newLocalityString.equals(localityString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
public List<Locality> findAllLocalityEntities() {
        if (localityItems == null) {

            localityItems = jpaController.findAllLocalityEntities();
        }
        return localityItems;
    }
    public List<SelectItem> getAllLocalityForCombo(){
        List<SelectItem> selectItems=new ArrayList<SelectItem>();

        List<Locality> vItems=new ArrayList<Locality>();
        vItems=findAllLocalityEntities();
        System.out.println("vendor size "+vItems.size());


        for(int i=0;i<vItems.size();i++){
      SelectItem item=new SelectItem(vItems.get(i).getLocalityName(),vItems.get(i).getLocalityName());
      selectItems.add(item);
        } return selectItems;

    }
}
