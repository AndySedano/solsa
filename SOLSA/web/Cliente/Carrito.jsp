<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="cliente" activo ="carrito">
    <jsp:attribute name="titulo">
        Carrito
    </jsp:attribute>
    <jsp:body>

        <form>

            <h1>Carrito de Ventas</h1>
            <br />
            <p>
                Aquí podrá modificar los elementos que agregó a su carrito
                de compras, al finalizar de editar sus peticiones puede
                enviarlas al Aprobador de su empresa, revise en su lista
                de peticiones para saber si ésta fue aprobada o cancelada.
            </p>
            <br />
            <br />

            <center>
                <table class="table table-hover">
                    <tr>
                        <th>Nombre</th>
                        <th>Precio Unitario</th>
                        <th>Cantidad a Pedir</th>
                        <th>Precio Total</th>
                        <th>Quitar</th>
                    </tr>

                    <c:forEach items="${requestScope.inf}" var="al">
                        <tr>
                            <td>
                        <c:out value="${al.nombre}" />
                        </td>
                        <td>
                        <c:out value="${al.precio}" />
                        </td>
                        <td>
                            <input type="text" value="${al.cantidad}" />
                        </td>
                        <td>
                            PrecioTotal
                        </td>
                        <td>
                            <input type="submit" name="${al.idProducto}" value="Quitar"/>
                        </td>
                        </tr>
                    </c:forEach>
                </table>
            </center>
            <br />
            <br />
            <h3>Estas acciones no se pueden deshacer:</h3>
            <input type="submit" value="Eliminar Carrito" />&nbsp;&nbsp;
            <input type="submit" value="Enviar Petición a Aprobador" />



        </form>

    </jsp:body>
</t:layout>