<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo="empresas">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>
            Empresas
        </h2>
        <form class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="buscar">Buscar empresa por nombre</label>
                <div class="input-group">
                    <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                    <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar empresa por nombre" value="${buscar}">
                </div>
            </div>
        </form>
        <!--<input type="text" name="busqueda" />

        <select name="loquequieras">
            <option value="1">Empresa</option>
            <option value="2">Estado</option>
        </select>
        <input type="submit" name="botonCool"/>
        <br />
        <br />
-->
        <table class="table table-hover">
            <tr>
                <th>
                    Nombre
                </th>
                <th>
                    Dirección
                </th>
                <th>
                    Teléfono
                </th>
                <th>
                    RFC
                </th>
            </tr>

            <c:forEach items="${empresas}" var="empresa">
                <tr onclick="location.href = 'Empresa_Modificar?id=${empresa.idEmpresa}'">
                    <td>
                        ${empresa.nombre}
                    </td>
                    <td style="white-space: pre-wrap;"><!--
                     -->${empresa.direccion}<!--
                 --></td>
                    <td>
                        ${empresa.telefono}
                    </td>
                    <td>
                        ${empresa.rfc}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>