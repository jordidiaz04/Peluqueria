validarSesion();
var idModal = 'modalServicio';
var idServicio;
var listaServicios;
var consulta;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/ServicioController.do', 'POST', datos, cargarDatosIniciales);

    $('#btnGuardar').click(function () {
        if (validarGrabar()) {
            var nombre = $('#txtNombre').val().trim().toUpperCase();
            var detalle = $('#txtDetalle').val().trim().toUpperCase();
            var precio = $('#txtPrecio').val().trim().toUpperCase();
            
            var datos = {
                Consulta: consulta,
                IdServicio: idServicio,
                Nombre: nombre,
                Detalle: detalle,
                Precio: precio
            };
            consultarServidor('/SistemaPeluqueria/ServicioController.do', 'POST', datos, actualizarTabla);
        }
    });
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');

        listaServicios = data[1];
        generarTabla();
    }
}
function mostrarDetalle(opcion, id) {
    idServicio = id;
    limpiarModal(idModal);
    switch (opcion) {
        case 1:
            consulta = 'INSERT';
            abrirModal(idModal);
            $('#lblModalTitulo').html('Nuevo servicio');
            break;
        case 2:
            consulta = 'UPDATE';
            var datos = {Consulta: 'OBTENER', IdServicio: idServicio};
            consultarServidor('/SistemaPeluqueria/ServicioController.do', 'POST', datos, cargarDatosServicio);
            break;
    }
}
function cargarDatosServicio(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1] === null)
            mostrarMensajeInfo('No se encuentró el servicio.');
        else {
            var servicio = data[1];
            $('#txtNombre').val(servicio.Nombre);
            $('#txtDetalle').val(servicio.Detalle);
            $('#txtPrecio').val(servicio.Precio);

            abrirModal(idModal);
        }
    }
}
function habilitarDeshabilitar(id, valor) {
    consulta = valor ? 'DISABLE' : 'ENABLE';
    var datos = {Consulta: consulta, IdServicio: id, Estado: !valor};
    consultarServidor('/SistemaPeluqueria/ServicioController.do', 'POST', datos, actualizarTabla);
}
function actualizarTabla(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else if (data[0] === false)
        mostrarMensajeError('No se pudo completar la operación.');
    else {
        if (data[1].length === 0) mostrarMensajeInfo('No se encuentran registros.');

        var mensaje = consulta === 'INSERT' ? 'Servicio registrado exitosamente' : consulta === '´UPDATE' ? 'Datos actualizados' :
                consulta === 'DISABLE' ? 'Servicio deshabilitado' : 'Servicio habilitado';
        mostrarMensajeCorrecto(mensaje);

        if (consulta === 'INSERT' || consulta === 'UPDATE')
            cerrarModal(idModal);

        listaServicios = data[1];
        generarTabla();
    }
}

function validarGrabar() {
    var correct = true;
    if (validarInput('txtNombre'))
        correct = false;
    if (validarInput('txtDetalle'))
        correct = false;
    if (validarInput('txtPrecio'))
        correct = false;

    return correct;
}
function generarTabla() {
    var datos = {
        Lista: listaServicios,
        TablaId: 'tablaServicio',
        Cabecera: ['Servicio', 'Detalle', 'Precio'],
        NumColVacias: 1,
        ColumnasVisibles: ['Nombre', 'Detalle', 'Precio'],
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
                objeto: ['IdServicio'],
                funcion: 'mostrarDetalle(2, objeto0)'
            }, {
                css: ['btn-danger', 'btn-success'],
                icono: ['fa fa-close', 'icon-refresh'],
                elemento: 'Estado',
                valor: [true, false],
                objeto: ['IdServicio', 'Estado'],
                funcion: 'habilitarDeshabilitar(objeto0, objeto1)'
            }]
    };
    cargarTabla(datos);
}