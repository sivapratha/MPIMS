<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>All Therapy Category</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>


              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>All Therapy Category</h1>   
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Category Items Found)<br />" rendered="#{category.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{category.pagingInfo.itemCount > 0}">
                <h:outputText value=" #{category.pagingInfo.firstItem + 1}..#{category.pagingInfo.lastItem} of #{category.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{category.prev}" value="Previous #{category.pagingInfo.batchSize}" rendered="#{category.pagingInfo.firstItem >= category.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{category.next}" value="Next #{category.pagingInfo.batchSize}" rendered="#{category.pagingInfo.lastItem + category.pagingInfo.batchSize <= category.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{category.next}" value="Remaining #{category.pagingInfo.itemCount - category.pagingInfo.lastItem}"
                               rendered="#{category.pagingInfo.lastItem < category.pagingInfo.itemCount && category.pagingInfo.lastItem + category.pagingInfo.batchSize > category.pagingInfo.itemCount}"/>
                <br><br>  <h:dataTable value="#{category.categoryItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Theaphy Name"/>
                        </f:facet>
                        <h:outputText value="#{item.catName}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{category.detailSetup}">
                            <f:param name="jsfcrud.currentCategory" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][category.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{category.editSetup}">
                            <f:param name="jsfcrud.currentCategory" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][category.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{category.destroy}">
                            <f:param name="jsfcrud.currentCategory" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][category.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{category.createSetup}" value="Add New Therapy"/>
  
          

        </h:form>


            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               
        </body>
    </html>
</f:view>
