<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="clientes">
    <jsp:attribute name="titulo">
        Búsqueda de Cliente
    </jsp:attribute>
    <jsp:body>
        <form>
            <h2>Clientes</h2>
            
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="buscar">Buscar cliente por usuario</label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                        <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar cliente por usuario" value="${buscar}">
                    </div>
                </div>
            </form>
            <table class="table table-hover">
            <tr>
                <th>
                    Username
                </th>
                <th>
                    Nombre
                </th>
                <th>
                    Empresa
                </th>
                <th>
                    Departamento
                </th>
            </tr>
            <tr onclick="location.href = 'Cliente_Alta'" class="text-primary">
                <td>
                    Registrar Cliente...
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

            <c:forEach items="${clientes}" var="cliente">
                <tr onclick="location.href = 'Cliente_Modificar?username=${cliente.username}'">
                    <td>
                        ${cliente.username}
                    </td>
                    <td>
                        ${cliente.nombre}
                    </td>
                    <td>
                        ${cliente.nombreEmpresa}
                    </td>
                    <td>
                        ${cliente.nombreDepartamento}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>