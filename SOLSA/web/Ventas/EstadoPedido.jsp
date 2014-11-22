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
                id: 
                <br />
                Empresa:
                <br>
                Fecha de Entrega:
                <br />
                Estado:
                <br />
                <br />
            </p>
            <p>
            <h4>Elementos Pedidos:</h4>
            
            <table>

                <tr>
                    <th>
                        &nbsp;
                        &nbsp;
                        id
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Nombre
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Cantidad
                        &nbsp;
                        &nbsp;
                    </th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.id}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.nombre}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.cantidad}" />
                            &nbsp;
                            &nbsp;
                        </td>
                    </tr>
                </c:forEach>

            </table>
            
            <br />
            <h4>Estado del Pedido</h4>
            <input type="radio">&nbsp;Recibido
            <br />
            <input type="radio">&nbsp;En Almacen
            <br />
            <input type="radio">&nbsp;En Camino
            <br />
            <input type="radio">&nbsp;Entregado
            <br />
            <br />
            <input type="submit" name="cambiarEstado" value="Cambiar Estado">
        </p>
    </form> 
</jsp:body>
</t:layout>