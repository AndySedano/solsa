<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="aprobador" activo ="reporte">
    <jsp:attribute name="titulo">
        Aprobador
    </jsp:attribute>

    <jsp:body>
        <h1>Reportes de SOLSA</h1>
        <h3>Reportes de Peticiones</h3>
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
                    <h4>De Todas las Peticiones:</h4>
                    
                    Peticiones Aprobadas: ${numAprobados}
                    <br />
                    Peticiones en Espera: ${numEspera}
                    <br />
                    <br />
                    
                    
         
       
            <br />
            <br />
            
            <h4>Por Cada Empresa:</h4>


            <table class="table table-hover">
                <tr>
                    <th>Nombre de la Empresa</th>
                    <th>Peticiones Realizadas</th>
                    <th>Peticiones Aprobadas</th>
                    <th>Peticiones Rechazadas</th>
                </tr>


                <c:forEach items="${peticiones}" var="peticion">
                    <tr>
                        <td>
                            ${peticion.nombreEmpresa}
                        </td>
                        <td>
                            ${peticion.numRealizadas}
                        </td>
                        <td>
                            ${peticion.numAprobadas}
                        </td>
                        <td>
                            ${peticion.numRechazadas}
                        </td>
                       
                    </tr>
                </c:forEach>

            </table>            

        </div>
    </jsp:body>
</t:layout>