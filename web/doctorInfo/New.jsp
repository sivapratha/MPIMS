<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Add New Doctor Information</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Add New Doctor </h1>
        <h:form>
            <h:inputHidden id="validateCreateField" validator="#{doctorInfo.validateCreate}" value="value"/>
            <h:panelGrid columns="2">
                <h:outputText value="First Name:"/>
                <h:inputText id="fname" value="#{doctorInfo.doctorInfo.fname}" title="Fname" required="true" requiredMessage="The fname field is required." />
                <h:outputText value="Last Name:"/>
                <h:inputText id="lname" value="#{doctorInfo.doctorInfo.lname}" title="Lname" required="true" requiredMessage="The lname field is required." />
                <h:outputText value="Address:"/>
                <h:inputText id="address" value="#{doctorInfo.doctorInfo.address}" title="Address" required="true" requiredMessage="The address field is required." />
                <h:outputText value="Locality:"/>
                 <h:selectOneMenu value="#{doctorInfo.doctorInfo.locality}">
                     <f:selectItems value="#{locality.allLocalityForCombo}"></f:selectItems>
                </h:selectOneMenu>
               <h:outputText value="City:"/>
                 <h:selectOneMenu value="#{doctorInfo.doctorInfo.city}">
                     <f:selectItems value="#{city.allCityForCombo}"></f:selectItems>
                </h:selectOneMenu>
               
                <h:outputText value="Phone:"/>
                <h:inputText id="phone" value="#{doctorInfo.doctorInfo.phone}" title="Phone" required="true" requiredMessage="The phone field is required." />
                <h:outputText value="Email:"/>
                <h:inputText id="email" value="#{doctorInfo.doctorInfo.email}" title="Email" required="true" requiredMessage="The email field is required." />
                <h:outputText value="Therapy Category:"/>
                 <h:selectOneMenu value="#{doctorInfo.doctorInfo.web}">
                     <f:selectItems value="#{category.allCategoryForCombo}"></f:selectItems>
                </h:selectOneMenu>
                <%-- <h:outputText value="Web:"/>
                <h:inputText id="web" value="#{doctorInfo.doctorInfo.web}" title="Web" required="true" requiredMessage="The web field is required." />
                --%><h:outputText value="Council No:"/>
                <h:inputText id="cno" value="#{doctorInfo.doctorInfo.cno}" title="Cno" required="true" requiredMessage="The cno field is required." />
                <h:outputText value="Educational Qualification:"/>
                <h:inputText id="edu" value="#{doctorInfo.doctorInfo.edu}" title="Edu" required="true" requiredMessage="The edu field is required." />
                <h:outputText value="Specialization:"/>
                <h:selectOneMenu value="#{doctorInfo.doctorInfo.spec}">
                    <f:selectItems value="#{specialization.allSpecializationForCombo}"></f:selectItems>
                </h:selectOneMenu>
                <%--
                <h:inputText id="spec" value="#{doctorInfo.doctorInfo.spec}" title="Spec" required="true" requiredMessage="The spec field is required." />
               --%> <h:outputText value="Hospital:"/>
                 <h:selectOneMenu value="#{doctorInfo.doctorInfo.hospital}">
                     <f:selectItems value="#{hospital.allHospitalForCombo}"></f:selectItems>
                </h:selectOneMenu>
                <%--<h:inputText id="hospital" value="#{doctorInfo.doctorInfo.hospital}" title="Hospital" required="true" requiredMessage="The hospital field is required." />
               --%> <h:outputText value="Available Time:"/>
                <h:inputText id="time" value="#{doctorInfo.doctorInfo.time}" title="Time" required="true" requiredMessage="The time field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{doctorInfo.create}" value="Add New Doctor"/>
    
            <h:commandLink action="#{doctorInfo.listSetup}" value="Show All Doctors" immediate="true"/>
       
        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
