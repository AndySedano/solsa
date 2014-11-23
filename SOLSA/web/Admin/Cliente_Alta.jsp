<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout seccion="admin" activo ="cliente_alta">
    <jsp:attribute name="titulo">
        Alta de Clientes
    </jsp:attribute>
    <jsp:body>  
        <h2>Alta de Clientes</h2>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="solsa20.caafufvdj2xl.us-west-2.rds.amazonaws.com:3306/solsa2020" user="solsa2020" password="solsa2020" />
        <sql:query dataSource="${db}" var="result">SELECT idDepartamento, nombre FROM Departamento;
        </sql:query>
        ${requestScope.res}
            <form class="form-horizontal" method="post" action="Cliente_Alta">
                <div class="form-group">
                    <label for="usuario" class="col-md-3 control-label">Usuario:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="usuario" name="username" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="contraseña" class="col-md-3 control-label">Contraseña:</label>
                    <div class="col-md-9">
                        <input type="password" class="form-control" id="contraseña" name="password" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="nombre" class="col-md-3 control-label">Nombre:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="max. 20 caracteres"/>
                    </div>
                </div> 
                
                    <div class="form-group">
                        <label for="textArea" class="col-md-3 control-label">Dirección:</label>
                        <div class="col-lg-9">
                            <textarea id="text" class="form-control" rows="3" name="direccion" placeholder="max. 50 caracteres"></textarea>
                        </div>
                    </div>
                
                <div class="form-group">
                    <label for="telefono" class="col-md-3 control-label">Telefono:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="max. 8 caracteres"/>
                    </div>
                </div>    
                
                <div class="form-group">
                    <label for="departamento" class="col-md-3 control-label">Departamento (id):</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="departamento" name="idDepartamento" placeholder="max. 10 caracteres"/>
                    </div>
                </div>      
                
            <button type="submit" class="btn btn-success">Aceptar</button>
            <button type="submit" class="btn btn-danger">Cancelar</button>
            <button type="submit" class="btn btn-primary">Volver</button>                
                
            </form>
    </jsp:body>
</t:layout>
        
        
        
