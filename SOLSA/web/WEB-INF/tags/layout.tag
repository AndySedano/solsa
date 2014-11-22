

<%@ tag description="Diseño base del sitio de SOLSA" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" fragment="true" %>
<%@ attribute name="scripts" fragment="true" %>
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

                        <li><a href="<%  out.print(request.getContextPath()); %>/Logout">Logout</a></li>

                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <c:choose>
                        <c:when test="${seccion == 'cliente'}">
                            <ul class="nav nav-sidebar">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="Productos">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="Reporte">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="Peticiones">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="Carrito">Carrito</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${seccion == 'admin'}">
                            <ul class="nav nav-sidebar">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("empresa_alta") ? "active" : "none"}><a href="Empresa_Alta">Alta Empresa</a></li>
                                <li class=${activo.equals("empresa_modificacion") ? "active" : "none"}><a href="Empresa_Modificar">Modificar Empresa</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("producto_alta") ? "active" : "none"}><a href="Producto_Alta">Alta Producto</a></li>
                                <li class=${activo.equals("producto_modificacion") ? "active" : "none"}><a href="Producto_Modificar">Modificar Producto</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("ventas_alta") ? "active" : "none"}><a href="Ventas_Alta">Alta Empleado Ventas</a></li>
                                <li class=${activo.equals("ventas_modificacion") ? "active" : "none"}><a href="Ventas_Modificar">Modificacion Empleado Ventas</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("cliente_alta") ? "active" : "none"}><a href="Cliente_Alta">Alta Cliente</a></li>
                                <li class=${activo.equals("cliente_Modificacion") ? "active" : "none"}><a href="Cliente_Modificar">Modificacion Clientee</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("departamento_alta") ? "active" : "none"}><a href="Departamento_Alta">Alta Departamento</a></li>
                                <li class=${activo.equals("departamento_Modificacion") ? "active" : "none"}><a href="Departamento_Modificar">Modificacion Departamento</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("buscar") ? "active" : "none"}><a href="Buscar">Buscar</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="Reporte">Reporte</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${seccion == 'aprobador'}">
                            <ul class="nav nav-sidebar">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="Peticiones">Peticiones</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="Reporte">Reporte</a></li>
                            </ul> 
                        </c:when>
                        <c:when test="${seccion == 'ventas'}">
                            <ul class="nav nav-sidebar">
                                <img src="../CSS/Solsa1.png" height="100" width="200" >
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("buscar") ? "active" : "none"}><a href="Buscar">Buscar</a></li>
                                <li class=${activo.equals("pedidos") ? "active" : "none"}><a href="Pedidos">Pedidos</a></li>    
                            </ul> 
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <jsp:doBody />
                </div>
            </div>
        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <jsp:invoke fragment="scripts" />
    </body>
</html>
