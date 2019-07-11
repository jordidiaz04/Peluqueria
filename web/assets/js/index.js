validarSesion();
var usuario = obtenerSesion();

$(function (){
    var datos = {Consulta: 'MENUS', IdPerfil: usuario.IdPerfil};
    consultarServidor('/SistemaPeluqueria/HomeController.do', 'POST', datos, obtenerMenus);
});

function obtenerMenus(data){
    if(data[0] === 'error') mostrarMensajeError(data[1]);
    else{
        if(data[1].length === 0) mostrarMensajeInfo('Su usuario no cuenta con algún acceso.');
        else{
            var lista = data[1].filter(function (item) {
                return item.Estado === true;
            });
            cargarMenus(lista);
        }
    }
}
function cargarMenus(lista){
    var cadena = '';
    $(lista).each(function (index, element){
        if(element.IdMenu === element.IdMenuPadre){
            cadena += '<li class="nav-parent">';
            if(element.EsPadre) cadena += '<a href="javascript:void(0)">';
            else cadena += '<a href="' + element.Ruta + '">';            
            cadena += '<i class="' + element.Icono + '"></i><span>' + element.Menu + '</span>';
            if(element.EsPadre) cadena += ' <span class="fa arrow"></span>';
            cadena += '</a>';
            if(element.EsPadre) cadena += cargarSubMenus(element.IdMenu, lista);
        }
    });
    $('#navMenu').html(cadena);
}
function cargarSubMenus(idmenu, lista){
    var cadena = '';
    var listaSubMenus = lista.filter(function (item) {
        return item.IdMenuPadre === idmenu;
    });
    
    cadena += '<ul class="children collapse">';
    $(listaSubMenus).each(function (index, element){
        if(element.IdMenu !== element.IdMenuPadre){
            if(element.EsPadre){
                cadena += '<ul class="nav nav-sidebar">';
                cadena += '<li class="nav-parent">';
                if(element.EsPadre) cadena += '<a href="javascript:void(0)">';
                else cadena += '<a href="' + element.Ruta + '">';            
                cadena += '<i class="' + element.Icono + '"></i><span>' + element.Menu + '</span>';
                if(element.EsPadre) cadena += ' <span class="fa arrow"></span>';
                cadena += '</a>';
                if(element.EsPadre) cadena += cargarSubMenus(element.IdMenu, lista);
                cadena += '</ul>';
            }
            else{
                cadena += '<li><a href="' + element.Ruta + '"> ' + element.Menu + '</a></li>';
            }
        }
    });
    cadena += '</ul>';
    
    return cadena;
}