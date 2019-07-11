validarSesion();
var listaPerfiles;
var listaMenus;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/AccesoController.do', 'POST', datos, cargarDatosIniciales);

    $('#cmbPerfiles').change(function () {
        var idPerfil = $(this).val() === '' ? 0 : parseInt($(this).val());
        if (idPerfil !== 0) {
            var datos = {Consulta: 'OBTENER', IdPerfil: idPerfil};
            consultarServidor('/SistemaPeluqueria/AccesoController.do', 'POST', datos, cargarAccesos);
        }
        else{
            deseleccionarTodo();
        }
    });
    $('#btnSeleccionar').click(function () {
        $('#tablaAcceso').find('tbody').find('tr').each(function (index, element) {
            var obj = $(element).find('td:eq(1)').children().children().children()[0];
            $('#' + obj.id).prop('checked', true);
            marcar(obj.id);
        });
    });
    $('#btnDeseleccionar').click(function () {
        deseleccionarTodo()
    });
    $('#btnAceptar').click(function () {
        var IdPerfil = $('#cmbPerfiles').val() === '' ? 0 : parseInt($('#cmbPerfiles').val());
        if (IdPerfil !== 0) {
            var cadena = '';
            $('#tablaAcceso').find('tbody').find('tr').each(function (index, element) {
                var obj = $(element).find('td:eq(1)').children().children().children()[0];
                var idMenu = obj.id.split('-')[2];
                var estado = obj.checked;
                cadena += IdPerfil + '▲';
                cadena += idMenu + '▲';
                cadena += estado + '▼';
            });

            var datos = {Consulta: 'GUARDAR', Accesos: cadena};
            consultarServidor('/SistemaPeluqueria/AccesoController.do', 'POST', datos, guardar);
        }
    });
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0 || data[2].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');
        else {
            listaPerfiles = data[1];
            listaMenus = data[2];

            listarMenus();
            llenarComboBox(listaPerfiles, 'cmbPerfiles', ['IdPerfil', 'Descripcion']);
        }
    }
}
function listarMenus() {
    var cadena = '';
    $(listaMenus).each(function (index, element) {
        cadena += element.EsPadre ? '<tr class="padre">' : '<tr class="d-none hijo hijo' + element.IdMenuPadre + '">';
        cadena += element.EsPadre ? '<td onclick="desplegar(this, ' + element.IdMenu + ');">' : '<td>';
        cadena += element.EsPadre ? '<i class="fa fa-chevron-right"></i> ' + element.Titulo : element.Titulo;
        cadena += '</td>';
        cadena += '<td class="text-center">';
        cadena += '<label class="">';
        cadena += '<div class="icheckbox_square-blue" style="position: relative;">';
        cadena += '<input type="checkbox" id="chb-' + element.IdMenuPadre + '-' + element.IdMenu + '"';
        cadena += 'class="' + (element.EsPadre ? 'chbPadre-' + element.IdMenuPadre : 'chbHijo-' + element.IdMenuPadre) + '"';
        cadena += ' onchange="marcar(\'chb-' + element.IdMenuPadre + '-' + element.IdMenu + '\');';
        cadena += element.EsPadre ? 'seleccionarSubMenus(' + element.IdMenuPadre + ');' : 'seleccionarMenuPadre(' + element.IdMenuPadre + ');';
        cadena += '" data-checkbox="icheckbox_square-blue" style="position: absolute; opacity: 0;">';
        cadena += '</div>';
        cadena += '</label>';
        cadena += '</td>';
        cadena += '</tr>';
    });
    $('#tablaAcceso').find('tbody').html(cadena);
}
function desplegar(obj, idMenuPadre) {
    if ($(obj).children('i').hasClass('fa-chevron-right')) {
        $(obj).children('i').removeClass('fa-chevron-right');
        $(obj).children('i').addClass('fa-chevron-down');
        $('.hijo' + idMenuPadre).removeClass('d-none');
    } else {
        $(obj).children('i').addClass('fa-chevron-right');
        $(obj).children('i').removeClass('fa-chevron-down');
        $('.hijo' + idMenuPadre).addClass('d-none');
    }
}
function cargarAccesos(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('El perfil no cuenta con accesos');
        else {
            var listaAccesos = data[1];
            console.log(listaAccesos);
            $(listaAccesos).each(function (index, element) {
                $('#chb-' + element.IdMenuPadre + '-' + element.IdMenu).prop('checked', element.Estado);
                if (element.Estado)
                    $('#chb-' + element.IdMenuPadre + '-' + element.IdMenu).parent().addClass('checked');
                else
                    $('#chb-' + element.IdMenuPadre + '-' + element.IdMenu).parent().removeClass('checked');
            });
        }
    }
}
function seleccionarSubMenus(idMenuPadre) {
    var check = $('#chb-' + idMenuPadre + '-' + idMenuPadre).prop('checked');
    $('.chbHijo-' + idMenuPadre).each(function (index, element) {
        $(element).prop('checked', check);
        marcar(element.id);
    });
}
function seleccionarMenuPadre(idMenuPadre) {
    var check = false;
    $('.chbHijo-' + idMenuPadre).each(function (index, element) {
        if ($(element).prop('checked')) {
            check = true;
        }
    });
    $('#chb-' + idMenuPadre + '-' + idMenuPadre).prop('checked', check);
    marcar('chb-' + idMenuPadre + '-' + idMenuPadre);
}
function guardar(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1] === false)
            mostrarMensajeInfo('No se agregó algunos accesos');
        else
            mostrarMensajeCorrecto('Datos guardados correctamente');
    }
}
function deseleccionarTodo() {
    $('#tablaAcceso').find('tbody').find('tr').each(function (index, element) {
        var obj = $(element).find('td:eq(1)').children().children().children()[0];
        $('#' + obj.id).prop('checked', false);
        marcar(obj.id);
    });
}