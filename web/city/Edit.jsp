<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing City</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Update City</h1><br/>
        <h:form>
            <h:panelGrid columns="2">
               <%-- <h:outputText value="Id:"/>
                <h:outputText value="#{city.city.id}" title="Id" />--%>
                <h:outputText value="City Name:"/>
                <h:inputText id="cityName" value="#{city.city.cityName}" title="CityName" required="true" requiredMessage="The cityName field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{city.edit}" value="Save">
                <f:param name="jsfcrud.currentCity" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][city.city][city.converter].jsfcrud_invoke}"/>
            </h:commandLink>
    
            <h:commandLink action="#{city.detailSetup}" value="View" immediate="true">
                <f:param name="jsfcrud.currentCity" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][city.city][city.converter].jsfcrud_invoke}"/>
            </h:commandLink>
   
            <h:commandLink action="#{city.listSetup}" value="Show All Cities" immediate="true"/>
         
        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
