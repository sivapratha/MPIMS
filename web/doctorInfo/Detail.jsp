<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Doctor's Information Details</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Doctor's Information Details</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Id:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.id}" title="Id" />
                <h:outputText value="First Name:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.fname}" title="Fname" />
                <h:outputText value="Last Name:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.lname}" title="Lname" />
                <h:outputText value="Address:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.address}" title="Address" />
                <h:outputText value="Locality:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.locality}" title="Locality:" />
                <h:outputText value="City:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.city}" title="City" />
                <h:outputText value="Phone:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.phone}" title="Phone" />
                <h:outputText value="Email:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.email}" title="Email" />
                <h:outputText value="Web:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.web}" title="Web" />
                <h:outputText value="Council No:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.cno}" title="Cno" />
                <h:outputText value="Educational Qualification:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.edu}" title="Edu" />
                <h:outputText value="Specialization:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.spec}" title="Spec" />
                <h:outputText value="Hospital:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.hospital}" title="Hospital" />
                <h:outputText value="Available Time:"/>
                <h:outputText value="#{doctorInfo.doctorInfo.time}" title="Time" />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{doctorInfo.destroy}" value="Delete">
                <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctorInfo.doctorInfo][doctorInfo.converter].jsfcrud_invoke}" />
            </h:commandLink>
        
            <h:commandLink action="#{doctorInfo.editSetup}" value="Update">
                <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctorInfo.doctorInfo][doctorInfo.converter].jsfcrud_invoke}" />
            </h:commandLink>
   
            <h:commandLink action="#{doctorInfo.createSetup}" value="Add New Doctor" />
     
            <h:commandLink action="#{doctorInfo.listSetup}" value="Show All Doctors"/>
          
        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
