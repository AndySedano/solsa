<%@ tag description="Diseño base del sitio de SOLSA" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="titulo" fragment="true" %>
<%@ attribute name="scripts" fragment="true" %>
<%@ attribute name="seccion" type="java.lang.String" %>
<%@ attribute name="activo" type="java.lang.String" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>SOLSA - <jsp:invoke fragment="titulo" /></title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="<% out.print(request.getContextPath()); %>/CSS/layout.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">
        <link rel="stylesheet" href="<% out.print(request.getContextPath()); %>/CSS/select2-bootstrap.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css">
    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand">SOLSA</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="<%  out.print(request.getContextPath());%>/Logout">Logout</a></li>

                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <c:choose>
                        <c:when test="${seccion == 'cliente'}">
                            <ul class="nav nav-sidebar">
                                <div id="site-logo" style="background-image: url('<% out.print(request.getContextPath()); %><% out.print(session.getAttribute("logo")); %>');"></div>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="Productos">Productos</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="Reporte">Reporte</a></li>
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="Peticiones">Peticiones</a></li>
                                <li class=${activo.equals("carrito") ? "active" : "none"}><a href="VerCarrito">Carrito</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${seccion == 'admin'}">
                            <ul class="nav nav-sidebar">
                                <img src="<% out.print(request.getContextPath()); %>/CSS/Solsa1.png" style="max-width: 100%; max-height: 100px;">
                            </ul>
                            <ul class="nav nav-sidebar">
                                <!--<li class=${activo.equals("buscar") ? "active" : "none"}><a href="Buscar">Buscar</a></li>-->
                                <li class=${activo.equals("reportes") ? "active" : "none"}><a href="Reportes">Reportes</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("empresas") ? "active" : "none"}><a href="Empresa_Buscar">Empresas</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("productos") ? "active" : "none"}><a href="Producto_Buscar">Productos</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("departamentos") ? "active" : "none"}><a href="Departamento_Buscar">Departamentos</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("ventas_buscar") ? "active" : "none"}><a href="Ventas_Buscar">Ventas</a></li>
                                <li class=${activo.equals("ventas_alta") ? "active" : "none"}><a href="Ventas_Alta">Alta Empleado Ventas</a></li>
                                <li class=${activo.equals("ventas_modificacion") ? "active" : "none"}><a href="Ventas_Modificar">Modificacion Empleado Ventas</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("cliente_buscar") ? "active" : "none"}><a href="Cliente_Buscar">Clientes</a></li>
                                <li class=${activo.equals("cliente_alta") ? "active" : "none"}><a href="Cliente_Alta">Alta Cliente</a></li>
                                <li class=${activo.equals("cliente_Modificacion") ? "active" : "none"}><a href="Cliente_Modificar">Modificacion Cliente</a></li>
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("aprobador_alta") ? "active" : "none"}><a href="Aprobador_Alta">Alta Aprobador</a></li>
                            </ul>
                        </c:when>
                        <c:when test="${seccion == 'aprobador'}">
                            <ul class="nav nav-sidebar">
                                <img src="<% out.print(request.getContextPath()); %>/CSS/Solsa1.png" style="max-width: 100%; max-height: 100px;">
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("peticiones") ? "active" : "none"}><a href="Peticiones">Peticiones</a></li>
                                <li class=${activo.equals("reporte") ? "active" : "none"}><a href="Reporte">Reporte</a></li>
                            </ul> 
                        </c:when>
                        <c:when test="${seccion == 'ventas'}">
                            <ul class="nav nav-sidebar">
                                <img src="<% out.print(request.getContextPath()); %>/CSS/Solsa1.png" style="max-width: 100%; max-height: 100px;">
                            </ul>
                            <ul class="nav nav-sidebar">
                                <li class=${activo.equals("pedidos") ? "active" : "none"}><a href="Pedidos">Pedidos</a></li>    
                            </ul> 
                        </c:when>
                    </c:choose>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <jsp:doBody />
                </div>
            </div>
        </div>


        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/locales/bootstrap-datepicker.es.min.js"></script>
        <script>
            $(document).on('change', '.btn-file :file', function() {
                var $input = $(this);
                var text = $input.val().replace(/\\/g, '/').replace(/.*\//, '');
                var $label = $input.closest('.input-group').find(':text');
                $label.val(text);
            });
            $(function () {
                $("select.form-control").select2({
                    //from http://stackoverflow.com/a/21996758/1858296
                    matcher: function (term, text, opt) {
                        return text.toUpperCase().indexOf(term.toUpperCase()) >= 0
                            || opt.parent("optgroup").length
                            && opt.parent("optgroup").attr("label")
                            && opt.parent("optgroup").attr("label").toUpperCase().indexOf(term.toUpperCase()) >= 0;
                    },
                    //from http://stackoverflow.com/a/19037918/1858296
                    formatSelection: function (item) {
                        og = $(item.element).parent('optgroup');
                        if (og.length)
                            return og.attr('label') + ": " + item.text;
                        else
                            return item.text;
                    }
                });
                
                $.fn.datepicker.defaults.format = "yyyy-mm-dd";
                $.fn.datepicker.defaults.language = "es";
                $('.input-daterange').datepicker();
            });
        </script>
        <jsp:invoke fragment="scripts" />
    </body>
</html>