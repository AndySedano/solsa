<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="departamentos">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>Modificar Departamento</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="Departamento_Modificar?id=${departamento.idDepartamento}">
            <input type="hidden" name="idDepartamento" value="${departamento.idDepartamento}">
            
            <div class="form-group">    <label class="col-md-3 control-label" for="nombre">Nombre</label>
                <div class="col-md-9">      <input type="text" class="form-control" id="nombre" name="nombre" placeholder="" value="${departamento.nombreDepartamento}">
                </div>
            </div>

            <div class="form-group">    <label class="col-md-3 control-label" for="idEmpresa">Empresa</label>
                <div class="col-md-9">      <select class="form-control" id="idEmpresa" name="idEmpresa" data-placeholder="Selecciona una empresa">
                        <option></option>
                        <c:forEach var="empresa" items="${empresas}">
                            <c:if test="${empresa.idEmpresa == departamento.idEmpresa}">
                                <option value="${empresa.idEmpresa}" selected>${empresa.nombre}</option>
                            </c:if>
                            <c:if test="${empresa.idEmpresa != departamento.idEmpresa}">
                                <option value="${empresa.idEmpresa}">${empresa.nombre}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">    <label class="col-md-3 control-label" for="idProductos">Productos</label>
                <div class="col-md-9">      <select class="form-control" id="idProductos" name="idProductos" data-placeholder="Selecciona uno o más productos" multiple>
                        <option></option>
                        <c:forEach var="producto" items="${productos}">
                            <c:if test="${producto.idDepartamento == departamento.idDepartamento}">
                                <option value="${producto.idProducto}" selected>${producto.nombre}</option>
                            </c:if>
                            <c:if test="${producto.idDepartamento != departamento.idDepartamento}">
                                <option value="${producto.idProducto}">${producto.nombre}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group"><div class="col-md-offset-3 col-md-9">
                    <button type="submit" class="btn btn-primary">Modificar</button>
                    <a href="Departamento_Buscar" class="btn btn-default">Regresar</a>
                </div></div>
        </form>
    </jsp:body>
</t:layout>