<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="aprobador" activo ="peticiones">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#pedir").on("click", function (event) {
                var a = document.getElementById("entrada").value.toString();

                for (int i = 0; i < nombre.length;
                i++
                        ) {
                if (isNaN(nombre.charAt(i)) == false) {
                    alert("No ingrese números");
                }
                }
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <h1>Bienvenido ${requestScope.nombre} ! </h1>

        <form method="post" action="Peticiones">

            <h2>Peticiones de los empleados</h2>
            <p>
                Se muestran las peticiones que los empleados de su empresa
                desean, seleccione una para poder modificar su estado de
                aceptación.
                <br />
                También puede realizar una búsqueda de las peticiones
            </p>

            <input type="search" name="buscar" id="buscar" value="${buscar}"/>

            <select name="loquequieras">
                <option value="1">Pedidor</option>
                <option value="2">Estado</option>
            </select>
            <input type="submit" name="botonCool" value="Buscar" id="pedir"/>
            <br />
            <br />

            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>Responsable</th>
                    <th>Fecha de Petición</th>
                    <th>Estado</th>
                    <th>Seleccionar</th>
                </tr>

                <c:forEach items="${requestScope.peticiones}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.responsable}" />
                        </td>
                        <td>
                            <c:out value="${al.date}" />
                        </td>
                        <td>
                            <c:out value="${al.estado}" />
                        </td>
                        <td>
                            <a class=" btn btn-primary btn-sm" href="Peticion?id=${al.id}">Ver ó Modificar</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </jsp:body>
</t:layout>