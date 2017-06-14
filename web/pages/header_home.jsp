<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


            <title><h:outputText value="#{admin.webPageHeader}"/></title>
            <link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css" />
            <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
            <link href="<%=request.getContextPath()%>/css/form.css" rel="stylesheet" type="text/css" />

            <!--[if gte IE 5.5]>
<script language="JavaScript" src="js/ieh.js" type="text/JavaScript"></script>
<![endif]-->
        </head>

        <body>
            <div id="forbg">
                <table id="mainWrapper" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                       <td>
    <table id="header" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="980" colspan="3"><img src="<%=request.getContextPath()%>/images/mpims-logo.gif" alt="Medical Practitioners Information management System" width="982px" /></td>
         <%--   <td width="642"></td>
            <td width="250">
                Username:<b> <h:outputLabel value=" #{admin.mainAdmin.adminLogin.username}"/> </b><br/>
                    Admin:<b> <h:outputLabel value=" #{admin.mainAdmin.firstName} #{admin.mainAdmin.middleName} #{admin.mainAdmin.lastName}"/> </b>

            <br/> Branch:<b><h:outputText value="#{admin.mainAdmin.branch.name}"/></b>
            <br/>

            </td>--%>
        </tr>
    </table>
</td>

                    </tr>
                    <tr>
                        <td> <ul id="navmenu"><li></li></ul>
                   <%--         <jsp:include page="samenu.jsp"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td id="container">