<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="cliente" activo ="reporte">
    <jsp:attribute name="titulo">
        Reporte
    </jsp:attribute>
    <jsp:body>
        <h1>Reporte de Pedidos</h1>
        <p>
            Para hacer un reporte ingrese las fechas en el formato
            correspondiente.
        </p>
        <form class="form-inline" role="form">
            <div class="form-group" style="width: 300px;">
                <div class="input-daterange input-group">
                    <input type="text" class="input-sm form-control" name="fechaInicio" value="${fechaInicio}" />
                    <span class="input-group-addon">a</span>
                    <input type="text" class="input-sm form-control" name="fechaFin" value="${fechaFin}"/>
                </div>
            </div>
            <button type="submit" class="btn btn-default">Realizar Reporte</button>
        </form>

        <div>
            <h3>Reporte</h3>
            <br />
            <br />
            Peticiones Aceptados: 
            <br />
            Peticiones Canceladas:
            <br />
            <br />
            Pedidos Entregados:
            <br />
            Pedidos En Tránsito:
            <br />
            <br />
            Costo Total: MXN
            <br />
        </div>
    </jsp:body>
</t:layout>