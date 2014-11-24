<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="reportes">
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
                
                Pedidos Entregados: ${numEntregados}
                <br />
                Pedidos En Tránsito: ${numTransito}
                <br />
                <br />
                Ingresos Totales: $ ${total}
                <br />
                <br />

                <h4>Por Cada Empresa:</h4>

                <table class="table table-hover">
                    <tr>
                        <th>Nombre de la Empresa</th>
                        <th>Pedidos Realizados</th>
                        <th>Ingresos Totales</th>
                    </tr>


                    <c:forEach items="${empresas}" var="empresa">
                        <tr>
                            <td>
                                ${empresa.nombre}
                            </td>
                            <td>
                                ${empresa.numPedidos}
                            </td>
                            <td style="text-align: right;">
                                <span style="float: left;">$</span>${empresa.ingresoTotal}
                            </td>
                        </tr>
                    </c:forEach>

                </table>

            </div>

        </form>
    </jsp:body>
</t:layout>