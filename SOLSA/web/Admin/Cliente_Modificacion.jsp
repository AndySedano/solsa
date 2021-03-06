<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:layout seccion="admin" activo ="clientes">
    <jsp:attribute name="titulo">
        Modificaciön de Clientes
    </jsp:attribute>
    <jsp:body>
        <h2>Modificación de Clientes</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="Cliente_Modificar?username=${cliente.username}">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <p class="form-control-static">${cliente.username}</p>
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y Apellidos" value="${cliente.nombre}">
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Direccion</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.direccion}" />
                </div>
            </div>
            <div class="form-group">
                <label for="telefono" class="col-md-3 control-label">Tel&eacute;fono</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="telefono" name="telefono" placeholder="" value="${cliente.telefono}">
                </div>
            </div>
            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <button type="submit" class="btn btn-primary" name="submit" value="modificar">Modificar</button>
            <button type="submit" class="btn btn-danger" name="submit" value="borrar">Borrar</button></div></div>
        </form>
    </jsp:body>
</t:layout>
        