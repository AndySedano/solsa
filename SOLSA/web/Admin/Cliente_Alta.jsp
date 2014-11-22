<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:layout seccion="admin" activo ="cliente_alta">
    <jsp:attribute name="titulo">
        Admin
    </jsp:attribute>
    <jsp:body>
        
        <div class="container">
            
            <div class="bs-docs-section">
                
                <div class="row">
                    
                    <div class="col-lg-12" id="Cliente_Alta">
                        
                        <div class="page-header">
                            <h1 id="forms">Alta de Cliente</h1>
                        </div>
                        
                        <div class="row">
                        
                            <div class="col-lg-6">
                                
                                <div class="well bs-component">
                                    
                                    <div class="form-horizontal">
                                        
                                        <fieldset>
                                            
                                            <div class="form-horizontal">
                                                
                                                <h7>Usuario:</h7>
                                                <span>
                                                    <input id="element1" name="element1" class="element text" maxlength="30" size="15" value=""/>
                                                </span>
                                                
                                                <h7>Contrasena:</h7>
                                                <span>
                                                    <input
                                                </span>
                                                
                                            </div>
                                            
                                        </fieldset>
                                        
                                    </div>
                                    
                                </div>
                                
                            </div>
                            
                        </div>
                        
                    </div>
                    
                </div>
                
            </div>
            
        </div>
        
        
    </jsp:body>
</t:layout>
        
        
        
