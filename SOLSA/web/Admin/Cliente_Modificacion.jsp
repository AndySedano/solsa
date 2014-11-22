<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_modificacion">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Modificacion Cliente</h1>
        
        <form method="POST" action="./Cliente_Modificacion" >
            <p>Username:</p><br/>
            <input type="text" name="username" class=""/>
            <p>Nombre: </p><br/>
            <input type="text" name="nombre" class="" />
            <p>Salt: </p><br/>
            <input type="text" name="salt" class="" size="2" />
            <p>Direccion: </p>
            
        </form>
    </jsp:body>
</t:layout>