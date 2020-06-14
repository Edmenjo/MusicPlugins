<%-- 
    Document   : cart
    Created on : 09-may-2020, 18:25:25
    Author     : zuzu
--%>


<%@page import="ejbs.vstCartLocal"%>
<%@page import="ejbs.VST"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>Cart</title>
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>Cart</h1>
        
        <%
        vstCartLocal cart = (vstCartLocal) session.getAttribute("Cart");
        
        for(VST cartContent: cart.getProducts()){%>
        <h3><%=cartContent%></h3>
        <%
        }
        %>
        </div>
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>
