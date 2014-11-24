<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="ventas_buscar">
    <jsp:attribute name="titulo">
        Búsqueda de Personal de Ventas
    </jsp:attribute>
    <jsp:body>
        <form>
            <h2>Personal de Ventas</h2>
            
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="buscar">Buscar personal de ventas por usuario </label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                        <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar personal de ventas por nombre" value="${buscar}">
                    </div>
                </div>
            </form>
            <table id="table" class="table table-hover">
            <tr>
                <th>
                    Username
                </th>
                <th>
                    Nombre
                </th>
                <th>
                    Direccion
                </th>
                <th>
                    Telefono
                </th>
            </tr>
            <tr onclick="location.href = 'Ventas_Alta'" class="text-primary">
                <td>
                    Registrar Personal de Ventas...
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

            <c:forEach items="${ventas}" var="venta">
                <tr onclick="location.href = 'Ventas_Modificacion?id=${venta.username}'">
                    <td>
                        ${venta.username}
                    </td>
                    <td>
                        ${venta.nombre}
                    </td>
                    <td>
                        ${venta.direccion}
                    </td>
                    <td>
                        ${venta.telefono}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>