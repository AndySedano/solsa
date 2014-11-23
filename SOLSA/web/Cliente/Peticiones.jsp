<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="cliente" activo ="peticiones">
    <jsp:attribute name="titulo">
        Peticiones
    </jsp:attribute>
    <jsp:body>
        
        <form>
            
            <h1>Estado de las Peticiones Actuales</h1>
            <p>
                Aquí podrá ver las peticiones que ha hecho recientemente,
                incluyendo su estado.
            </p>
            
            <table class="table table-hover">
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        Fecha de Petición
                    </th>
                    <th>
                        Estado
                    </th>
                </tr>

                <c:forEach items="${requestScope.inf}" var="al">
                    <tr>
                        <td>
                            <c:out value="${al.id}" />
                        </td>
                        <td>
                            <c:out value="${al.date}" />
                        </td>
                        <td>
                            <c:out value="${al.estado}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
        </form>
        
    </jsp:body>
</t:layout>