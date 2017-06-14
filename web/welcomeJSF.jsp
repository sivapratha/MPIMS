<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
<link rel="stylesheet" type="text/css" href="/MPIMS/faces/jsfcrud.css" />
        </head>
        <body>



              <%--   START HEADER PART --%>
              <jsp:include page="/pages/header.jsp"></jsp:include>
             <%--   END HEADER PART --%>



            <h:form>
<h1><h:outputText value="JavaServer Faces"/></h1>
    <br/>
<h:commandLink action="#{specialization.listSetup}" value="Show All Specialization Items"/>

    <br/>
<h:commandLink action="#{login.listSetup}" value="Show All Login Items"/>

    <br/>
<h:commandLink action="#{hospital.listSetup}" value="Show All Hospital Items"/>

    <br/>
<h:commandLink action="#{doctorInfo.listSetup}" value="Show All DoctorInfo Items"/>

    <br/>
<h:commandLink action="#{locality.listSetup}" value="Show All Locality Items"/>

<br/>
<h:commandLink action="#{city.listSetup}" value="Show All City Items"/>

    <br/>
<h:commandLink action="#{category.listSetup}" value="Show All Category Items"/>
</h:form>




            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>

               

        </body>
    </html>
</f:view>
