<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/jspf/meta.jspf" %>
        <title>Sistema Peluqueria</title>
        <%@include file="/WEB-INF/jspf/estilo.jspf" %>        
    </head>
    <body class="fixed-topbar fixed-sidebar theme-sdtl color-default dashboard">    
        <section>
            <%@include file="/WEB-INF/jspf/menu.jspf" %>            
            <div class="main-content">
                <%@include file="/WEB-INF/jspf/header.jspf" %>
                <div class="page-content">
                    <div class="header">
                        <h2><strong>Mantenimiento de Accesos</strong></h2>
                        <div class="breadcrumb-wrapper">
                            <ol class="breadcrumb">
                                <li><a href="javascript:void(0)">Menus</a>
                                </li>
                                <li><a href="javascript:void(0)">Seguridad</a>
                                </li>
                                <li class="active">Accesos</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 portlets ui-sortable">
                            <div class="panel">
                                <div class="row">
                                    <div class="col-sm-6 panel-content">
                                        <label class="col-sm-3 control-label">Perfil:</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="cmbPerfiles"></select>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 panel-content">
                                        <div class="pull-right">
                                            <button type="button" class="btn btn-success btn-embossed" id="btnAceptar"> Aceptar</button>
                                            <button type="button" class="btn btn-primary btn-embossed" id="btnSeleccionar"> Seleccionar Todo</button>
                                            <button type="button" class="btn btn-warning btn-embossed" id="btnDeseleccionar"> Deseleccionar Todo</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="panel-content">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="tablaAcceso">
                                                <thead>
                                                    <tr>
                                                        <th>Menu</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <a href="#" class="scrollup" style="display: none;"><i class="fa fa-angle-up"></i></a>

        <%@include file="/WEB-INF/jspf/preloader.jspf" %>
        <%@include file="/WEB-INF/jspf/javascript.jspf" %>
        <script src="<%=request.getContextPath()%>/assets/js/index.js"></script>
        <script src="acceso.js"></script>
    </body>
</html>