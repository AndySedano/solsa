<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="admin" activo ="reportes">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Reportes de SOLSA</h1>
        <h3>Reportes de Pedidos</h3>
        <p>
            Para generar un reporte ingrese las fechas en los siguientes campos.
        </p>
        <form class="form-inline" role="form">
            <div class="form-group" style="width: 300px;">
                <div class="input-daterange input-group">
                    <input type="text" class="input-sm form-control" name="fechaInicio" value="${fechaInicio}" />
                    <span class="input-group-addon">a</span>
                    <input type="text" class="input-sm form-control" name="fechaFin" value="${fechaFin}"/>
                </div>
            </div>
            <button type="submit" class="btn btn-default">Generar</button>
        </form>

        <div>
            <h3>Reporte General de SOLSA</h3>

            <h4>De Todas las Empresas:</h4>

            Pedidos Entregados: ${numEntregados}
            <br />
            Pedidos En Tránsito: ${numTransito}
            <br />
            <br />
            <c:if test="${conFechas}">
                Ingresos del ${fechaInicio} al ${fechaFin}: $ ${total}
            </c:if>
            <c:if test="${not conFechas}">
                Ingresos Totales: $ ${total}
            </c:if>
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
    </jsp:body>
</t:layout>