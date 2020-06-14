<%-- 
    Document   : prueba
    Created on : 21-mar-2020, 20:05:33
    Author     : zuzu
--%>

<%@page import="ejbs.VST"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejbs.Counter"%>
<%@page import="ejbs.vstCartLocal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="/MusicPlugins-war/web/resource/main.css"/>
        <title>VST plugins</title>
        
        
    </head>
    <body>
        <jsp:include page="resource/header.jsp" />
        <div class="forms">
        <h1>You've searched for a VST Plugin</h1>
        <h2>If you know a VST Plugin name, just search for it</h2>
        
        <form action ="/MusicPlugins-war/FrontController">
            <input type="text" name="product">
            <input type="hidden" name="command" value="SearchCommand">
            <input type="submit" value='Search'>
        </form>
        
        
        <% 
            VST vst1;
            
            
            vst1 = (VST) session.getAttribute("vst");
            
            vstCartLocal cart = (vstCartLocal) session.getAttribute("Cart");
            //if(cart != null){
            if(vst1!=null){
                
            %>
           
            
                
                    <form onSubmit="if(!confirm('Do you really want to add this plugin?')){return false;}">
                        <label> <%=vst1%> </label>
                        <input type="hidden" name="command" value="VstCommand"></input>
                        
                        <button type="submit" >Add</button>
                        
                        <button type="submit" >Wish</button>
                        <input type="hidden" name="command" value="VstCommand"></input>
                    </form>

            <%    
                
            } else {
                %><label>Can't find that plugin</label><%
}
            
            %>
        
            
        </div>
        
        
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>
