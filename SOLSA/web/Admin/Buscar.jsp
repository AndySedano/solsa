<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="buscar">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form>
            <h1>Búsqueda General</h1>
            <p>
                Desde aquí se pueden realizar búsquedas tanto de empresas,
                personas, productos, etc.
                <br />
                Realice la búsqueda en cada sección.
            </p>
            <br />
            <br />
            
            <h3>Búsqueda de Empresas</h3>
            <input type="text" placeholder="texto" />
            <input type="submit" value="Buscar" />
            <br /> <br />
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>Nombre de la Empresa</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>RFC</th>
                </tr>
                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.nombre}" />
                        </td>
                        <td>
                            <c:out value="${al.direccion}" />
                        </td>
                        <td>
                            <c:out value="${al.telefono}" />
                        </td>
                        <td>
                            <c:out value="${al.rfc}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
                        
            
                        
                        
            <h3>Búsqueda de Personas</h3>
            <input type="text" placeholder="texto" />
            <input type="submit" value="Buscar" />
            <br /> <br />
            <table class="table table-hover">
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>username</th>
                    <th>Empresa para la que trabaja</th>
                </tr>
                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.nombre}" />
                        </td>
                        <td>
                            <c:out value="${al.username}" />
                        </td>
                        <td>
                            <c:out value="${al.empresa}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
        </form>
    </jsp:body>
</t:layout>