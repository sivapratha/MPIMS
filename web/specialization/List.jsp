<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>List of Specialization </title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>List of Specialization </h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Specialization Items Found)<br />" rendered="#{specialization.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{specialization.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{specialization.pagingInfo.firstItem + 1}..#{specialization.pagingInfo.lastItem} of #{specialization.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{specialization.prev}" value="Previous #{specialization.pagingInfo.batchSize}" rendered="#{specialization.pagingInfo.firstItem >= specialization.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{specialization.next}" value="Next #{specialization.pagingInfo.batchSize}" rendered="#{specialization.pagingInfo.lastItem + specialization.pagingInfo.batchSize <= specialization.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{specialization.next}" value="Remaining #{specialization.pagingInfo.itemCount - specialization.pagingInfo.lastItem}"
                               rendered="#{specialization.pagingInfo.lastItem < specialization.pagingInfo.itemCount && specialization.pagingInfo.lastItem + specialization.pagingInfo.batchSize > specialization.pagingInfo.itemCount}"/>
                <br><br>  <h:dataTable value="#{specialization.specializationItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Specialization Nae"/>
                        </f:facet>
                        <h:outputText value="#{item.specializationName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{specialization.detailSetup}">
                            <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][specialization.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{specialization.editSetup}">
                            <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][specialization.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{specialization.destroy}">
                            <f:param name="jsfcrud.currentSpecialization" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][specialization.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
      <br />
            <h:commandLink action="#{specialization.createSetup}" value="Add New Specialization"/>
         

        </h:form>

            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
