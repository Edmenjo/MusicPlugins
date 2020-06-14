<%-- 
    Document   : unknown
    Created on : 13-mar-2020, 17:52:18
    Author     : zuzu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>Unknown Command</title>
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>UNKNOWN COMMAND</h1>
        <%String url = (String) session.getAttribute("url");%>
        <a href="/MusicLibrary<%=url%>">Volver</a>
        </div>
        <jsp:include page="resource/footer.jsp" />
    </body>

