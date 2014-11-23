<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="ventas" activo ="null">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="${pageContext.request.contextPath}/">
            <h1>
                Estado de Pedido: 
            </h1>
            <br />

            <p>
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
<<<<<<< HEAD
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.idProducto}" />
                            &nbsp;
                            &nbsp;
=======
                            <c:out value="${al.id}" />
>>>>>>> origin/master
                        </td>
                        <td>
                            <c:out value="${al.nombre}" />
                        </td>
                        <td>
<<<<<<< HEAD
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.descripcion}" />
                            &nbsp;
                            &nbsp;
=======
                            <c:out value="${al.cantidad}" />
>>>>>>> origin/master
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <br />
            <h4>Estado del Pedido</h4>
            <input type="radio" name="estado">&nbsp;Recibido
            <br />
            <input type="radio" name="estado">&nbsp;En Almacen
            <br />
            <input type="radio" name="estado">&nbsp;En Camino
            <br />
            <input type="radio" name="estado">&nbsp;Entregado
            <br />
            <br />
            <input type="submit" name="cambiarEstado" value="Cambiar Estado">
            </p>
        </form> 
    </jsp:body>
</t:layout>