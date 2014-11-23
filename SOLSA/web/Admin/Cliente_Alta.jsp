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
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="Cliente_Alta">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="username" name="username" placeholder="max. 20 caracteres"/>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-md-3 control-label">Contraseña</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="password" name="password" placeholder=""/>
                </div>
            </div>

            <div class="form-group">
                <label for="passwordagain" class="col-md-3 control-label">Confirmar contraseña</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="passwordagain" name="passwordagain" placeholder=""/>
                </div>
            </div>

            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder=""/>
                </div>
            </div> 

                <div class="form-group">
                    <label for="direccion" class="col-md-3 control-label">Dirección</label>
                    <div class="col-lg-9">
                        <textarea id="direccion" class="form-control" rows="3" name="direccion" placeholder=""></textarea>
                    </div>
                </div>

            <div class="form-group">
                <label for="telefono" class="col-md-3 control-label">Teléfono</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Incluyendo LADA"/>
                </div>
            </div>    

            <div class="form-group">
                <label for="idDepartamento" class="col-md-3 control-label">Departamento</label>
                <div class="col-md-9">
                    <select class="form-control" id="idDepartamento" name="idDepartamento" data-placeholder="Selecciona el departamento de una empresa">
                        <option></option>
                        <c:forEach var="empresa" items="${empresas}">
                            <optgroup label="${empresa.key}">
                                <c:forEach var="departamento" items="${empresa.value}">
                                    <option value="${departamento.idDepartamento}">${departamento.nombreDepartamento}</option>
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                </div>
            </div>      

            <div class="form-group"><div class="col-md-offset-3 col-md-9">
            <button type="submit" class="btn btn-primary">Crear</button>
            </div></div>
        </form>
     </jsp:body>
</t:layout>
        
        
        
