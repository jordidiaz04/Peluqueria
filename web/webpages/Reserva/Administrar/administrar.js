validarSesion();
var idModal = 'modalReserva';
var idReserva;
var listaReservas;
var listaClientes;
var consulta;

$(function () {
    var datos = {Consulta: 'LISTAR'};
    consultarServidor('/SistemaPeluqueria/ReservaAdministrarController.do', 'POST', datos, cargarDatosIniciales);
    
    
});

function cargarDatosIniciales(data) {
    if (data[0] === 'error')
        mostrarMensajeError(data[1]);
    else {
        if (data[1].length === 0)
            mostrarMensajeInfo('No se encuentran registros.');
        
        listaReservas = data[1];
        listaClientes = data[2];
        llenarComboBox(listaClientes, 'cmbClientes', ['IdCliente', 'NombreCompleto']);        
        generarTabla();
    }
}