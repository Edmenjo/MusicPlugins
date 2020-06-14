<%-- 
    Document   : header
    Created on : 28-mar-2020, 19:53:00
    Author     : zuzu
--%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



                <div class="header">
                    <nav>
                        <ul>
                            <li><a href="web/main.jsp">Home</a></li>
                            <li><a href="web/vst.jsp">VST Searcher</a></li>
                            <li><a href="web/catalogue.jsp">Catalogue</a></li>
                            <div class="cart">
                            <form action ="/MusicPlugins-war/FrontController">
                                <input type="hidden" name="command" value="ShowCartCommand">
                                <button style="font-size:24px"><i class="fa fa-shopping-cart"></i></button>
                            </form>
                            <form action ="/MusicPlugins-war/FrontController">
                                <input type="hidden" name="command" value="ShowWishListCommand">
                                <button class="fa fa-heart" style="font-size:26px;color:orange"></button>
                            </form>
                            
                                
                            </div>
                        </ul>
                        
                        
                        
                         
                    </nav>
                </div>
            </header>  


