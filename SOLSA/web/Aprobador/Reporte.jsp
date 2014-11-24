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
            <input type="text" placeholder="día" id="diaA">&nbsp;
            <input type="text" placeholder="mes" id="mesA">&nbsp;
            <input type="text" placeholder="año" id="añoA">&nbsp;
            <br />
            <br />
            Fecha de Fin
            <br />
            <input type="text" placeholder="día" id="diaB">&nbsp;
            <input type="text" placeholder="mes" id="mesB">&nbsp;
            <input type="text" placeholder="año" id="añoB">&nbsp;
            <br />
            <br />
            <input type="submit" value="Realizar Reporte" onclic="validauli()"/>
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
        
        <script>
            function validauli(){
                var d1 = document.getElementById("diaA").value;
                var m1 = document.getElementById("mesA").value;
                var a1 = document.getElementById("añoA").value;
                
                var d2 = document.getElementById("diaB").value;
                var m2 = document.getElementById("mesB").value;
                var a2 = document.getElementById("añoB").value;
                
                if( a1 > a2 ){
                    alert("El año de fin debe ser mayor al de inicio");
                }else if( a1 == a2 ){
                    if( m1 > m2 ){
                        alert("El mes de fin debe ser mayor que el de inicio");
                    }else if( m1 == m2){
                        if( d1 > d2 ){
                            alert("El día de fin debe ser menor al de inicio");
                        }
                    }
                }
            }
        </script>
    </jsp:body>
</t:layout>