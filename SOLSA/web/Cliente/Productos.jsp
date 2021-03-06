<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="cliente" activo ="productos">
    <jsp:attribute name="titulo">
        Productos
    </jsp:attribute>
    <jsp:body>
        <form>
            <h1>Catálogo de Productos</h1>
            <p>
                Productos disponibles para usted, si desea pedir alguno
                haga clic en "Agregar al carrito" si desea ordenar varios del
                mismo tipo puede modificar la cantidad posteriormente en el
                carrito.
            </p>
            <br />
            <br />

            <div class="row">
                <c:forEach items="${requestScope.inf}" var="al">
                    <div class="col-md-2">
                        <table>
                            <tr><td><center>

                                <div class="image" style="background-image: url('${al.foto.url}'); width: 100px; height: 100px;"></div>

                            </center></td></tr>
                            <tr><td><center>

                                <c:out value="${al.nombre}" />

                            </center></td></tr>
                            <tr><td><center>

                                <c:out value="${al.descripcion}" />

                            </center></td></tr>
                            <tr><td><center>

                                <c:out value="${al.tipo}" />

                            </center></td></tr>
                            <tr><td><center>

                                <c:out value="${al.precio}" />

                            </center></td></tr>
                            <tr><td><center>

                                <a class=" btn btn-primary btn-sm" 
                                   href="Carrito?id=${al.idProducto}" onclick="uli()">
                                    Añadir al Carrito
                                </a>

                            </center></td></tr>
                        </table>
                    </div>
                </c:forEach> 
            </div>

        </form>

        <script>
            function uli() {
                alert("Se está agregando el producto a su carrito");
            }
        </script>

    </jsp:body>
</t:layout>