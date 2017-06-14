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
import mpims.entities.Hospital;
import mpims.jpa.controller.HospitalJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class HospitalController {

    public HospitalController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (HospitalJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "hospitalJpa");
        pagingInfo = new PagingInfo();
        converter = new HospitalConverter();
    }
    private Hospital hospital = null;
    private List<Hospital> hospitalItems = null;
    private HospitalJpaController jpaController = null;
    private HospitalConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getHospitalCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getHospitalItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findHospitalEntities(), false);
    }

    public SelectItem[] getHospitalItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findHospitalEntities(), true);
    }

    public Hospital getHospital() {
        if (hospital == null) {
            hospital = (Hospital) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHospital", converter, null);
        }
        if (hospital == null) {
            hospital = new Hospital();
        }
        return hospital;
    }

    public String listSetup() {
        reset(true);
        return "hospital_list";
    }

    public String createSetup() {
        reset(false);
        hospital = new Hospital();
        return "hospital_create";
    }

    public String create() {
        try {
            jpaController.create(hospital);
            JsfUtil.addSuccessMessage("Hospital was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("hospital_detail");
    }

    public String editSetup() {
        return scalarSetup("hospital_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        hospital = (Hospital) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHospital", converter, null);
        if (hospital == null) {
            String requestHospitalString = JsfUtil.getRequestParameter("jsfcrud.currentHospital");
            JsfUtil.addErrorMessage("The hospital with id " + requestHospitalString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String hospitalString = converter.getAsString(FacesContext.getCurrentInstance(), null, hospital);
        String currentHospitalString = JsfUtil.getRequestParameter("jsfcrud.currentHospital");
        if (hospitalString == null || hospitalString.length() == 0 || !hospitalString.equals(currentHospitalString)) {
            String outcome = editSetup();
            if ("hospital_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit hospital. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(hospital);
            JsfUtil.addSuccessMessage("Hospital was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentHospital");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Hospital was successfully deleted.");
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

    public List<Hospital> getHospitalItems() {
        if (hospitalItems == null) {
            getPagingInfo();
            hospitalItems = jpaController.findHospitalEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return hospitalItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "hospital_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "hospital_list";
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
        hospital = null;
        hospitalItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Hospital newHospital = new Hospital();
        String newHospitalString = converter.getAsString(FacesContext.getCurrentInstance(), null, newHospital);
        String hospitalString = converter.getAsString(FacesContext.getCurrentInstance(), null, hospital);
        if (!newHospitalString.equals(hospitalString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
 public List<Hospital> findAllHospitalEntities() {
        if (hospitalItems == null) {

            hospitalItems = jpaController.findAllHospitalEntities();
        }
        return hospitalItems;
    }
    public List<SelectItem> getAllHospitalForCombo(){
        List<SelectItem> selectItems=new ArrayList<SelectItem>();

        List<Hospital> vItems=new ArrayList<Hospital>();
        vItems=findAllHospitalEntities();
        System.out.println("vendor size "+vItems.size());


        for(int i=0;i<vItems.size();i++){
      SelectItem item=new SelectItem(vItems.get(i).getHospitalName(),vItems.get(i).getHospitalName());
      selectItems.add(item);
        } return selectItems;

    }
}
