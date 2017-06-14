<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>List of  Localities</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>

              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>List of  Localities</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Locality Items Found)<br />" rendered="#{locality.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{locality.pagingInfo.itemCount > 0}">
                <h:outputText value="Locality #{locality.pagingInfo.firstItem + 1}..#{locality.pagingInfo.lastItem} of #{locality.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{locality.prev}" value="Previous #{locality.pagingInfo.batchSize}" rendered="#{locality.pagingInfo.firstItem >= locality.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{locality.next}" value="Next #{locality.pagingInfo.batchSize}" rendered="#{locality.pagingInfo.lastItem + locality.pagingInfo.batchSize <= locality.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{locality.next}" value="Remaining #{locality.pagingInfo.itemCount - locality.pagingInfo.lastItem}"
                               rendered="#{locality.pagingInfo.lastItem < locality.pagingInfo.itemCount && locality.pagingInfo.lastItem + locality.pagingInfo.batchSize > locality.pagingInfo.itemCount}"/>
                <br/><br/>  <h:dataTable value="#{locality.localityItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Locality Name:"/>
                        </f:facet>
                        <h:outputText value="#{item.localityName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{locality.detailSetup}">
                            <f:param name="jsfcrud.currentLocality" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][locality.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{locality.editSetup}">
                            <f:param name="jsfcrud.currentLocality" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][locality.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{locality.destroy}">
                            <f:param name="jsfcrud.currentLocality" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][locality.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{locality.createSetup}" value=" Add Newity Locality"/>
           

        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
