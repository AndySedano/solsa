

<%@ tag description="Diseño base del sitio de SOLSA" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" fragment="true" %>
<%@ attribute name="seccion" type="java.lang.String" %>
<%@ attribute name="activo" type="java.lang.String" %>

<%@ attribute name="urlimagen" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>SOLSA - <jsp:invoke fragment="titulo" /></title>
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
              rel="stylesheet">
        <link href="../CSS/layout.css" rel="stylesheet">

    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand">SOLSA</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="./Logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <c:choose>
                            <c:when test="${seccion == 'clientes'}">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                                </br>
                                </br>
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="../Clientes/Productos.jsp">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="../Clientes/Reporte.jsp">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="../Clientes/Peticiones.jsp">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="../Clientes/Carrito.jsp">Carrito</a></li>
                                </c:when>
                                <c:when test="${seccion == 'admin'}">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                                </br>
                                </br>
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="../Clientes/Productos.jsp">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="../Clientes/Reporte.jsp">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="../Clientes/Peticiones.jsp">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="../Clientes/Carrito.jsp">Carrito</a></li>
                                </c:when>
                                <c:when test="${seccion == 'aprobador'}">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                                </br>
                                </br>
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="../Clientes/Productos.jsp">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="../Clientes/Reporte.jsp">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="../Clientes/Peticiones.jsp">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="../Clientes/Carrito.jsp">Carrito</a></li>
                                </c:when>
                                <c:when test="${seccion == 'ventas'}">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                                </br>
                                </br>
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="../Clientes/Productos.jsp">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="../Clientes/Reporte.jsp">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="../Clientes/Peticiones.jsp">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="../Clientes/Carrito.jsp">Carrito</a></li>
                                </c:when>
                            </c:choose>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <jsp:doBody />
                </div>
            </div>
        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    </body>
</html>
