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
import mpims.entities.City;
import mpims.jpa.controller.CityJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class CityController {

    public CityController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CityJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cityJpa");
        pagingInfo = new PagingInfo();
        converter = new CityConverter();
    }
    private City city = null;
    private List<City> cityItems = null;
    private CityJpaController jpaController = null;
    private CityConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCityCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCityItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCityEntities(), false);
    }

    public SelectItem[] getCityItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCityEntities(), true);
    }

    public City getCity() {
        if (city == null) {
            city = (City) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCity", converter, null);
        }
        if (city == null) {
            city = new City();
        }
        return city;
    }

    public String listSetup() {
        reset(true);
        return "city_list";
    }

    public String createSetup() {
        reset(false);
        city = new City();
        return "city_create";
    }

    public String create() {
        try {
            jpaController.create(city);
            JsfUtil.addSuccessMessage("City was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("city_detail");
    }

    public String editSetup() {
        return scalarSetup("city_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        city = (City) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCity", converter, null);
        if (city == null) {
            String requestCityString = JsfUtil.getRequestParameter("jsfcrud.currentCity");
            JsfUtil.addErrorMessage("The city with id " + requestCityString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cityString = converter.getAsString(FacesContext.getCurrentInstance(), null, city);
        String currentCityString = JsfUtil.getRequestParameter("jsfcrud.currentCity");
        if (cityString == null || cityString.length() == 0 || !cityString.equals(currentCityString)) {
            String outcome = editSetup();
            if ("city_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit city. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(city);
            JsfUtil.addSuccessMessage("City was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCity");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("City was successfully deleted.");
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

    public List<City> getCityItems() {
        if (cityItems == null) {
            getPagingInfo();
            cityItems = jpaController.findCityEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return cityItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "city_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "city_list";
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
        city = null;
        cityItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        City newCity = new City();
        String newCityString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCity);
        String cityString = converter.getAsString(FacesContext.getCurrentInstance(), null, city);
        if (!newCityString.equals(cityString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
public List<City> findAllCityEntities() {
        if (cityItems == null) {

            cityItems = jpaController.findAllCityEntities();
        }
        return cityItems;
    }
    public List<SelectItem> getAllCityForCombo(){
        List<SelectItem> selectItems=new ArrayList<SelectItem>();

        List<City> vItems=new ArrayList<City>();
        vItems=findAllCityEntities();
        System.out.println("vendor size "+vItems.size());


        for(int i=0;i<vItems.size();i++){
      SelectItem item=new SelectItem(vItems.get(i).getCityName(),vItems.get(i).getCityName());
      selectItems.add(item);
        } return selectItems;

    }
}
