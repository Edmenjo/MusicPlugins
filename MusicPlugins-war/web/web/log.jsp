<%-- 
    Document   : log
    Created on : 12-may-2020, 1:31:13
    Author     : zuzu
--%>

<%@page import="ejbs.Log"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>Log</title>
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
            <%
            Log log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
            String logg = log.getLog();%>
            <h3> <%=logg%> </h3>
            </div>
            
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>
