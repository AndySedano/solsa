<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_modificacion">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>Modificación de Usuarios</h2>
        ${requestScope.res}
        <form class="form-horizontal" method="post" action="Cliente_Modificacion">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="username" name="username" placeholder="max. 20 caracteres">
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y Apellidos">
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Direccion</label>
                <div class="col-md-9">
                    <textarea class="form-control" rows="4" id="direccion" name="direccion"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Tel&eacute;fono</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Incluyendo LADA">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Modificar</button>
            <a href="/" class="btn btn-primary">Continuar</a>
        </form>
    </jsp:body>
</t:layout>
        