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
import mpims.entities.Specialization;
import mpims.jpa.controller.SpecializationJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class SpecializationController {

    public SpecializationController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (SpecializationJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "specializationJpa");
        pagingInfo = new PagingInfo();
        converter = new SpecializationConverter();
    }
    private Specialization specialization = null;
    private List<Specialization> specializationItems = null;
    private SpecializationJpaController jpaController = null;
    private SpecializationConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getSpecializationCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getSpecializationItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findSpecializationEntities(), false);
    }

    public SelectItem[] getSpecializationItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findSpecializationEntities(), true);
    }

    public Specialization getSpecialization() {
        if (specialization == null) {
            specialization = (Specialization) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSpecialization", converter, null);
        }
        if (specialization == null) {
            specialization = new Specialization();
        }
        return specialization;
    }

    public String listSetup() {
        reset(true);
        return "specialization_list";
    }

    public String createSetup() {
        reset(false);
        specialization = new Specialization();
        return "specialization_create";
    }

    public String create() {
        try {
            jpaController.create(specialization);
            JsfUtil.addSuccessMessage("Specialization was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("specialization_detail");
    }

    public String editSetup() {
        return scalarSetup("specialization_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        specialization = (Specialization) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentSpecialization", converter, null);
        if (specialization == null) {
            String requestSpecializationString = JsfUtil.getRequestParameter("jsfcrud.currentSpecialization");
            JsfUtil.addErrorMessage("The specialization with id " + requestSpecializationString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String specializationString = converter.getAsString(FacesContext.getCurrentInstance(), null, specialization);
        String currentSpecializationString = JsfUtil.getRequestParameter("jsfcrud.currentSpecialization");
        if (specializationString == null || specializationString.length() == 0 || !specializationString.equals(currentSpecializationString)) {
            String outcome = editSetup();
            if ("specialization_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit specialization. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(specialization);
            JsfUtil.addSuccessMessage("Specialization was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentSpecialization");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Specialization was successfully deleted.");
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

    public List<Specialization> getSpecializationItems() {
        if (specializationItems == null) {
            getPagingInfo();
            specializationItems = jpaController.findSpecializationEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return specializationItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "specialization_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "specialization_list";
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
        specialization = null;
        specializationItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Specialization newSpecialization = new Specialization();
        String newSpecializationString = converter.getAsString(FacesContext.getCurrentInstance(), null, newSpecialization);
        String specializationString = converter.getAsString(FacesContext.getCurrentInstance(), null, specialization);
        if (!newSpecializationString.equals(specializationString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
      public List<Specialization> findAllSpecializationEntities() {
        if (specializationItems == null) {

            specializationItems = jpaController.findAllSpecializationEntities();
        }
        return specializationItems;
    }
    public List<SelectItem> getAllSpecializationForCombo(){
        List<SelectItem> selectItems=new ArrayList<SelectItem>();

        List<Specialization> vItems=new ArrayList<Specialization>();
        vItems=findAllSpecializationEntities();
        System.out.println("vendor size "+vItems.size());


        for(int i=0;i<vItems.size();i++){
      SelectItem item=new SelectItem(vItems.get(i).getSpecializationName(),vItems.get(i).getSpecializationName());
      selectItems.add(item);
        } return selectItems;

    }


}
