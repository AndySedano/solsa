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
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="solsa20.caafufvdj2xl.us-west-2.rds.amazonaws.com/solsa2020" user="solsa2020" password="solsa2020" />
        <sql:query dataSource="${db}" sql="SELECT idDepartamento, nombre FROM Departamento;" var="result">
        </sql:query>
        <form class="form-horizontal" method="post" action="Cliente_Modificar">
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
            <button type="submit" class="btn btn-success">Modificar</button>
            <button type="submit" class="btn btn-danger">Borrar</button>
            <a href="Inicio" class="btn btn-primary">Continuar</a>
        </form>
    </jsp:body>
</t:layout>
        