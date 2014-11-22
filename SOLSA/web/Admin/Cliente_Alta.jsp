<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_alta">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>

        <div class="container">
            <div class="col-lg-12" id="Cliente_Alta">
                <div class="page-header">
                    <h1 id="forms">Alta de Cliente</h1>
                </div>
                <h7>Usuario:</h7>
                <span>
                    <input id="element1" name="element1" class="element text" maxlength="30" size="15" value=""/>
                </span>
                &nbsp;
                &nbsp;
                <h7>Contraseña:</h7>
                <span>
                    <input id="element2" name="element2" class="element text" maxlength="30" size="15" value=""/>
                </span>
            </div>
        </div>
                                                
                                                
                                                

    </jsp:body>
</t:layout>
        
        
        
