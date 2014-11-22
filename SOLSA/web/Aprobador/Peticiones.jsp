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
            
            
            
            
            
            
            
            
            <input type="text" name="busqueda" />
            
            <select name="loquequieras">
                <option value="">Pedidor</option>
                <option value="">Estado</option>
            </select>
            <input type="submit" name="botonCool" value="Buscar" />
            <br />
            <br />
                        
            <table border="1px">
                <tr>
                    <th>
                        &nbsp;
                        &nbsp;
                        id
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Responsable
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Fecha de Petición
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Estado
                        &nbsp;
                        &nbsp;
                    </th>
                    <th>
                        &nbsp;
                        &nbsp;
                        Seleccionar
                        &nbsp;
                        &nbsp;
                    </th>
                </tr>
                
                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.id}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.responsable}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.fechaPeticion}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <c:out value="${al.estado}" />
                            &nbsp;
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                            &nbsp;
                            <input type="submit" name="${al.id}" value="Ver/Modificar"/>
                            &nbsp;
                            &nbsp;
                        </td>
                    </tr>
                </c:forEach>
                
            </table>
            
            
            
            
            
            
            
        </form>
        
    </jsp:body>
</t:layout>