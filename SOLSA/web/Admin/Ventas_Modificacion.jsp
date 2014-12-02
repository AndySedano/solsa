<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:layout seccion="admin" activo ="ventas">
    <jsp:attribute name="titulo">
        Modificaciön de Empleados de Ventas
    </jsp:attribute>
    <jsp:body>
        <h2>Modificación de Empleados de Ventas</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="Ventas_Modificar?username=${venta.username}">
            <input type="hidden" name="username" value="${venta.username}">

            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <p class="form-control-static">${venta.username}</p>
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y Apellidos" value="${venta.nombre}">
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Direccion</label>
                <div class="col-md-9">
                    <textarea class="form-control" id="direccion" name="direccion" placeholder="">${venta.direccion}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="telefono" class="col-md-3 control-label">Tel&eacute;fono</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="direccion" name="telefono" placeholder="" value="${venta.telefono}">
                </div>
            </div>
            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <button type="submit" class="btn btn-primary" name="submit" value="modificar">Modificar</button>
            <button type="submit" class="btn btn-danger" name="submit" value="borrar">Borrar</button></div></div>
        </form>
    </jsp:body>
</t:layout>
        