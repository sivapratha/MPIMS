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
import mpims.entities.DoctorInfo;
import mpims.jpa.controller.DoctorInfoJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class DoctorInfoController {

    public DoctorInfoController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (DoctorInfoJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "doctorInfoJpa");
        pagingInfo = new PagingInfo();
        converter = new DoctorInfoConverter();
    }
    private DoctorInfo doctorInfo = null;
    private List<DoctorInfo> doctorInfoItems = null;
    private DoctorInfoJpaController jpaController = null;
    private DoctorInfoConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getDoctorInfoCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getDoctorInfoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findDoctorInfoEntities(), false);
    }

    public SelectItem[] getDoctorInfoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findDoctorInfoEntities(), true);
    }

    public DoctorInfo getDoctorInfo() {
        if (doctorInfo == null) {
            doctorInfo = (DoctorInfo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDoctorInfo", converter, null);
        }
        if (doctorInfo == null) {
            doctorInfo = new DoctorInfo();
        }
        return doctorInfo;
    }

    public String listSetup() {
        reset(true);
        return "doctorInfo_list";
    }

    public String createSetup() {
        reset(false);
        doctorInfo = new DoctorInfo();
        return "doctorInfo_create";
    }

    public String create() {
        try {
            jpaController.create(doctorInfo);
            JsfUtil.addSuccessMessage("DoctorInfo was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("doctorInfo_detail");
    }

    public String searchDetails() {
        return scalarSetup("doctor_detail");
    }
    

    public String editSetup() {
        return scalarSetup("doctorInfo_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        doctorInfo = (DoctorInfo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDoctorInfo", converter, null);
        if (doctorInfo == null) {
            String requestDoctorInfoString = JsfUtil.getRequestParameter("jsfcrud.currentDoctorInfo");
            JsfUtil.addErrorMessage("The doctorInfo with id " + requestDoctorInfoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String doctorInfoString = converter.getAsString(FacesContext.getCurrentInstance(), null, doctorInfo);
        String currentDoctorInfoString = JsfUtil.getRequestParameter("jsfcrud.currentDoctorInfo");
        if (doctorInfoString == null || doctorInfoString.length() == 0 || !doctorInfoString.equals(currentDoctorInfoString)) {
            String outcome = editSetup();
            if ("doctorInfo_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit doctorInfo. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(doctorInfo);
            JsfUtil.addSuccessMessage("DoctorInfo was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDoctorInfo");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("DoctorInfo was successfully deleted.");
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

    public List<DoctorInfo> getDoctorInfoItems() {
        if (doctorInfoItems == null) {
            getPagingInfo();
            doctorInfoItems = jpaController.findDoctorInfoEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return doctorInfoItems;
    }
   
 public List<DoctorInfo> getDoctorInfoItemsAll() {
        if (doctorInfoItems == null) {
              doctorInfoItems = jpaController.findDoctorInfoEntitiesAll();
        }
        return doctorInfoItems;
    }
    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "doctorInfo_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "doctorInfo_list";
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
        doctorInfo = null;
        doctorInfoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        DoctorInfo newDoctorInfo = new DoctorInfo();
        String newDoctorInfoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDoctorInfo);
        String doctorInfoString = converter.getAsString(FacesContext.getCurrentInstance(), null, doctorInfo);
        if (!newDoctorInfoString.equals(doctorInfoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

  String hospital;
  String specialization;
  String category;    
  String city;    
  String locality;
  String time;
  String edu;
  String lastname;
  String firstname;
 
   String errorMsg="";

    public String searchDoctor()
    {
            errorMsg="";
            List<DoctorInfo> doctorInfos=getDoctorInfoItemsAll();
            System.out.println("doctor Infos size is"+doctorInfos.size());
            System.out.println("the selected hospital name is"+hospital);
            System.out.println("specialization"+specialization);
            System.out.println("catagory"+category);
            System.out.println("City"+city);
            System.out.println("Locality"+locality);
            searchDoctorInfo=new ArrayList<DoctorInfo>();
            for(int i=0; i<doctorInfos.size(); i++)
            {
                System.out.println("spec from database"+doctorInfos.get(i).getSpec());
                if(doctorInfos.get(i).getHospital().equals(hospital)){
                    System.out.println("inside condition");
                    if(doctorInfos.get(i).getCity().equals(city) && doctorInfos.get(i).getLocality().equals(locality)){
                        if(doctorInfos.get(i).getSpec().equals(specialization)){
                            if(doctorInfos.get(i).getWeb().equals(category)){
                            DoctorInfo doctorInfo=new DoctorInfo();
                            doctorInfo.setId(doctorInfos.get(i).getId());
                            doctorInfo.setFname(doctorInfos.get(i).getFname());
                            doctorInfo.setLname(doctorInfos.get(i).getLname());
                            doctorInfo.setHospital(doctorInfos.get(i).getHospital());
                            doctorInfo.setSpec(doctorInfos.get(i).getSpec());
                            doctorInfo.setEdu(doctorInfos.get(i).getEdu());
                            doctorInfo.setCity(doctorInfos.get(i).getCity());
                            doctorInfo.setLocality(doctorInfos.get(i).getLocality());
                            doctorInfo.setTime(doctorInfos.get(i).getTime());
                            searchDoctorInfo.add(doctorInfo);
                           
                            }
                            else errorMsg="Docotor not found in this category";
                          
                        }
                           else errorMsg="Docotor not found in this specialization";
                           
                    }
                       else errorMsg="Docotor not found in this location";
                           
                }
                   else errorMsg="Docotor not found in this hospital";
                          
            }
            return "viewDoctor";
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

   

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

List<DoctorInfo> searchDoctorInfo;

    public List<DoctorInfo> getSearchDoctorInfo() {
        return searchDoctorInfo;
    }

    public void setSearchDoctorInfo(List<DoctorInfo> searchDoctorInfo) {
        this.searchDoctorInfo = searchDoctorInfo;
    }




}
