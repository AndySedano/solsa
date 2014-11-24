<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo="departamentos">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h2>
            Departamentos
        </h2>
        <form class="form-inline" role="form">
            <div class="form-group">
                <label class="sr-only" for="buscar">Buscar por departamento o empresa</label>
                <div class="input-group">
                    <div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
                    <input type="search" class="form-control" id="buscar" name="buscar" placeholder="Buscar por departamento o empresa" value="${buscar}">
                </div>
            </div>
        </form>
        <table class="table table-hover">
            <tr>
                <th>
                    Empresa
                </th>
                <th>
                    Departamento
                </th>
                <th>
                    Productos
                </th>
            </tr>
            <tr onclick="location.href = 'Departamento_Alta'" class="text-primary">
                <td>
                    Nuevo departamento...
                </td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${empresas}" var="empresa">
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${empresa.value}" var="departamento">
                    <tr onclick="location.href = 'Departamento_Modificar?id=${departamento.idDepartamento}'">
                        <td>
                            <c:if test="${count == 0}">
                                ${departamento.nombreEmpresa}
                            </c:if>
                            <c:if test="${count != 0}">
                                <span class="text-muted">${departamento.nombreEmpresa}</span>
                            </c:if>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                        </td>
                        <td>
                            ${departamento.nombreDepartamento}
                        </td>
                        <td>
                            ${departamento.numProductos}
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </jsp:body>
</t:layout>