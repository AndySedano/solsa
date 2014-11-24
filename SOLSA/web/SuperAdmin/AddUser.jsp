<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout seccion="superadmin" activo="">
    <jsp:attribute name="titulo">
        Alta de Administradores    
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#crear").on("click", function (event) {
                var username = document.getElementById("username").value.toString();
                var pssw1 = document.getElementById("password").value.toString();
                var pssw2 = document.getElementById("passwordagain").value.toString();
                var nombre = document.getElementById("nombre").value.toString();

                if (valor == null || valor.length == 0) {//username
                    event.preventDefault();
                    alert("Username vacio! Por favor ingrese un username válido");
                } else if (!(pssw1 == pssw2)) {//comprobas passwords no hay .equals() :( cara triste
                    event.preventDefault();
                    alert("las contraseñas no coinciden");
                }
                //comprobar nombre no numérico

                for (int i = 0; i < nombre.length; i++) {
                    if (isNaN(nombre.charAt(i)) == false) {
                        event.preventDefault();
                        alert("No ingrese números en el nombre");
                    }
                }
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <h2>Alta de Administradores</h2>
        <c:if test="${not empty message}">
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>
            <c:if test="${empty error}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
        </c:if>
        <form class="form-horizontal" method="post" action="AddUser">
            <div class="form-group">
                <label for="username" class="col-md-3 control-label">Usuario</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="username" name="username" placeholder="max. 20 caracteres">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">Contraseña</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="password" name="password" placeholder="min. 8 caracteres">
                </div>
            </div>
            <div class="form-group">
                <label for="passwordagain" class="col-md-3 control-label">Confirmar contraseña</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="passwordagain" name="passwordagain" placeholder="min. 8 caracteres">
                </div>
            </div>
            <div class="form-group">
                <label for="nombre" class="col-md-3 control-label">Nombre</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre y apellidos">
                </div>
            </div>
            <div class="form-group">
                <label for="direccion" class="col-md-3 control-label">Dirección</label>
                <div class="col-md-9">
                    <textarea class="form-control" rows="4" id="direccion" name="direccion"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="telefono" class="col-md-3 control-label">Teléfono</label>
                <div class="col-md-9">
                    <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="Incluyendo LADA">
                </div>
            </div>
            <button type="submit" class="btn btn-primary" id="crear">Crear</button>
        </form>
    </jsp:body>
</t:layout>