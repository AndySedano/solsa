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
                            <c:out value="${al.cantidad}" />
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <br />
            <h4>Estado del Pedido</h4>
            <input type="radio" name="estado" value="recibido" ${bean.estado.equals("recibido") ? "checked" : ""} ${estado > 1 ?"disabled" : "" }>&nbsp;Recibido
            <br />
            <input type="radio" name="estado" value="inventario" ${bean.estado.equals("inventario") ? "checked" : ""} ${estado > 2 ?"disabled" : "" }>&nbsp;En Almacen
            <br />
            <input type="radio" name="estado" value="transito" ${bean.estado.equals("transito") ? "checked" : ""} ${estado > 3 ?"disabled" : "" }>&nbsp;En Camino
            <br />
            <input type="radio" name="estado" value="entregado" ${bean.estado.equals("entregado") ? "checked" : ""} ${estado > 4 ?"disabled" : "" }>&nbsp;Entregado
            <br />
            <br />
            <input type="submit" name="cambiarEstado" value="Cambiar" onclick="uli()">
            </p>
        </form> 
        <script>
            function uli() {
                alert("Se ha modificado el estado del pedido");
            }
        </script>
    </jsp:body>
</t:layout>