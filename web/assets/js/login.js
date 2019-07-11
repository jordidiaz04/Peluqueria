validarSesionLogin();

$(function () {
    $('#btnIngresar').click(function () {
        if (validarLogin()) {
            var usuario = $('#txtUsuario').val().trim();
            var contraseña = $('#txtContraseña').val();
            var datos = {
                Consulta: 'LOGIN',
                Usuario: usuario,
                Contraseña: contraseña
            };
            consultarServidor('/SistemaPeluqueria/LoginController.do', 'POST', datos, loguear);
        }
    });
});

function validarLogin() {
    var result = true;
    if (validarInput('txtUsuario'))
        result = false;
    if (validarInput('txtContraseña'))
        result = false;

    return result;
}
function loguear(data){
    if(data[0] === 'error') mostrarMensajeError(data[1]);
    else{
        if(data[1] === null) mostrarMensajeInfo('Usuario y/o contraseña incorrecta.');
        else{
            var usuario = JSON.stringify(data[1]);
            var recordar = $('#chbRecordar').prop('checked');
            if(recordar) localStorage.setItem('USUARIO', usuario);
            else sessionStorage.setItem('USUARIO', usuario);
            window.location.href = 'index.jsp';
        }
    }
}