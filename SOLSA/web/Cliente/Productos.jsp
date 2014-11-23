<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="cliente" activo ="productos">
    <jsp:attribute name="titulo">
        Productos
    </jsp:attribute>
    <jsp:body>
        <form>
            
            <h1>Catálogo de Productos</h1>
            
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>Empresa</th>
                    <th>Fecha de Entrega</th>
                    <th>Estado</th>
                    <th>Seleccionar</th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.empresa}" />
                        </td>
                        <td>
                            <c:out value="${al.date}" />
                        </td>
                        <td>
                            <c:out value="${al.estado}" />
                        </td>
                        <td>
                            <a class=" btn btn-primary btn-sm" 
                               href="EstadoPedido?id=${al.id}">Ver</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            
        </form>
    </jsp:body>
</t:layout>