<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

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
            <table border="1px">
                <tr>
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
                        Precio Unitario
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Cantidad a Pedir
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Precio Total
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Quitar
                        &nbsp;
                        &nbsp;
                    </th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
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
                    <c:out value="${al.precio}" />
                    &nbsp;
                    &nbsp;
                    </td>
                    <td>
                        &nbsp;
                        &nbsp;
                        <input type="text" value="1" />
                        &nbsp;
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                        &nbsp;
                        PrecioTotal
                        &nbsp;
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                        &nbsp;
                        <input type="submit" name="${al.id}" value="Quitar"/>
                        &nbsp;
                        &nbsp;
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