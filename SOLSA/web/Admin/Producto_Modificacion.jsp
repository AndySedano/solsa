<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="producto_modificacion">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>Modificar Producto</h2>
            <form class="form-horizontal" method="post" action="Producto_Modificacion">
                
                <div class="form-group">
                    <label for="nombre" class="col-md-3 control-label">Nombre del Producto:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="nombre" placeholder="max. 20 caracteres" />
                    </div>
                </div> 
                
                    <div class="form-group">
                        <label for="textArea" class="col-md-3 control-label">Descripción:</label>
                        <div class="col-lg-9">
                            <textarea id="text" class="form-control" rows="3" placeholder="max. 50 caracteres"></textarea>
                        </div>
                    </div>
                
                <div class="form-group">
                    <label for="precio" class="col-md-3 control-label">Precio:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="precio" placeholder="max. 8 caracteres"/>
                    </div>
                </div>    
                
                <div class="form-group">
                    <label for="reorden" class="col-md-3 control-label">Punto de Reorden:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="reorden" placeholder="max. 10 caracteres"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="tipo" class="col-md-3 control-label">Tipo:</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" id="tipo" placeholder="max. 10 caracteres"/>
                    </div>
                </div> 
                
                <div class="form-group">
                    <label for="foto" class="col-md-3 control-label">Foto:</label>
                    <div class="col-md-9">
                        <input type="file" class="form-control" id="foto" />
                    </div>
                </div>                

            <button type="submit" class="btn btn-success">Modificar</button>
            <button type="submit" class="btn btn-danger">Cancelar</button>
            <button type="submit" class="btn btn-primary">Volver</button>
                
            </form>       
    </jsp:body>
</t:layout>