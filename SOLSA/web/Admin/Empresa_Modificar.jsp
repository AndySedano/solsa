<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo="empresa_alta">
    <jsp:attribute name="titulo">
        Alta de Empresas
    </jsp:attribute>
    <jsp:attribute name="scripts">
    </jsp:attribute>
    <jsp:body>
        <h2>Modificar Empresa</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <p>
            <a href="Empresa_Buscar">Regresar</a>
        </p>
        <form class="form-horizontal" method="post" action="Empresa_Modificar?id=${empresa.idEmpresa}" enctype="multipart/form-data">
            <input type="hidden" name="idEmpresa" value="${empresa.idEmpresa}">
            
            <div class="form-group">    <label class="col-md-3 control-label" for="nombre">Nombre</label>
            <div class="col-md-9">      <input type="text" class="form-control" id="nombre" name="nombre" placeholder="max. 20 caracteres" value="${empresa.nombre}">
            </div>
            </div>
            
            <div class="form-group">    <label class="col-md-3 control-label" for="direccion">Dirección</label>
            <div class="col-md-9">      <textarea class="form-control" rows="4" id="direccion" name="direccion">${empresa.direccion}</textarea>
            </div>
            </div>
            
            <div class="form-group">    <label class="col-md-3 control-label" for="telefono">Teléfono</label>
            <div class="col-md-9">      <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Incluyendo LADA" value="${empresa.telefono}">
            </div>
            </div>
            
            <div class="form-group">    <label class="col-md-3 control-label" for="rfc">RFC</label>
            <div class="col-md-9">      <input type="text" class="form-control" id="rfc" name="rfc" placeholder="12 o 13 caracteres" value="${empresa.rfc}">
            </div>
            </div>
            
            <div class="form-group">    <label class="col-md-3 control-label" for="foto">Foto</label>
            <div class="col-md-9">
            <div class="input-group">
                <span class="input-group-btn">
                    <span class="btn btn-default btn-file">
                        Examinar…
                        <input type="file" id="foto" name="foto">
                    </span>
                </span>
                <input type="text" class="form-control" readonly value="${empresa.foto.nombre}">
            </div>
            </div>
            </div>
            
            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <div id="site-logo" style="width: 100px; height: 100px; background-image: url('${empresa.foto.url}');"></div>
            </div></div>
            
            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <button type="submit" class="btn btn-primary">Modificar</button>
            </div></div>
        </form>
    </jsp:body>
</t:layout>