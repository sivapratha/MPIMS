<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing DoctorInfo</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing DoctorInfo</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Id:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.id}" title="Id" />
                <h:outputText value="Fname:"/>
                <h:inputText id="fname" value="#{doctorInfo.doctorInfo.fname}" title="Fname" required="true" requiredMessage="The fname field is required." />
                <h:outputText value="Lname:"/>
                <h:inputText id="lname" value="#{doctorInfo.doctorInfo.lname}" title="Lname" required="true" requiredMessage="The lname field is required." />
                <h:outputText value="Address:"/>
                <h:inputText id="address" value="#{doctorInfo.doctorInfo.address}" title="Address" required="true" requiredMessage="The address field is required." />
                <h:outputText value="Locality:"/>
                <h:inputText id="locality" value="#{doctorInfo.doctorInfo.locality}" title="Locality" required="true" requiredMessage="The city field is required." />
                <h:outputText value="City:"/>
                <h:inputText id="city" value="#{doctorInfo.doctorInfo.city}" title="City" required="true" requiredMessage="The city field is required." />
                <h:outputText value="Phone:"/>
                <h:inputText id="phone" value="#{doctorInfo.doctorInfo.phone}" title="Phone" required="true" requiredMessage="The phone field is required." />
                <h:outputText value="Email:"/>
                <h:inputText id="email" value="#{doctorInfo.doctorInfo.email}" title="Email" required="true" requiredMessage="The email field is required." />
                <h:outputText value="Web:"/>
                <h:inputText id="web" value="#{doctorInfo.doctorInfo.web}" title="Web" required="true" requiredMessage="The web field is required." />
                <h:outputText value="Cno:"/>
                <h:inputText id="cno" value="#{doctorInfo.doctorInfo.cno}" title="Cno" required="true" requiredMessage="The cno field is required." />
                <h:outputText value="Edu:"/>
                <h:inputText id="edu" value="#{doctorInfo.doctorInfo.edu}" title="Edu" required="true" requiredMessage="The edu field is required." />
                <h:outputText value="Spec:"/>
                <h:inputText id="spec" value="#{doctorInfo.doctorInfo.spec}" title="Spec" required="true" requiredMessage="The spec field is required." />
                <h:outputText value="Hospital:"/>
                <h:inputText id="hospital" value="#{doctorInfo.doctorInfo.hospital}" title="Hospital" required="true" requiredMessage="The hospital field is required." />
                <h:outputText value="Time:"/>
                <h:inputText id="time" value="#{doctorInfo.doctorInfo.time}" title="Time" required="true" requiredMessage="The time field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{doctorInfo.edit}" value="Save">
                <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctorInfo.doctorInfo][doctorInfo.converter].jsfcrud_invoke}"/>
            </h:commandLink>
     
            <h:commandLink action="#{doctorInfo.detailSetup}" value="View" immediate="true">
                <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctorInfo.doctorInfo][doctorInfo.converter].jsfcrud_invoke}"/>
            </h:commandLink>
        
            <h:commandLink action="#{doctorInfo.listSetup}" value="Show All Doctors" immediate="true"/>
          
        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
