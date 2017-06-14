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
import mpims.entities.Category;
import mpims.jpa.controller.CategoryJpaController;
import mpims.jsf.classes.util.JsfUtil;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jsf.classes.util.PagingInfo;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class CategoryController {

    public CategoryController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CategoryJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "categoryJpa");
        pagingInfo = new PagingInfo();
        converter = new CategoryConverter();
    }
    private Category category = null;
    private List<Category> categoryItems = null;
    private CategoryJpaController jpaController = null;
    private CategoryConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCategoryCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCategoryItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCategoryEntities(), false);
    }

    public SelectItem[] getCategoryItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCategoryEntities(), true);
    }

    public Category getCategory() {
        if (category == null) {
            category = (Category) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCategory", converter, null);
        }
        if (category == null) {
            category = new Category();
        }
        return category;
    }

    public String listSetup() {
        reset(true);
        return "category_list";
    }

    public String createSetup() {
        reset(false);
        category = new Category();
        return "category_create";
    }

    public String create() {
        try {
            jpaController.create(category);
            JsfUtil.addSuccessMessage("Category was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("category_detail");
    }

    public String editSetup() {
        return scalarSetup("category_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        category = (Category) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCategory", converter, null);
        if (category == null) {
            String requestCategoryString = JsfUtil.getRequestParameter("jsfcrud.currentCategory");
            JsfUtil.addErrorMessage("The category with id " + requestCategoryString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String categoryString = converter.getAsString(FacesContext.getCurrentInstance(), null, category);
        String currentCategoryString = JsfUtil.getRequestParameter("jsfcrud.currentCategory");
        if (categoryString == null || categoryString.length() == 0 || !categoryString.equals(currentCategoryString)) {
            String outcome = editSetup();
            if ("category_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit category. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(category);
            JsfUtil.addSuccessMessage("Category was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCategory");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Category was successfully deleted.");
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

    public List<Category> getCategoryItems() {
        if (categoryItems == null) {
            getPagingInfo();
            categoryItems = jpaController.findCategoryEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return categoryItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "category_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "category_list";
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
        category = null;
        categoryItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Category newCategory = new Category();
        String newCategoryString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCategory);
        String categoryString = converter.getAsString(FacesContext.getCurrentInstance(), null, category);
        if (!newCategoryString.equals(categoryString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
   public List<Category> findAllCategoryEntities() {
        if (categoryItems == null) {

            categoryItems = jpaController.findAllCategoryEntities();
        }
        return categoryItems;
    }
    public List<SelectItem> getAllCategoryForCombo(){
        List<SelectItem> selectItems=new ArrayList<SelectItem>();

        List<Category> vItems=new ArrayList<Category>();
        vItems=findAllCategoryEntities();
        System.out.println("vendor size "+vItems.size());


        for(int i=0;i<vItems.size();i++){
      SelectItem item=new SelectItem(vItems.get(i).getCatName(),vItems.get(i).getCatName());
      selectItems.add(item);
        } return selectItems;

    }


}
