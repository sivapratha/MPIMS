<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<f:view>
    <html>
        <head>
            <title>Medical Practitioners Information management System : Find a doctor in your area!</title>
            <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta http-equiv="Pragma" content="no-cache"/>
            <meta http-equiv="Cache-Control" content="no-store"/>
            <meta http-equiv="cache-Control" content="no-cache"/>
            <meta http-equiv="Expires" content="0"/>
            <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
            <meta http-equiv="description" content="This is my page"/>
            <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/pngfix.js"></script>
            <link href="<%=request.getContextPath()%>/css/mainStyler.css" rel="stylesheet" type="text/css" media="screen" />

            <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
     <%--       <script type="text/javascript">
                $(
                function(){
                    $('#kaptchaImage').click(function () {
                        $(this).attr('src', '<%=request.getContextPath()%>/kaptcha?'+ Math.floor(Math.random()*100) ); })});
            </script>--%>
        </head>

        <body>


                 <%--   START HEADER PART --%>
              <jsp:include page="/pages/header_home.jsp"></jsp:include>
             <%--   END HEADER PART --%>



                    <h:form styleClass="loginContent">

                        <h1 class="login">Add a Doctor!</h1>
                        <div  id="errormsg"><h:outputText value="#{login.errorMsg}"/></div>
                        <h:panelGroup id="InvalidGroup" rendered="">
                            <div class="info" >
                                <h:outputText value="UserName/Password invalid or UserBlocked"></h:outputText>
                            </div>
                        </h:panelGroup>
                        <table cellpadding="0" cellspacing="0" width="90%" id="hometable">
                            <tr><td>
                        <label>
                            Username: 
                        </label>
                                </td><td>
                        <h:inputText value="#{login.userName}" required="true" requiredMessage="Required!" id="userId"></h:inputText>
                        <div  id="errormsg"> <h:message for="userId" ></h:message></div>
                                </td></tr> <tr><td>
                        <label>
					Password:
                        </label></td><td>
                        <h:inputSecret value="#{login.password}" id="password" required="true" requiredMessage="Required!"></h:inputSecret>
                        <div  id="errormsg"> <h:message for="password" ></h:message></div>
                        </td> </tr>
                             <tr><td>


                        <label>
                            &nbsp;
                        </label>
                                 </td><td   id="loginbtn">
                                   <h:commandButton value="Login" action="#{login.verifyLogin}" ></h:commandButton>
                                 </td></tr>
                           
                              
                        </table>
                    </h:form>


                    <h:form styleClass="loginContent">

                        <h1 class="login">Find a Doctor!</h1>
                        <div  id="errormsg"><h:outputText value="#{doctorInfo.errorMsg}"/></div>
                        <h:panelGroup id="InvalidGroup" rendered="">
                            <div class="info" >
                                <h:outputText value="UserName/Password invalid or UserBlocked"></h:outputText>
                            </div>
                        </h:panelGroup>
                        <table cellpadding="0" cellspacing="0" width="90%" id="hometable">
                            <tr><td>
                        <label>
                            Hospital:
                        </label>
                                </td><td>
                        <h:selectOneMenu value="#{doctorInfo.hospital}">
                     <f:selectItems value="#{hospital.allHospitalForCombo}"></f:selectItems>
                </h:selectOneMenu>
                       
                                </td></tr> <tr><td>
                        <label>
					Specialization:
                        </label></td><td>
                            <h:selectOneMenu value="#{doctorInfo.specialization}">
                    <f:selectItems value="#{specialization.allSpecializationForCombo}"></f:selectItems>
                </h:selectOneMenu>
                        </td> </tr>
                            <tr><td>
                        <label>
					Category:
                        </label></td><td>
                            <h:selectOneMenu value="#{doctorInfo.category}">
                     <f:selectItems value="#{category.allCategoryForCombo}"></f:selectItems>
                </h:selectOneMenu>
                        </td> </tr>
                            <tr><td>
                        <label>
					Locality:
                        </label></td><td>
                            <h:selectOneMenu value="#{doctorInfo.locality}">
                     <f:selectItems value="#{locality.allLocalityForCombo}"></f:selectItems>
                </h:selectOneMenu>
                        </td> </tr>
                             <tr><td>
                        <label>
					City:
                        </label></td><td>
                            <h:selectOneMenu value="#{doctorInfo.city}">
                     <f:selectItems value="#{city.allCityForCombo}"></f:selectItems>
                </h:selectOneMenu>
                        </td> </tr>
                             <tr><td>


                        <label>
                            &nbsp;
                        </label>
                                 </td><td  id="loginbtn">
                                     <h:commandButton value="Search" action="#{doctorInfo.searchDoctor}"></h:commandButton>
                                 </td></tr>


                        </table>
                    </h:form>



                    
                    
            <%--  START FOOTER PART --%>
                <jsp:include page="/pages/footer.jsp"></jsp:include>
               <%--  START FOOTER PART --%>
        </body>
    </html>
</f:view>
