<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout seccion="admin" activo ="ventas_alta">
    <jsp:attribute name="titulo">
        Alta de Ventas
    </jsp:attribute>
    <jsp:body>
        <h2>Alta de Ventas</h2>
        <sql:setDataSource var="db" driver="com.mysql.jdbc,Driver" url="solsa20.caafufvdj2xl.us-west-2.rds.amazonaws.com:3306/solsa2020" user="solsa2020" password="solsa2020" />
        <sql:query dataSource="${db}" var="result">SELECT idDepartamento, nombre FROM Departamento;
        </sql:query>
        <form class="form-horizontal" method="post" action="Ventas_Alta">
                <div class="form-group">
                    <label for="usuario" class="col-md-3 control-label">Username:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="username" name="username" placeholder="max. 20 caracteres" required="required"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="contraseña" class="col-md-3 control-label">Contraseña:</label>
                    <div class="col-md-9">
                        <input type="password" class="form-control" id="password" name="password" placeholder="" required="required"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="nombre" class="col-md-3 control-label">Nombre:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="" required="required"/>
                    </div>
                </div> 
                
                    <div class="form-group">
                        <label for="textArea" class="col-md-3 control-label">Dirección:</label>
                        <div class="col-lg-9">
                            <textarea id="direccion" class="form-control" rows="4" name="direccion" placeholder="" required="required"></textarea>
                        </div>
                    </div>
                
                <div class="form-group">
                    <label for="telefono" class="col-md-3 control-label">Telefono:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Incluyendo LADA" required="required"/>
                    </div>
                </div>    
                
                <div class="form-group">
                    <label for="departamento" class="col-md-3 control-label">Departamento (id):</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="idDepartamento" name="idDepartamento" placeholder="" required="required"/>
                    </div>
                </div>      
                
            <button type="submit" class="btn btn-success">Crear</button>
            <button type="reset" class="btn btn-danger">Cancelar</button>
            <a href="Inicio" class="btn btn-primary">Continuar</a>                
                
            </form>
    </jsp:body>
</t:layout>
