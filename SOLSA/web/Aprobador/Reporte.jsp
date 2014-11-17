<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="aprobador" activo ="reporte">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Bienvenido ${requestScope.nombre} ! </h1>
    </jsp:body>
</t:layout>