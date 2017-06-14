<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Login Detail</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>

        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Login Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Id:"/>
                <h:outputText value="#{login.login.id}" title="Id" />
                <h:outputText value="UserName:"/>
                <h:outputText value="#{login.login.userName}" title="UserName" />
                <h:outputText value="Password:"/>
                   <h:outputText value="******"/>
              <%--  <h:outputText value="#{login.login.password}" title="Password" />--%>
            </h:panelGrid>
            <br />
            <h:commandLink action="#{login.destroy}" value="Destroy">
                <f:param name="jsfcrud.currentLogin" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][login.login][login.converter].jsfcrud_invoke}" />
            </h:commandLink>
          
            <h:commandLink action="#{login.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentLogin" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][login.login][login.converter].jsfcrud_invoke}" />
            </h:commandLink>

            <h:commandLink action="#{login.createSetup}" value="New Login" />
         
            <h:commandLink action="#{login.listSetup}" value="Show All Login Items"/>
         
        </h:form>

            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>
        </body>
    </html>
</f:view>
