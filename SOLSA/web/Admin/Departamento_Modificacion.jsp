<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="departamento_modificacion">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <form>
            <h1>Modificar Departamento</h1>
            
            <p>Si desconocel el id de la empresa puede enconrarlo realizando
                una búsqueda en la sección de buscar</p>
                <input type="text" placeholder="id de la empresa" />
                <input type="submit" value="Seleccionar" />
                <br />
                <br />
                <br />
                
                <select>
                    <option>IT</option>
                    <option>Recursos Humanos</option>
                    <option>Mercadotecnia (buu)</option>
                    <option>Otros</option>
                </select>
                
        </form>
    </jsp:body>
</t:layout>