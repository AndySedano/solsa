<%-- 
    Document   : example
    Created on : 11/11/2014, 10:24:57 PM
    Author     : Arturo
--%>

<%@ tag description="Diseño base del sitio de Creatividad a Color" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" fragment="true" %>
<%@ attribute name="seccion" type="java.lang.String" %>

<%@ attribute name="urlimagen" fragment="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8' />
		<title>Creatividad a Color - <jsp:invoke fragment="titulo" /></title>
		<link rel="stylesheet" href="style.css" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
	</head>
	<body>
		<header>
			<img id="logo" src="logo.jpg" />
		</header>
		<nav>
			<c:choose>
				<c:when test="${seccion == 'inicio'}">
					<ul class="nav"><!--
					---><li class="red active"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				<c:when test="${seccion == 'clientes'}">
					<ul class="nav"><!--
					---><li class="red"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow active"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				<c:when test="${seccion == 'proveedores'}">
					<ul class="nav"><!--
					---><li class="red"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green active"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				<c:when test="${seccion == 'cotizaciones'}">
					<ul class="nav"><!--
					---><li class="red"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan active"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				<c:when test="${seccion == 'ventas'}">
					<ul class="nav"><!--
					---><li class="red"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue active"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				<c:when test="${seccion == 'productos'}">
					<ul class="nav"><!--
					---><li class="red"><a href="Inicio">Inicio</a></li><!--
					---><li class="yellow"><a href="IndexClientes">Clientes</a></li><!--
					---><li class="green"><a href="IndexProveedores">Proveedores</a></li><!--
					---><li class="cyan"><a href="IndexCotizacion">Cotizaciones</a></li><!--
					---><li class="blue"><a href="IndexVentas">Ventas</a></li><!--
					---><li class="purple active"><a href="IndexProductos">Productos</a></li><!--
					---><li class="red" style="float: right;"><a href="Logout">Cerrar sesión</a></li></ul>
				</c:when>
				
				</c:choose>
		</nav>
		<article>
			<h1><jsp:invoke fragment="titulo" /></h1>
			<jsp:doBody />
		</article>
	</body>
</html>
