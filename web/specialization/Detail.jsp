<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Specialization Details</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

            <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Specialization Details</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Id:"/>
                <h:outputText value="#{specialization.specialization.id}" title="Id" />
                <h:outputText value="SpecializationName:"/>
                <h:outputText value="#{specialization.specialization.specializationName}" title="SpecializationName" />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{specialization.destroy}" value="Delete">
                <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][specialization.specialization][specialization.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{specialization.editSetup}" value="Update">
                <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][specialization.specialization][specialization.converter].jsfcrud_invoke}" />
            </h:commandLink>
         
            <h:commandLink action="#{specialization.createSetup}" value="Add New Specialization" />
           
            <h:commandLink action="#{specialization.listSetup}" value="Show All Specializations"/>
         
        </h:form>

            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
