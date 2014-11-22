<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="empresa_modificacion">
    <jsp:attribute name="titulo">
        Productos
    </jsp:attribute>
    <jsp:body>
        <h2>Modificar Empresa</h2>
            <form class="form-horizontal" method="post" action="Empresa_Modificacion">
                
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
                    <label for="rfc" class="col-md-3 control-label">RFC:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="rfc" placeholder="max. 10 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="contrato" class="col-md-3 control-label">Contrato (id):</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="contrato" placeholder="max. 10 caracteres"/>
                    </div>
                </div> 

            <button type="submit" class="btn btn-success">Modificar</button>
            <button type="submit" class="btn btn-danger">Cancelar</button>
            <button type="submit" class="btn btn-primary">Volver</button>
            
                
            </form>      
    </jsp:body>
</t:layout>