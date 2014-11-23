<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="producto_alta">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>Alta de Productos</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="Producto_Alta" enctype="multipart/form-data">
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre del Producto:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="max. 20 caracteres" />
                </div>
            </div> 

            <div class="form-group">
                <label for="descripcion" class="col-md-3 control-label">Descripción:</label>
                <div class="col-md-9">
                    <textarea id="text" class="form-control" name="descripcion" id="descripcion" rows="3" placeholder=""></textarea>
                </div>
            </div>

            <div class="form-group">
                <label for="precio" class="col-md-3 control-label">Precio:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <span class="input-group-addon">$</span>
                        <input type="number" class="form-control" name="precio" id="precio" placeholder="00.00" min="0" step="0.01">
                    </div>
                </div>
            </div>    

            <div class="form-group">
                <label for="puntoDeReorden" class="col-md-3 control-label">Punto de Reorden:</label>
                <div class="col-md-9">
                    <input type="number" class="form-control" name="puntoDeReorden" id="puntoDeReorden" placeholder="1000"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="foto" class="col-md-3 control-label">Foto:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <span class="input-group-btn">
                            <span class="btn btn-default btn-file">
                                Examinar…
                                <input type="file" id="foto" name="foto">
                            </span>
                        </span>
                        <input type="text" class="form-control" readonly>
                    </div>
                </div>
            </div>
            
            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <button type="submit" class="btn btn-primary">Crear</button>
            </div></div>            
        </form>    
    </jsp:body>
</t:layout>