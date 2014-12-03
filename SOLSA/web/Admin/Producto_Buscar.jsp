<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo="productos">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>
            Productos
        </h2>
        <form class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="buscar">Buscar producto por nombre</label>
                <div class="input-group">
                    <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                    <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar producto por nombre" value="${buscar}">
                </div>
            </div>
        </form>
        <table class="table table-hover">
            <tr>
                <th>
                    Nombre
                </th>
                <th>
                    Descripción
                </th>
                <th>
                    Precio
                </th>
                <th>
                    Punto de reorden
                </th>
            </tr>
            <tr onclick="location.href = 'Producto_Alta'" class="text-primary">
                <td>
                    Nuevo producto...
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

            <c:forEach items="${productos}" var="producto">
                <tr onclick="location.href = 'Producto_Modificar?id=${producto.idProducto}'">
                    <td>
                        ${producto.nombre}
                    </td>
                    <td style="white-space: pre-wrap;"><!--
                     -->${producto.descripcion}<!--
                 --></td>
                    <td style="text-align: right;">
                        <span style="float: left;">$</span>${producto.precio}
                    </td>
                    <td style="text-align: right;">
                        ${producto.puntoDeReorden}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>