<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Locality</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Update Locality</h1><br/>
        <h:form>
            <h:panelGrid columns="2">
               <%-- <h:outputText value="Id:"/>
                <h:outputText value="#{locality.locality.id}" title="Id" />--%>
                <h:outputText value="Locality Name:"/>
                <h:inputText id="localityName" value="#{locality.locality.localityName}" title="LocalityName" required="true" requiredMessage="The localityName field is required." />
            </h:panelGrid>
            <br />
            <h:commandLink action="#{locality.edit}" value="Save">
                <f:param name="jsfcrud.currentLocality" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][locality.locality][locality.converter].jsfcrud_invoke}"/>
            </h:commandLink>
    
            <h:commandLink action="#{locality.detailSetup}" value="View" immediate="true">
                <f:param name="jsfcrud.currentLocality" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][locality.locality][locality.converter].jsfcrud_invoke}"/>
            </h:commandLink>
   
            <h:commandLink action="#{locality.listSetup}" value="Show All Cities" immediate="true"/>
         
        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
