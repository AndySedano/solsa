<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="aprobador" activo ="peticiones">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Bienvenido ${requestScope.nombre} ! </h1>
        
        <form>
            
            <h2>Peticiones de los empleados</h2>
            <p>
                Se muestran las peticiones que los empleados de su empresa
                desean, seleccione una para poder modificar su estado de
                aceptación.
                <br />
                También puede realizar una búsqueda de las peticiones
            </p>
            
            <input type="text" name="busqueda" id="entrada"/>
            
            <select name="loquequieras">
                <option value="">Pedidor</option>
                <option value="">Estado</option>
            </select>
            <input type="submit" name="botonCool" value="Buscar" onclic="validauli()"/>
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
                
                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.responsable}" />
                        </td>
                        <td>
                            <c:out value="${al.fechaPeticion}" />
                        </td>
                        <td>
                            <c:out value="${al.estado}" />
                        </td>
                        <td>
                            <a class=" btn btn-primary btn-sm" href="EstadoPedido?id=${al.id}">Ver ó Modificar</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
                                      
        <script>
                function validauli(){
                    var a = document.getElementById("entrada").value.toString();
                                        
                    for(int i=0; i<nombre.length; i++){
                        if( isNaN(nombre.charAt(i)) == false ){
                            alert("No ingrese números");
                        }
                    }
                }
        </script>
    </jsp:body>
</t:layout>