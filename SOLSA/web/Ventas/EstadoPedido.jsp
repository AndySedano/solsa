<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="null">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
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
            Elementos Pedidos:
            <br />
        <table>
            
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>Cantidad</th>
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
            
        </p>
        
    </jsp:body>
</t:layout>