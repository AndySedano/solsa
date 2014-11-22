<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_alta">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>  
        <h2>Alta de Clientes</h2>
            <form class="form-horizontal" method="post" action="ClienteAlta">
                <div class="form-group">
                    <label for="usuario" class="col-md-3 control-label">Usuario:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="usuario" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="contraseña" class="col-md-3 control-label">Contraseña:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="contraseña" placeholder="max. 20 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="nombre" class="col-md-3 control-label">Nombre:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="nombre" placeholder="max. 20 caracteres"/>
                    </div>
                </div> 
                
                    <div class="form-group">
                        <label for="textArea" class="col-md-3 control-label">Dirección:</label>
                        <div class="col-lg-9">
                            <textarea type="text" class="form-control" rows="3" id="textArea" placeholder="max. 50 caracteres"></textarea>
                        </div>
                    </div>
                
                <div class="form-group">
                    <label for="telefono" class="col-md-3 control-label">Telefono:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="telefono" placeholder="max. 8 caracteres"/>
                    </div>
                </div>    
                
                <div class="form-group">
                    <label for="tipo" class="col-md-3 control-label">Tipo:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="tipo" placeholder="max. 10 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="departamento" class="col-md-3 control-label">Departamento (id):</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" if="departamento" placeholder="max. 10 caracteres"/>
                    </div>
                </div>                
            </form>
    </jsp:body>
</t:layout>
        
        
        
