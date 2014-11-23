<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="ventas" activo ="pedidos">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="Pedidos">
            <h1>
                Pedidos
            </h1>
            <br/>
            <br/>
            <p>
                Para buscar un pedido ingrese en el campo de texto y 
                seleccione el criterio de búsqueda correspondiuente.
            </p>
            <input type="text" name="busqueda" />

            <select name="loquequieras">
                <option value="1">Empresa</option>
                <option value="2">Estado</option>
            </select>
            <input type="submit" name="botonCool"/>
            <br />
            <br />

            <table class="table table-hover"border="1px">
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        Empresa
                    </th>
                    <th>
                        Fecha de Entrega
                    </th>
                    <th>
                        Estado
                    </th>
                    <th>
                        Seleccionar
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
                            <c:out value="${al.empresa}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.date}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.estado}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <a class=" btn btn-primary btn-sm" href="EstadoPedido?id=${al.id}">Ver</a>
                            &nbsp;
                            &nbsp;
                        </td>
                    </tr>
                </c:forEach>

            </table>

        </form>
    </jsp:body>
</t:layout>