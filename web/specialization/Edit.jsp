<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Update Specialization</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Update Specialization</h1>
        <h:form>
            <h:panelGrid columns="2">
                <%--<h:outputText value="Id:"/>
                <h:outputText value="#{specialization.specialization.id}" title="Id" />--%>
                <h:outputText value="Specialization Name: "/>
                <h:inputText id="specializationName" value="#{specialization.specialization.specializationName}" title="SpecializationName" required="true" requiredMessage="The specializationName field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{specialization.edit}" value="Save">
                <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][specialization.specialization][specialization.converter].jsfcrud_invoke}"/>
            </h:commandLink>
       
            <h:commandLink action="#{specialization.detailSetup}" value="View" immediate="true">
                <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][specialization.specialization][specialization.converter].jsfcrud_invoke}"/>
            </h:commandLink>
         
            <h:commandLink action="#{specialization.listSetup}" value="Show All Specializations" immediate="true"/>
           
        </h:form>

            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
