validarSesion();
var idModal = 'modalPersonal';
var idPersonal;
var listaPersonal;
var listaDocumentos;
var listaPerfiles;
var listaDepartamentos;
var listaProvincias;
var listaDistritos;
var consulta;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/PersonalController.do', 'POST', datos, cargarDatosIniciales);

    $('#cmbDepartamentos').change(function () {
        var idDepartamento = $(this).val();
        cargarProvincias(listaProvincias, idDepartamento, 'cmbProvincias', ['Provincia', 'Ubicacion']);
    });
    $('#cmbProvincias').change(function () {
        var idDepartamento = $('#cmbDepartamentos').val();
        var idProvincia = $(this).val();
        cargarDistritos(listaDistritos, idDepartamento, idProvincia, 'cmbDistritos', ['Distrito', 'Ubicacion']);
    });

    $('#btnGuardar').click(function () {
        if (validarGrabar()) {
            var idTipoDocumento = $('#cmbTiposDocumento').val();
            var docIdentidad = $('#txtDocIdentidad').val().trim();
            var nombres = $('#txtNombres').val().trim();
            var apellidos = $('#txtApellidos').val().trim();
            var sexo = $('#rbMasculino').prop('checked') ? 'M' : 'F';
            var idUbigeo = $('#cmbDepartamentos').val() + $('#cmbProvincias').val() + $('#cmbDistritos').val();
            var domicilio = $('#txtDomicilio').val().trim();
            var email = $('#txtEmail').val().trim();
            var telefono = $('#txtTelefono').val().trim();
            var contraseña = $('#txtContraseña').val().trim();
            var idPerfil = $('#cmbPerfiles').val();
            var datos = {
                Consulta: consulta,
                IdPersonal: idPersonal,
                Sexo: sexo,
                IdTipoDocIdentidad: idTipoDocumento,
                DocIdentidad: docIdentidad,
                Nombres: nombres,
                Apellidos: apellidos,
                IdUbigeo: idUbigeo,
                Domicilio: domicilio,
                Email: email,
                Telefono: telefono,
                Contraseña: contraseña,
                IdPerfil: idPerfil
            };
            consultarServidor('/SistemaPeluqueria/PersonalController.do', 'POST', datos, actualizarTabla);
        }
    });
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');

        listaPerfiles = data[2].filter(function (item) {
            return item.Estado === true;
        });
        listaDocumentos = data[3];
        listaDepartamentos = data[4];
        listaProvincias = data[5];
        listaDistritos = data[6];

        llenarComboBox(listaPerfiles, 'cmbPerfiles', ['IdPerfil', 'Descripcion']);
        llenarComboBox(listaDocumentos, 'cmbTiposDocumento', ['Codigo', 'Descripcion']);
        llenarComboBox(listaDepartamentos, 'cmbDepartamentos', ['Departamento', 'Ubicacion']);

        listaPersonal = data[1];
        generarTabla();
    }
}
function mostrarDetalle(opcion, id) {
    idPersonal = id;
    limpiarModal(idModal);
    switch (opcion) {
        case 1:
            consulta = 'INSERT';
            abrirModal(idModal);
            $('#lblModalTitulo').html('Nuevo personal');
            break;
        case 2:
            consulta = 'UPDATE';
            var datos = {Consulta: 'OBTENER', IdPersonal: idPersonal};
            consultarServidor('/SistemaPeluqueria/PersonalController.do', 'POST', datos, cargarDatosPersonal);
            break;
    }
}
function cargarDatosPersonal(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1] === null)
            mostrarMensajeInfo('No se encuentró el personal.');
        else {
            var personal = data[1];
            $('#cmbTiposDocumento').val(personal.IdTipoDocIdentidad);
            $('#txtDocIdentidad').val(personal.DocIdentidad);
            $('#txtNombres').val(personal.Nombres);
            $('#txtApellidos').val(personal.Apellidos);
            if (personal.Sexo === 'M')
                $('#rbMasculino').prop('checked', true);
            else
                $('#rbFemenino').prop('checked', true);
            if (personal.IdUbigeo !== '') {
                $('#cmbDepartamentos').val(personal.IdUbigeo.substr(0, 2));
                cargarProvincias(listaProvincias, personal.IdUbigeo.substr(0, 2), 'cmbProvincias', ['Provincia', 'Ubicacion']);
                $('#cmbProvincias').val(personal.IdUbigeo.substr(2, 2));
                cargarDistritos(listaDistritos, personal.IdUbigeo.substr(0, 2), personal.IdUbigeo.substr(2, 2), 'cmbDistritos', ['Distrito', 'Ubicacion']);
                $('#cmbDistritos').val(personal.IdUbigeo.substr(4, 2));
            }
            $('#txtDomicilio').val(personal.Domicilio);
            $('#txtEmail').val(personal.Email);
            $('#txtTelefono').val(personal.Telefono);
            $('#txtContraseña').val(personal.Contraseña);
            $('#cmbPerfiles').val(personal.IdPerfil);

            abrirModal(idModal);
        }
    }
}
function habilitarDeshabilitar(id, valor) {
    consulta = valor ? 'DISABLE' : 'ENABLE';
    var datos = {Consulta: consulta, IdPersonal: id, Estado: !valor};
    consultarServidor('/SistemaPeluqueria/PersonalController.do', 'POST', datos, actualizarTabla);
}
function actualizarTabla(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else if (data[0] === false)
        mostrarMensajeError('No se pudo completar la operación.');
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');

        var mensaje = consulta === 'INSERT' ? 'Personal registrado exitosamente' : consulta === '´UPDATE' ? 'Datos actualizados' :
                consulta === 'DISABLE' ? 'Personal deshabilitado' : 'Personal habilitado';
        mostrarMensajeCorrecto(mensaje);

        if (consulta === 'INSERT' || consulta === 'UPDATE')
            cerrarModal(idModal);

        listaPersonal = data[1];
        generarTabla();
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
    if (validarInput('txtContraseña'))
        correct = false;
    if (validarInput('cmbPerfiles'))
        correct = false;

    return correct;
}
function generarTabla() {
    var datos = {
        Lista: listaPersonal,
        TablaId: 'tablaPersonal',
        Cabecera: ['Dni', 'Nombre', 'Cargo', 'Email', 'Telefono'],
        NumColVacias: 1,
        ColumnasVisibles: ['DocIdentidad', 'NombreCompleto', 'Perfil', 'Email', 'Telefono'],
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
                objeto: ['IdPersonal'],
                funcion: 'mostrarDetalle(2, objeto0)'
            }, {
                css: ['btn-danger', 'btn-success'],
                icono: ['fa fa-close', 'icon-refresh'],
                elemento: 'Estado',
                valor: [true, false],
                objeto: ['IdPersonal', 'Estado'],
                funcion: 'habilitarDeshabilitar(objeto0, objeto1)'
            }]
    };
    cargarTabla(datos);
}