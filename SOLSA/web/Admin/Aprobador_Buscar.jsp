<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="aprobadores">
    <jsp:attribute name="titulo">
        Búsqueda de Aprobadores
    </jsp:attribute>
    <jsp:body>
        <form>
            <h2>Aprobadores</h2>
            
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="buscar">Buscar aprobadores por usuario </label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                        <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar aprobadores por usuario" value="${buscar}">
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
            <tr onclick="location.href = 'Aprobador_Alta'" class="text-primary">
                <td>
                    Registrar Aprobador...
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

            <c:forEach items="${aprobadores}" var="aprobador">
                <tr onclick="location.href ='Aprobador_Modificar?username=${aprobador.username}'">
                    <td>
                        ${aprobador.username}
                    </td>
                    <td>
                        ${aprobador.nombre}
                    </td>
                    <td>
                        ${aprobador.direccion}
                    </td>
                    <td>
                        ${aprobador.telefono}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>