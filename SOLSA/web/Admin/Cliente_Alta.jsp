<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_alta">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>  
        <h2>Alta de Clientes</h2>
            <form class="form-horizontal" method="post" action="Cliente_Alta">
                <div class="form-group">
                    <label for="usuario" class="col-md-3 control-label">Usuario:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="usuario" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="contraseña" class="col-md-3 control-label">Contraseña:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="contraseña" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="nombre" class="col-md-3 control-label">Nombre:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="nombre" placeholder="max. 20 caracteres"/>
                    </div>
                </div> 
                
                    <div class="form-group">
                        <label for="textArea" class="col-md-3 control-label">Dirección:</label>
                        <div class="col-lg-9">
                            <textarea id="text" class="form-control" rows="3" placeholder="max. 50 caracteres"></textarea>
                        </div>
                    </div>
                
                <div class="form-group">
                    <label for="telefono" class="col-md-3 control-label">Telefono:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="telefono" placeholder="max. 8 caracteres"/>
                    </div>
                </div>    
                
                <div class="form-group">
                    <label for="tipo" class="col-md-3 control-label">Tipo:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="tipo" placeholder="max. 10 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="departamento" class="col-md-3 control-label">Departamento (id):</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="departamento" placeholder="max. 10 caracteres"/>
                    </div>
                </div>      
                
            <button type="submit" class="btn btn-success">Aceptar</button>
            <button type="submit" class="btn btn-danger">Cancelar</button>
            <button type="submit" class="btn btn-primary">Volver</button>                
                
            </form>
    </jsp:body>
</t:layout>
        
        
        
