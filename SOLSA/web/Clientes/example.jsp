<%-- 
    Document   : example
    Created on : 11/11/2014, 10:27:21 PM
    Author     : Arturo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:example seccion="clientes">
    <jsp:attribute name="titulo">
		Nuevo Cliente
    </jsp:attribute>
    <jsp:body>
		<c:if test="${not empty requestScope.error}">
			<div style="position: absolute; top: 180px; left: 0px; right: 0px; text-align: center; color: red;">${error}</div>
		</c:if>
    	<form method="POST" action="./InsertarClientes">
    	<table>
			<tbody>
				<tr>
					<td>Nombre</td>
					<td><input type="text" name="nombre" style="width: 15em;" required /></td>

				</tr>
				<tr>
					<td>RFC</td>
					<td><input type="text" name="rfc" style="width: 15em;" /></td>

				</tr>
				<tr>
					<td>Teléfono</td>
					<td><input type="tel" name="telefono" style="width: 15em;" /></td>
				</tr>
				<tr>
					<td>Correo electrónico</td>
					<td><input type="email" name="correo" style="width: 15em;" /></td>
				</tr>
				<tr>
					<td>Dirección</td>
					<td><textarea name="direccion" cols="20" rows="5" style="width: 15em; resize: none;"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="Agregar"></td>
					<td><input type="reset" value="Limpiar registro" /></td>
				</tr>
			</tbody>
		</table>
		</form>
    </jsp:body>
</t:example>
