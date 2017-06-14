<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Category</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>


              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Category</h1><br>
        <h:form>
            <h:panelGrid columns="2">
               <%-- <h:outputText value="Id:"/>
                <h:outputText value="#{category.category.id}" title="Id" />--%>
                <h:outputText value="Theraphy Name:"/>
                <h:inputText id="catName" value="#{category.category.catName}" title="CatName" required="true" requiredMessage="The catName field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{category.edit}" value="Save">
                <f:param name="jsfcrud.currentCategory" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][category.category][category.converter].jsfcrud_invoke}"/>
            </h:commandLink>
        
          <%--  <h:commandLink action="#{category.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentCategory" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][category.category][category.converter].jsfcrud_invoke}"/>
            </h:commandLink>
          --%>
            <h:commandLink action="#{category.listSetup}" value="Show All Therapies" immediate="true"/>
       
         
        </h:form>


            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
