validarSesion();
var idModal = 'modalMenu';
var idMenu;
var listaMenus;
var listaMenusPadre;
var consulta;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/MenuController.do', 'POST', datos, cargarDatosIniciales);

    $('#txtIcono').focusout(function () {
        var icono = $(this).val();
        $('#iIcono').removeClass();
        $('#iIcono').addClass(icono);
    });
    $('#rbSi').change(function () {
        $('#txtIcono').parent().parent().removeClass('d-none');
    });
    $('#rbNo').change(function () {
        $('#txtIcono').parent().parent().addClass('d-none');
    });
    $('#btnGuardar').click(function () {
        if (validarGrabar()) {
            var padre = $('#cmbMenusPadre option:selected').text();
            var titulo = $('#txtTitulo').val().trim();
            var ruta = $('#rbSi').prop('checked') ? '' : '/SistemaPeluqueria/webpages/' + capitalizeAll(padre, false) + '/' + capitalizeAll(titulo, false);
            var esPadre = $('#rbSi').prop('checked');
            var idMenuPadre = $('#cmbMenusPadre').val() === '' ? 0 : parseInt($('#cmbMenusPadre').val());
            var icono = $('#rbSi').prop('checked') ? $('#txtIcono').val().trim() : '';
            var datos = {
                Consulta: consulta,
                IdMenu: idMenu,
                Titulo: titulo,
                Ruta: ruta,
                EsPadre: esPadre,
                IdMenuPadre: idMenuPadre,
                Icono: icono
            };
            consultarServidor('/SistemaPeluqueria/MenuController.do', 'POST', datos, actualizarTabla);
        }
    });
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');
        else {
            listaMenusPadre = data[1].filter(function (item) {
                return item.EsPadre === true & item.Estado === true;
            });
            llenarComboBox(listaMenusPadre, 'cmbMenusPadre', ['IdMenu', 'Titulo']);
        }
        listaMenus = data[1];
        generarTabla();
    }
}
function mostrarDetalle(opcion, id) {
    idMenu = id;
    limpiarModal(idModal);
    switch (opcion) {
        case 1:
            consulta = 'INSERT';
            abrirModal(idModal);
            $('#lblModalTitulo').html('Nuevo menú');
            break;
        case 2:
            consulta = 'UPDATE';
            var datos = {Consulta: 'OBTENER', IdMenu: idMenu};
            consultarServidor('/SistemaPeluqueria/MenuController.do', 'POST', datos, cargarDatosMenu);
            break;
    }
}
function cargarDatosMenu(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1] === null)
            mostrarMensajeInfo('No se encuentró el menu.');
        else {
            var menu = data[1];
            $('#txtTitulo').val(menu.Titulo);
            if (menu.IdMenu !== menu.IdMenuPadre)
                $('#cmbMenusPadre').val(menu.IdMenuPadre);
            if (menu.EsPadre)
                $('#rbSi').prop('checked', true);
            else
                $('#rbNo').prop('checked', true);
            $('#txtRuta').val(menu.Ruta);
            $('#txtIcono').val(menu.Icono);

            abrirModal(idModal);
        }
    }
}
function habilitarDeshabilitar(id, valor) {
    consulta = valor ? 'DISABLE' : 'ENABLE';
    var datos = {Consulta: consulta, IdMenu: id, Estado: !valor};
    consultarServidor('/SistemaPeluqueria/MenuController.do', 'POST', datos, actualizarTabla);
}
function actualizarTabla(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else if (data[0] === false)
        mostrarMensajeError('No se pudo completar la operación.');
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');
        else {
            var mensaje = consulta === 'INSERT' ? 'Menu registrado exitosamente' : consulta === '´UPDATE' ? 'Datos actualizados' :
                    consulta === 'DISABLE' ? 'Menu deshabilitado' : 'Menu habilitado';
            mostrarMensajeCorrecto(mensaje);

            if (consulta === 'INSERT' || consulta === 'UPDATE')
                cerrarModal(idModal);

            listaMenus = data[1];
            listaMenusPadre = data[1].filter(function (item) {
                return item.EsPadre === true & item.Estado === true;
            });

            llenarComboBox(listaMenusPadre, 'cmbMenusPadre', ['IdMenu', 'Titulo']);
            generarTabla();
        }
    }
}

function validarGrabar() {
    var correct = true;
    if (validarInput('txtTitulo'))
        correct = false;
    if ($('#rbSi').prop('checked'))
        if (validarInput('txtIcono'))
            correct = false;

    return correct;
}
function generarTabla() {
    var datos = {
        Lista: listaMenus,
        TablaId: 'tablaMenu',
        Cabecera: ['Menu', 'Icono', 'Ruta', 'Menu Padre'],
        NumColVacias: 1,
        ColumnasVisibles: ['Titulo', 'Icono', 'Ruta', 'MenuPadre'],
        Fila: {
            columna: 'Estado',
            valor: [true, false],
            css: ['', 'danger']
        },
        Botones: [{
                css: ['btn-default'],
                icono: ['icon-note'],
                elemento: null,
                valor: null,
                objeto: ['IdMenu'],
                funcion: 'mostrarDetalle(2, objeto0)'
            }, {
                css: ['btn-danger', 'btn-success'],
                icono: ['fa fa-close', 'icon-refresh'],
                elemento: 'Estado',
                valor: [true, false],
                objeto: ['IdMenu', 'Estado'],
                funcion: 'habilitarDeshabilitar(objeto0, objeto1)'
            }]
    };
    cargarTabla(datos);
}