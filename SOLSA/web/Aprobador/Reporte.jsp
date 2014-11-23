<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="aprobador" activo ="reporte">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        <h1>Bienvenido ${requestScope.nombre} ! </h1>
        <form>

            <h3>Reportes de Pedidos</h3>
            <p>
                Para hacer un reporte ingrese las fechas en el formato
                correspondiente.
            </p>
            <br />
            Fecha de Inicio
            <br />
            <input type="text" placeholder="día">&nbsp;
            <input type="text" placeholder="mes">&nbsp;
            <input type="text" placeholder="año">&nbsp;
            <br />
            <br />
            Fecha de Fin
            <br />
            <input type="text" placeholder="día">&nbsp;
            <input type="text" placeholder="mes">&nbsp;
            <input type="text" placeholder="año">&nbsp;
            <br />
            <br />
            <input type="submit" value="Realizar Reporte" />
            <br />
            <br />

            <div>
                <h3>Reporte</h3>
                <br />
                Fecha de Reporte:
                <br />
                <br />
            </div>
            
            <table class="table table-striped table-hover ">
              <thead>
                <tr>
                  <th>#</th>
                  <th> Empresa</th>
                  <th>Peticiones Aceptadas</th>
                  <th>Peticiones Canceladas</th>
                  <th>Pedidos Entregados</th>
                  <th>Pedidos En Tránsito</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr class="info">
                  <td>3</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr class="success">
                  <td>4</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr class="danger">
                  <td>5</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr class="warning">
                  <td>6</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
                <tr class="active">
                  <td>7</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                  <td>Column content</td>
                </tr>
              </tbody>
            </table>             

        </form>
    </jsp:body>
</t:layout>