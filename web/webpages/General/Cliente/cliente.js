validarSesion();
var idModal = 'modalCliente';
var idCliente;
var listaClientes;
var listaDocumentos;
var consulta;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/ClienteController.do', 'POST', datos, cargarDatosIniciales);

    $('#btnGuardar').click(function () {
        if (validarGrabar()) {
            var idTipoDocumento = $('#cmbTiposDocumento').val();
            var docIdentidad = $('#txtDocIdentidad').val().trim();
            var nombres = $('#txtNombres').val().trim().toUpperCase();
            var apellidos = $('#txtApellidos').val().trim().toUpperCase();
            var email = $('#txtEmail').val().trim().toUpperCase();
            var telefono = $('#txtTelefono').val().trim();
            var datos = {
                Consulta: consulta,
                IdCliente: idCliente,
                IdTipoDocIdentidad: idTipoDocumento,
                DocIdentidad: docIdentidad,
                Nombres: nombres,
                Apellidos: apellidos,
                Email: email,
                Telefono: telefono
            };
            consultarServidor('/SistemaPeluqueria/ClienteController.do', 'POST', datos, actualizarTabla);
        }
    });
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0) mostrarMensajeInfo('No se encuentran registros.');

        listaDocumentos = data[2];
        llenarComboBox(listaDocumentos, 'cmbTiposDocumento', ['Codigo', 'Descripcion']);

        listaClientes = data[1];
        generarTabla();
    }
}
function mostrarDetalle(opcion, id) {
    idCliente = id;
    limpiarModal(idModal);
    switch (opcion) {
        case 1:
            consulta = 'INSERT';
            abrirModal(idModal);
            $('#lblModalTitulo').html('Nuevo cliente');
            break;
        case 2:
            consulta = 'UPDATE';
            var datos = {Consulta: 'OBTENER', IdCliente: idCliente};
            consultarServidor('/SistemaPeluqueria/ClienteController.do', 'POST', datos, cargarDatosCliente);
            break;
    }
}
function cargarDatosCliente(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1] === null)
            mostrarMensajeInfo('No se encuentró el cliente.');
        else {
            var cliente = data[1];
            $('#cmbTiposDocumento').val(cliente.IdTipoDocIdentidad);
            $('#txtDocIdentidad').val(cliente.DocIdentidad);
            $('#txtNombres').val(cliente.Nombres);
            $('#txtApellidos').val(cliente.Apellidos);
            $('#txtEmail').val(cliente.Email);
            $('#txtTelefono').val(cliente.Telefono);

            abrirModal(idModal);
        }
    }
}
function habilitarDeshabilitar(id, valor) {
    consulta = valor ? 'DISABLE' : 'ENABLE';
    var datos = {Consulta: consulta, IdCliente: id, Estado: !valor};
    consultarServidor('/SistemaPeluqueria/ClienteController.do', 'POST', datos, actualizarTabla);
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
            var mensaje = consulta === 'INSERT' ? 'Cliente registrado exitosamente' : consulta === '´UPDATE' ? 'Datos actualizados' :
                    consulta === 'DISABLE' ? 'Cliente deshabilitado' : 'Cliente habilitado';
            mostrarMensajeCorrecto(mensaje);

            if (consulta === 'INSERT' || consulta === 'UPDATE')
                cerrarModal(idModal);

            listaClientes = data[1];
            generarTabla();
        }
    }
}

function validarGrabar() {
    var correct = true;
    if (validarInput('cmbTiposDocumento'))
        correct = false;
    if (validarInput('txtDocIdentidad'))
        correct = false;
    if (validarInput('txtNombres'))
        correct = false;
    if (validarInput('txtApellidos'))
        correct = false;
    if (validarInput('txtTelefono'))
        correct = false;

    return correct;
}
function generarTabla() {
    var datos = {
        Lista: listaClientes,
        TablaId: 'tablaCliente',
        Cabecera: ['Dni', 'Nombre', 'Email', 'Telefono'],
        NumColVacias: 1,
        ColumnasVisibles: ['DocIdentidad', 'NombreCompleto', 'Email', 'Telefono'],
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
                objeto: ['IdCliente'],
                funcion: 'mostrarDetalle(2, objeto0)'
            }]
    };
    cargarTabla(datos);
}