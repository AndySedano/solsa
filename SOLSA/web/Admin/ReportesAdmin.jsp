<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="reporte">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Reportes de SOLSA</h1>
        <form>

            <h3>Reportes de Pedidos</h3>
            <p>
                Para hacer un reporte ingrese las fechas en el formato
                correspondiente.
            </p>
            <br />
            Fecha de Inicio
            <br />
            <input type="text" placeholder="día">&nbsp;
            <input type="text" placeholder="mes">&nbsp;
            <input type="text" placeholder="año">&nbsp;
            <br />
            <br />
            Fecha de Fin
            <br />
            <input type="text" placeholder="día">&nbsp;
            <input type="text" placeholder="mes">&nbsp;
            <input type="text" placeholder="año">&nbsp;
            <br />
            <br />
            <input type="submit" value="Realizar Reporte" />
            <br />
            <br />

            <div>
                <h3>Reporte General de SOLSA</h3>

                <h4>De Todas las Empresas:</h4>
                
                Pedidos Entregados:
                <br />
                Pedidos En Tránsito:
                <br />
                <br />
                Ganancias Totales: MXN
                <br />
                <br />

                <h4>Por Cada Empresa:</h4>

                <table class="table table-hover">
                    <tr>
                        <th>Nombre de la Empresa</th>
                        <th>Pedidos Realizados</th>
                        <th>Ingresos Totales</th>
                    </tr>


                    <c:forEach items="${requestScope.inf}" var="empresa">
                        <tr>
                            <td>
                                <c:out value="${empresa.nombre}" />
                            </td>
                            <td>
                                <c:out value="${empresa.pedidos}" />
                            </td>
                            <td>
                                <c:out value="${empresa.dineros}" />
                            </td>
                        </tr>
                    </c:forEach>

                </table>

            </div>

        </form>
    </jsp:body>
</t:layout>