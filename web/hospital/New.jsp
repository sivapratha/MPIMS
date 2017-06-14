<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Add New Hospital</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Add New Hospital</h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{hospital.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="Hospital Name: "/>
                <h:inputText id="hospitalName" value="#{hospital.hospital.hospitalName}" title="HospitalName" required="true" requiredMessage="The hospitalName field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{hospital.create}" value="Add New Hospital"/>
           
            <h:commandLink action="#{hospital.listSetup}" value="Show All Hospitals" immediate="true"/>
           
        </h:form>    <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
