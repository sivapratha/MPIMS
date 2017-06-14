<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Doctors' List</title>
            <link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>
              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>


        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Doctors' List</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No DoctorInfo Items Found)<br />" rendered="#{doctorInfo.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{doctorInfo.pagingInfo.itemCount > 0}">
                <h:outputText value="Doctor #{doctorInfo.pagingInfo.firstItem + 1}..#{doctorInfo.pagingInfo.lastItem} of #{doctorInfo.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{doctorInfo.prev}" value="Previous #{doctorInfo.pagingInfo.batchSize}" rendered="#{doctorInfo.pagingInfo.firstItem >= doctorInfo.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{doctorInfo.next}" value="Next #{doctorInfo.pagingInfo.batchSize}" rendered="#{doctorInfo.pagingInfo.lastItem + doctorInfo.pagingInfo.batchSize <= doctorInfo.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{doctorInfo.next}" value="Remaining #{doctorInfo.pagingInfo.itemCount - doctorInfo.pagingInfo.lastItem}"
                               rendered="#{doctorInfo.pagingInfo.lastItem < doctorInfo.pagingInfo.itemCount && doctorInfo.pagingInfo.lastItem + doctorInfo.pagingInfo.batchSize > doctorInfo.pagingInfo.itemCount}"/>
                <br><br> <h:dataTable value="#{doctorInfo.doctorInfoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                   <h:column>
                        <f:facet name="header" >
                            <h:outputText value="First Name"/>
                        </f:facet>
                        <h:outputText value="#{item.fname}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Last Name"/>
                        </f:facet>
                        <h:outputText value="#{item.lname}"/>
                    </h:column>
               <%--     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Address"/>
                        </f:facet>
                        <h:outputText value="#{item.address}"/>
                    </h:column>--%>
                   <h:column>
                        <f:facet name="header">
                            <h:outputText value="Locality"/>
                        </f:facet>
                        <h:outputText value="#{item.locality}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="City"/>
                        </f:facet>
                        <h:outputText value="#{item.city}"/>
                    </h:column>
                  <%--  <h:column>
                        <f:facet name="header">
                            <h:outputText value="Phone"/>
                        </f:facet>
                        <h:outputText value="#{item.phone}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Email"/>
                        </f:facet>
                        <h:outputText value="#{item.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Web"/>
                        </f:facet>
                        <h:outputText value="#{item.web}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cno"/>
                        </f:facet>
                        <h:outputText value="#{item.cno}"/>
                    </h:column>--%>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Education"/>
                        </f:facet>
                        <h:outputText value="#{item.edu}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Specialization"/>
                        </f:facet>
                        <h:outputText value="#{item.spec}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Hospital"/>
                        </f:facet>
                        <h:outputText value="#{item.hospital}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Time"/>
                        </f:facet>
                        <h:outputText value="#{item.time}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="View" action="#{doctorInfo.detailSetup}">
                            <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctorInfo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Update" action="#{doctorInfo.editSetup}">
                            <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctorInfo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Delete" action="#{doctorInfo.destroy}">
                            <f:param name="jsfcrud.currentDoctorInfo" value="#{jsfcrud_class['mpims.jsf.classes.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctorInfo.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{doctorInfo.createSetup}" value="Add New Doctor"/>


        </h:form>
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>


        </body>
    </html>
</f:view>
