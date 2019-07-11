<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/WEB-INF/jspf/meta.jspf" %>        
        <%@include file="/WEB-INF/jspf/estilo.jspf" %>        
    </head>
    <body class="fixed-topbar fixed-sidebar theme-sdtl color-default dashboard">    
        <section>
            <%@include file="/WEB-INF/jspf/menu.jspf" %>            
            <div class="main-content">
                <%@include file="/WEB-INF/jspf/header.jspf" %>
                <div class="page-content">
                    <div class="header">
                        <h2><strong>Mantenimiento de Clientes</strong></h2>
                        <div class="breadcrumb-wrapper">
                            <ol class="breadcrumb">
                                <li><a href="javascript:void(0)">Menus</a>
                                </li>
                                <li><a href="javascript:void(0)">General</a>
                                </li>
                                <li class="active">Cliente</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 portlets ui-sortable">
                            <div class="panel">
                                <div class="row">
                                    <div class="panel-content pull-right">
                                        <button type="button" class="btn btn-success" onclick="mostrarDetalle(1, 0)"> Nuevo</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="panel-content">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="tablaCliente"></table>
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

        <div class="modal fade" id="modalCliente" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-primary">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="icons-office-52"></i></button>
                        <h4 class="modal-title" id="lblModalTitulo"></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <form class="form-horizontal col-sm-12">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Tipo Documento:</label>
                                    <div class="col-sm-9">
                                        <select class="form-control" id="cmbTiposDocumento"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Número Documento:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control numeros" id="txtDocIdentidad" placeholder="Complete este campo" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Nombres:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control letras" id="txtNombres" placeholder="Complete este campo" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Apellidos:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control letras" id="txtApellidos" placeholder="Complete este campo" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Email:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="txtEmail" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Telefono:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control numeros" id="txtTelefono" placeholder="Complete este campo" />
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-embossed" id="btnGuardar">Guardar</button>
                        <button type="button" class="btn btn-default btn-embossed" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/WEB-INF/jspf/preloader.jspf" %>
        <%@include file="/WEB-INF/jspf/javascript.jspf" %>
        <script src="<%=request.getContextPath()%>/assets/js/index.js"></script>
        <script src="cliente.js"></script>
    </body>
</html>