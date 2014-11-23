<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="aprobador" activo ="null">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="${pageContext.request.contextPath}/">
            <a class=" btn btn-primary btn-sm" href="EstadoPedido?id=${al.id}">
                <-- Volver</a>
            <h1>
               Petición: 
            </h1>
            <br />

            <p>
                id: 
                <br>
                Fecha de Petición:
                <br />
                Estado:
                <br />
                <br />
            </p>
            <p>
            <h4>Elementos Pedidos:</h4>
            
            <table class="table table-hover">
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        Nombre
                    </th>
                    <th>
                        Cantidad
                    </th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.nombre}" />
                        </td>
                        <td>
                            <c:out value="${al.cantidad}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br />
            <h4>Estado del Pedido</h4>
            <input type="submit" name="cambiarEstado" value="Aceptar">
            <input type="submit" name="cambiarEstado" value="Cancelar">
        </p>
    </form> 
</jsp:body>
</t:layout>