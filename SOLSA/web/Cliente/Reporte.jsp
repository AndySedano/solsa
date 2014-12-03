<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="cliente" activo ="reporte">
    <jsp:attribute name="titulo">
        Cliente
    </jsp:attribute>
      
    <jsp:body>
        <h1>Reportes</h1>
        <h3>Reportes de Productos</h3>
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
                    <h3>Reporte General</h3>
                    <h4>De Todos los Productos:</h4>
                   
            <c:if test="${conFechas}">
                Ingresos del ${fechaInicio} al ${fechaFin}: $ ${costoTotal}
            </c:if>
          
            <br />
            <br />
            

            <table class="table table-hover">
                <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                </tr>


                <c:forEach items="${productos}" var="empresa">
                    <tr>
                        <td>
                            ${producto.nombre}
                        </td>
                        <td>
                            ${producto.numPedidos}
                        </td>
                        <td style="text-align: right;">
                            <span style="float: left;">$</span>${producto.costoTotal}
                        </td>
                    </tr>
                </c:forEach>

            </table>            

        </div>
    </jsp:body>
</t:layout>
        
