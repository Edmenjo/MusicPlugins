<%-- 
    Document   : vstmanagement
    Created on : 14-jun-2020, 12:52:43
    Author     : zuzu
--%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="ejbs.ProductFacade"%>
<%@page import="javax.naming.InitialContext"%>
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
        <form onSubmit="if(!confirm('Do you really want to search this plugin?')){return false;}">            
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
            ProductFacade pf = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
            String name = (String) session.getAttribute("findName");
            Integer index = (Integer) session.getAttribute("indextofind");
            
            
            
            List<Product> products;
            if (name != null) {

                products = pf.findByName(name, index);
            } else {
            products = pf.findByNameCriteria(name, index);
            }
        %>
        
       
            
            <h2>Remove by id</h2>
            <form onSubmit="if(!confirm('Do you really want to remove this plugin?')){return false;}">
                <input type="text" name="idDel">
                <input type="hidden" name="command" value="DeleteProductDBCommand">
                <button type="submit" >DEL</button>
            </form>
            
            <h2>Set ID to 100</h2>
            <form onSubmit="if(!confirm('Do you really want to remove this plugin?')){return false;}">
                <input type="text" name="idUpd">
                <input type="hidden" name="command" value="UpdateIdCommand">
                <button type="submit" >UPDATE</button>
            </form>
            
            
             <%
                    for (Product product: products) {

                %>
                <li class="list-group-item group-item">
                    
                    <div class="data">
                        <h5><%=product.getName()%></h5> 
                        <small><%=product.getPrice()%> €</small>

                    </div>
            
            <%
                    }

                %>
                
                <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous"><form action="/MusicPlugins-war/FrontController">
                                <input type="hidden" name="command" value="PreResultsCommand">

                                <button class="page-link" type="submit" style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span></button>
                            </form> 

                        </a>
                    </li>
                    <%
                        Long countByName = (Long) session.getAttribute("indexes");
                        Integer i = 1;
                        while (countByName >= 0) {
                    %>



                    <li class="page-item"><a class="page-link" href="#">
                            <form action="/MusicPlugins-war/FrontController">
                                <input type="hidden" name="command" value="SelectRangeCommand">
                                <input type="hidden" name="npage" value="<%=i%>">
                                <button class="page-link"type="submit"style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><%=i%></button>
                            </form></a></li>


                    <%
                            i++;
                            countByName--;
                        }
                    %>

                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next"><form action="/MusicPlugins-war/FrontController">
                                <input type="hidden" name="command" value="NextResultsCommand">

                                <button class="page-link" type="submit" style="padding: 0;
                                        border: none;
                                        background-color: inherit;"><span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span></button>
                            </form> 

                        </a>
                    </li>
                </ul>
            </nav>
        </div>       
        
        <jsp:include page="resource/footer.jsp" />
    </body>
</html>