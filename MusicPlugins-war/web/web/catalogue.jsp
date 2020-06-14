<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.vstCartLocal"%>
<%@page import="ejbs.Catalogue"%>
<%@page import="ejbs.VST"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>VST Catalogue</title>
        
        
        
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>You've searched for the VST Catalogue</h1>
        
        <%
            vstCartLocal cart = (vstCartLocal) session.getAttribute("Cart");
            Catalogue catalogue;
            catalogue = (Catalogue) session.getAttribute("catalogue");
            if(cart!=null){
                for(VST vstCatalogue: catalogue.getCatalogue()){    
            %>
        <form onSubmit="if(!confirm('Do you really want to add this plugin?')){return false;}">
            <label> <%=vstCatalogue%> </label>
            <input type="hidden" name="command" value="AddToCartCommand"></input>
            <input type="hidden" name="vst" value="<%=vstCatalogue%>">
            <button type="submit" >Add</button>
        </form>    
        <form onSubmit="if(!confirm('Do you really want to add this plugin?')){return false;}">
            <input type="hidden" name="command" value="AddToWishCommand"></input>
            <input type="hidden" name="vst" value="<%=vstCatalogue%>">
            <button type="submit" >Wish</button>
        </form>
            

        <%  
             }  } 
        %>
        <h2>Add midi</h2>
        <form action="/MusicPlugins-war/FrontController">
            <input type="text" name="id" placeholder="id">
            <input type="text" name="name" placeholder="name">
            <input type="text" name="description" placeholder="description">
            <input type="text" name="price" placeholder="price">
            <input type="text" name="rate" placeholder="rate">
            <input type="hidden" name="command" value="AddMidiToDBCommand">
            <button type="submit" >ADD</button>
        </form>
        <%
              

            %>
           
            
            <h2>Search by name</h2>
            <form action="/MusicPlugins-war/FrontController">
                <input type="text" name="nameSearch">
                <input type="hidden" name="command" value="SearchDBByNameCommand">
                <button type="submit" >Search</button>
            </form>
            
            <%
                String nameSearch = (String) session.getAttribute("nameSearch");
                
                if(nameSearch != null){%>          
            <%=nameSearch%>
                
                <% } %>
            
            
            
            <h2>Remove by id</h2>
            <form action="/MusicPlugins-war/FrontController">
                <input type="text" name="idDel">
                <input type="hidden" name="command" value="DeleteMidiDBCommand">
                <button type="submit" >DEL</button>
            </form>
            
            <%
            %>
            
        </div>       
        
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>