<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="ventas" activo ="pedidos">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="EstadoPedido">
            <h1>
                Estado de Pedido: 
            </h1>
            <br />

            <p>
                <input type="hidden" value="${requestScope.bean.id}" name="id" id="id">
                id: ${requestScope.bean.id}
                <br />
                Empresa: ${requestScope.bean.empresa}
                <br>
                Fecha de Entrega: ${requestScope.bean.date}
                <br />
                Estado: ${requestScope.bean.estado}
                <br />
                <br />
            </p>
            <p>
            <h4>Elementos Pedidos:</h4>

            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.idProducto}" />
                        </td>
                        <td>
                            <c:out value="${al.nombre}" />
                        </td>
                        <td>
                            <c:out value="${al.descripcion}" />
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <br />
            <h4>Estado del Pedido</h4>
            <input type="radio" name="estado" value="recibido" ${requestScope.bean.estado.equals("recibido") ? "checked" : ""}>&nbsp;Recibido
            <br />
            <input type="radio" name="estado" value="inventario" ${requestScope.bean.estado.equals("inventario") ? "checked" : ""}>&nbsp;En Almacen
            <br />
            <input type="radio" name="estado" value="transito" ${requestScope.bean.estado.equals("transito") ? "checked" : ""}>&nbsp;En Camino
            <br />
            <input type="radio" name="estado" value="entregado" ${requestScope.bean.estado.equals("entregado") ? "checked" : ""}>&nbsp;Entregado
            <br />
            <br />
            <input type="submit" name="cambiarEstado" value="Cambiar">
            </p>
        </form> 
    </jsp:body>
</t:layout>