<%-- 
    Document   : wish
    Created on : 11-may-2020, 18:26:03
    Author     : zuzu
--%>

<%@page import="ejbs.VST"%>
<%@page import="ejbs.WishList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>Welcome to your Wish List</title>
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>Wish List</h1>
        
        <%
        WishList wishList = (WishList) session.getAttribute("Wish");
        
        for(VST wishListContent: wishList.getProducts()){%>
        <form onSubmit="if(!confirm('Do you really want to remove from your Wish List?')){return false;}">
            <label> <%=wishListContent%> </label>
            <input type="hidden" name="command" value="ShowWishListCommand"></input>
            <input type="hidden" name="vst" value="<%=wishListContent%>">
            <button type="submit" >Remove</button>
        </form>
        <%
        }
        %>
        </div>
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>
