<%-- 
    Document   : vstmanagement
    Created on : 14-jun-2020, 12:52:43
    Author     : zuzu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>VST Management</title>
        
        
        
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>Welcome to VST Management</h1>
        
        <h2>Add product</h2>
        <form onSubmit="if(!confirm('Do you really want to add this plugin?')){return false;}">
            <input type="text" name="id" placeholder="id">
            <input type="text" name="name" placeholder="name">
            <input type="text" name="description" placeholder="description">
            <input type="text" name="price" placeholder="price">
            <input type="hidden" name="command" value="AddProductToDBCommand">
            <button type="submit" >ADD</button>
        </form>
    
            <h2>Search by name</h2>
            <form onSubmit="if(!confirm('Do you really want to search this plugin?')){return false;}">
                <input type="text" name="nameSearch">
                <input type="hidden" name="command" value="PSearchDBByNameCommand">
                <button type="submit" >Search</button>
            </form>
            
            <%
                String nameSearch = (String) session.getAttribute("nameSearch");
                
                if(nameSearch != null){%>          
            <%=nameSearch%>
                
                <% } %>
            
            
            
            <h2>Remove by id</h2>
            <form onSubmit="if(!confirm('Do you really want to remove this plugin?')){return false;}">
                <input type="text" name="idDel">
                <input type="hidden" name="command" value="DeleteProductDBCommand">
                <button type="submit" >DEL</button>
            </form>
            
        </div>       
        
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>