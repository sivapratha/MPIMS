
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<h:form>
<table id="navigation" width="100%" border="0" cellspacing="0" cellpadding="0">

    <tr>
        <td>
            <ul id="navmenu">
                <li><h:commandLink action="#{doctorInfo.listSetup}" value="Home"/>

                </li>
                <li><a href="#">Admin Management</a>
                    <ul>
                        <li> <h:commandLink action="#{login.createSetup}" value="DB Configuration" immediate="true"/> </li>
                        <li> <h:commandLink action="#{login.listSetup}" value="Object Validation" immediate="true"/> </li>
                    </ul>
                </li>
                <li><a href="#">Reports</a>
                    <ul>
                        <li> <h:commandLink action="#{category.createSetup}" value="Schema Level Comparison" immediate="true"/> </li>
                        <li> <h:commandLink action="#{category.listSetup}" value="Object Level Comparison" immediate="true"/> </li>
                       
                        <!--li><a href="#">Manage Admin</a></li-->
                    </ul>
                </li>
       
            </ul>
        </td>
    </tr>
</table>
</h:form>