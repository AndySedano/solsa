<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="superadmin" activo="">
    <jsp:attribute name="titulo">
        Alta de Administradores
    </jsp:attribute>
    <jsp:body>
        <h2>Alta de Administradores</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">${message}</div>
        </c:if>
        <form class="form-horizontal" method="post" action="AddUser">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="username" name="username" placeholder="max. 20 caracteres">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">Contraseña</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="password" name="password" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y apellidos">
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Dirección</label>
                <div class="col-md-9">
                    <textarea class="form-control" rows="4" id="direccion" name="direccion"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="telefono" class="col-md-3 control-label">Teléfono</label>
                <div class="col-md-9">
                    <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="Incluyendo LADA">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Crear</button>
        </form>
    </jsp:body>
</t:layout>