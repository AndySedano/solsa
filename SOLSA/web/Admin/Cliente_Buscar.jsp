<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_buscar">
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
                        <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar empresa por nombre" value="${buscar}">
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
                    Departmento
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
                <tr onclick="location.href = 'Cliente_Modificacion?id=${cliente.username}'">
                    <td>
                        ${cliente.username}
                    </td>
                    <td style="white-space: pre-wrap;"><!--
                     -->${cliente.nombre}<!--
                 --></td>
                    <td>
                        ${cliente.Empresa}
                    </td>
                    <td>
                        ${cliente.Departamento}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>