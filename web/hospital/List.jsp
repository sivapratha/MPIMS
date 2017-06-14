<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>All Hospitals</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>All Hospitals</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Hospital Items Found)<br />" rendered="#{hospital.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{hospital.pagingInfo.itemCount > 0}">
                <h:outputText value="Hospital #{hospital.pagingInfo.firstItem + 1}..#{hospital.pagingInfo.lastItem} of #{hospital.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{hospital.prev}" value="Previous #{hospital.pagingInfo.batchSize}" rendered="#{hospital.pagingInfo.firstItem >= hospital.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{hospital.next}" value="Next #{hospital.pagingInfo.batchSize}" rendered="#{hospital.pagingInfo.lastItem + hospital.pagingInfo.batchSize <= hospital.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{hospital.next}" value="Remaining #{hospital.pagingInfo.itemCount - hospital.pagingInfo.lastItem}"
                               rendered="#{hospital.pagingInfo.lastItem < hospital.pagingInfo.itemCount && hospital.pagingInfo.lastItem + hospital.pagingInfo.batchSize > hospital.pagingInfo.itemCount}"/>
                <br><Br>  <h:dataTable value="#{hospital.hospitalItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Hospital Name:"/>
                        </f:facet>
                        <h:outputText value="#{item.hospitalName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{hospital.detailSetup}">
                            <f:param name="jsfcrud.currentHospital" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][hospital.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{hospital.editSetup}">
                            <f:param name="jsfcrud.currentHospital" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][hospital.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{hospital.destroy}">
                            <f:param name="jsfcrud.currentHospital" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][hospital.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{hospital.createSetup}" value="Add New Hospital"/>
           

        </h:form>    <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>


        </body>
    </html>
</f:view>
