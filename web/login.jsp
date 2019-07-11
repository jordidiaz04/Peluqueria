<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <head>
        <%@include file="/WEB-INF/jspf/meta.jspf" %>
        <title>Sistema Peluqueria - Inicio de Sesión</title>
        <%@include file="/WEB-INF/jspf/estilo.jspf" %>
    </head>
    <body class="account separate-inputs boxed" data-page="login">
        <div class="container" id="login-block">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4 m-t-100">
                    <div class="account-wall">
                        <i class="user-img icons-faces-users-03"></i>
                        <form class="form-signin m-t-20 m-b-20 m-l-10 m-r-10">
                            <div class="append-icon">
                                <input type="text" id="txtUsuario" class="form-control form-white username" placeholder="Usuario" required>
                                <i class="icon-user"></i>
                            </div>
                            <div class="append-icon m-b-10">
                                <input type="password" id="txtContraseña" class="form-control form-white password" placeholder="Contraseña" required>
                                <i class="icon-lock"></i>
                            </div>
                            <div class="icheck-inline m-b-10">
                                <label class="">
                                    <div class="icheckbox_flat-blue" style="position: relative;">
                                        <input type="checkbox" id="chbRecordar" data-checkbox="icheckbox_flat-blue" style="position: absolute; opacity: 0;">
                                    </div> Recordar
                                </label>
                            </div>
                            <button type="button" id="btnIngresar" class="btn btn-lg btn-danger btn-block ladda-button" data-style="expand-left">Ingresar</button>
                        </form>
                    </div>
                </div>
            </div>
            <p class="account-copyright">
                <span>Copyright © 2019 </span><span>SITEMA PELUQUERIA</span>.<span>Todos los derechos reservados.</span>
            </p>
        </div>

        <%@include file="/WEB-INF/jspf/preloader.jspf" %>
        <%@include file="/WEB-INF/jspf/javascript.jspf" %>
        <script src="assets/js/login.js"></script>
    </body>
</html>