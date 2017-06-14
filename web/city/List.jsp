<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>List of  Cities</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>List of  Cities</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No City Items Found)<br />" rendered="#{city.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{city.pagingInfo.itemCount > 0}">
                <h:outputText value="City #{city.pagingInfo.firstItem + 1}..#{city.pagingInfo.lastItem} of #{city.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{city.prev}" value="Previous #{city.pagingInfo.batchSize}" rendered="#{city.pagingInfo.firstItem >= city.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{city.next}" value="Next #{city.pagingInfo.batchSize}" rendered="#{city.pagingInfo.lastItem + city.pagingInfo.batchSize <= city.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{city.next}" value="Remaining #{city.pagingInfo.itemCount - city.pagingInfo.lastItem}"
                               rendered="#{city.pagingInfo.lastItem < city.pagingInfo.itemCount && city.pagingInfo.lastItem + city.pagingInfo.batchSize > city.pagingInfo.itemCount}"/>
                <br/><br/>  <h:dataTable value="#{city.cityItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="City Name:"/>
                        </f:facet>
                        <h:outputText value="#{item.cityName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{city.detailSetup}">
                            <f:param name="jsfcrud.currentCity" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][city.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{city.editSetup}">
                            <f:param name="jsfcrud.currentCity" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][city.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{city.destroy}">
                            <f:param name="jsfcrud.currentCity" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][city.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{city.createSetup}" value=" Add New City"/>
           

        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
