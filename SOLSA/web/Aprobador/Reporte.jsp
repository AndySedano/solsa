<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="aprobador" activo ="reporte">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Bienvenido ${requestScope.nombre} ! </h1>
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
                <h3>Reporte</h3>
                <br />
                Fecha de Reporte:
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
            
            

        </form>
    </jsp:body>
</t:layout>