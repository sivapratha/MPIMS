<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Hospital Details</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Hospital Details</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Id:"/>
                <h:outputText value="#{hospital.hospital.id}" title="Id" />
                <h:outputText value="Hospital Name:"/>
                <h:outputText value="#{hospital.hospital.hospitalName}" title="HospitalName" />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{hospital.destroy}" value="Delete">
                <f:param name="jsfcrud.currentHospital" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][hospital.hospital][hospital.converter].jsfcrud_invoke}" />
            </h:commandLink>
            
            <h:commandLink action="#{hospital.editSetup}" value="Update">
                <f:param name="jsfcrud.currentHospital" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][hospital.hospital][hospital.converter].jsfcrud_invoke}" />
            </h:commandLink>
         
            <h:commandLink action="#{hospital.createSetup}" value="Add New Hospital" />
       
            <h:commandLink action="#{hospital.listSetup}" value="Show All Hospitals"/>
            
        </h:form>    <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
