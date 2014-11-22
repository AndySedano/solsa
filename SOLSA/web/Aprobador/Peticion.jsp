<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="aprobador" activo ="null">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="${pageContext.request.contextPath}/">
            <input type="submit" value=" <-- Volver a Peticiones" />
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
            <input type="submit" name="cambiarEstado" value="Aceptar">
            <input type="submit" name="cambiarEstado" value="Cancelar">
        </p>
    </form> 
</jsp:body>
</t:layout>