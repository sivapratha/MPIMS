<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>All Users</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>

        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>All Users</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Login Items Found)<br />" rendered="#{login.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{login.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{login.pagingInfo.firstItem + 1}..#{login.pagingInfo.lastItem} of #{login.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{login.prev}" value="Previous #{login.pagingInfo.batchSize}" rendered="#{login.pagingInfo.firstItem >= login.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{login.next}" value="Next #{login.pagingInfo.batchSize}" rendered="#{login.pagingInfo.lastItem + login.pagingInfo.batchSize <= login.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{login.next}" value="Remaining #{login.pagingInfo.itemCount - login.pagingInfo.lastItem}"
                               rendered="#{login.pagingInfo.lastItem < login.pagingInfo.itemCount && login.pagingInfo.lastItem + login.pagingInfo.batchSize > login.pagingInfo.itemCount}"/>
                <br/><br/>  <h:dataTable value="#{login.loginItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Username"/>
                        </f:facet>
                        <h:outputText value="#{item.userName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Password"/>
                        </f:facet>
                        <h:outputText value="*******"/>
                       <%-- <h:outputText value="#{item.password}"/>--%>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{login.detailSetup}">
                            <f:param name="jsfcrud.currentLogin" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][login.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{login.editSetup}">
                            <f:param name="jsfcrud.currentLogin" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][login.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{login.destroy}">
                            <f:param name="jsfcrud.currentLogin" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][login.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{login.createSetup}" value="Create New Login"/>
          

        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

        </body>
    </html>
</f:view>
