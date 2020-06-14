<%-- 
    Document   : statistics
    Created on : 11-may-2020, 18:28:46
    Author     : zuzu
--%>

<%@page import="java.util.HashMap"%>
<%@page import="ejbs.Counter"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics</title>
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h2> Estadísticas de Compras </h2>
            
            <%
                Counter counter = (Counter) InitialContext.doLookup("java:global/MusicMusicPluginsins/VSTs-ejb/Counter!ejbs.Counter");
                HashMap<String, Integer> map = counter.getMap();
            %> <h3> Número de carritos: <%=map.size()-2%> </h3> <%
                for(HashMap.Entry<String, Integer> ent: map.entrySet()) {
                    %> <h3> <%=ent.getKey()%>: <%=ent.getValue()%> </h3> 
                    <%
                        
                }
                    %> 
        </div>
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>
