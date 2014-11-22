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
                <option value="Empresa.idEmpresa">Empresa</option>
                <option value="Pedido.Estado">Estado</option>
            </select>
            <input type="submit" name="botonCool" value="Buscar" />
            <br />
            <br />

            <table border="1px">
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
                        Empresa
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Fecha de Entrega
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Estado
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Seleccionar
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
                    <c:out value="${al.empresa}" />
                    &nbsp;
                    &nbsp;
                    </td>
                    <td>
                        &nbsp;
                        &nbsp;
                    <c:out value="${al.fechaEntrega}" />
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
                        <input type="submit" name="${al.id}" value="Ver"/>
                        &nbsp;
                        &nbsp;
                    </td>
                    </tr>
                </c:forEach>

            </table>

        </form>
    </jsp:body>
</t:layout>