<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:layout seccion="admin" activo ="cliente_modificacion">
    <jsp:attribute name="titulo">
        Modificaciön de Usuarios
    </jsp:attribute>
    <jsp:body>
        <h2>Modificación de Usuarios</h2>
        <p>
            Ingrese el Username en la casilla para desplegar los datos
            a cambiar:
        </p>
        ${requestScope.res}
        <form class="form-horizontal" method="post" action="Cliente_Modificar">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="username" name="username" placeholder="">
                </div>
            </div>
        </form>
            
        <form class="form-horizontal" method="get" action="Cliente_Modificar">
            
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y Apellidos" value="${requestScope.nombre}">
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Direccion</label>
                <div class="col-md-9">
                    <textarea class="form-control" rows="4" id="direccion" name="direccion" value="${requestScope.direccion}"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Tel&eacute;fono</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="direccion" name="direccion" placeholder="" value="${requestScope.telefono}">
                </div>
            </div>
            <button type="submit" class="btn btn-primary" name="submit" value="modficar">Modificar</button>
            <button type="submit" class="btn btn-danger" name="submit" value="borrar">Borrar</button>
        </form>
    </jsp:body>
</t:layout>
        