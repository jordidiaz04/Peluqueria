//funciones para vista
$(function () {
    $('input[type=checkbox]').change(function () {
        if ($(this).prop('checked'))
            $(this).parent().addClass('checked');
        else
            $(this).parent().removeClass('checked');
    });
    $('input[type=radio]').change(function () {
        $('input[type=radio]').parent().removeClass('checked');
        if ($(this).prop('checked'))
            $(this).parent().addClass('checked');
        else
            $(this).parent().removeClass('checked');
    });
    $('.numeros').keypress(function (e){
        var tecla = String.fromCharCode(e.which);
        var numeros = '0123456789';
        if(!numeros.includes(tecla))
            e.preventDefault();
    });
    $('.letras').keypress(function (e){
        var tecla = String.fromCharCode(e.which);
        var numeros = '0123456789';
        if(numeros.includes(tecla))
            e.preventDefault();
    });
    $('.monedas').keypress(function (e){
        var tecla = String.fromCharCode(e.which);
        var numeros = '0123456789.';
        if(!numeros.includes(tecla))
            e.preventDefault();
    });
});

//toUpper and toLowe Case
function capitalizeFirst(texto) {
    return texto.substr(0, 1).toUpperCase() + texto.substr(1).toLowerCase();
}
function capitalizeAll(texto, space) {
    var result = '';
    var palabras = texto.split(' ');

    for (var i = 0; i < palabras.length; i++) {
        result += capitalizeFirst(palabras[i]);
        if (space)
            result += ' ';
    }

    return result.trim();
}

//validaciones de input
function validarInput(input) {
    var value = $('#' + input).val().trim();

    if (value === null || value === '' || value === undefined) {
        $('#' + input).addClass('form-error');
        return true;
    } else {
        $('#' + input).removeClass('form-error');
        return false;
    }
}
function validarEmail(input) {
    var value = $('#' + input).val().trim();

    if (value.includes('.com') || value.includes('.pe') || value.includes('@gmail') || value.includes('@hotmail') || value.includes('@outlook')) {
        $('#' + input).removeClass('form-error');
        return false;
    } else {
        $('#' + input).addClass('form-error');
        return true;
    }
}

//validaciones de sesión cliente
function obtenerSesion() {
    var usuario = localStorage.getItem('USUARIO') !== null ? JSON.parse(localStorage.getItem('USUARIO')) :
            sessionStorage.getItem('USUARIO') !== null ? JSON.parse(sessionStorage.getItem('USUARIO')) : null;
    return usuario;
}
function validarSesion() {
    if (obtenerSesion() === null)
        window.location.href = 'login.jsp';
}
function validarSesionLogin() {
    if (obtenerSesion() !== null)
        window.location.href = 'index.jsp';
}

//muestra de mensajes
function mostrarMensajeCorrecto(mensaje) {
    Swal.fire({
        type: 'success',
        title: 'Correcto!',
        text: mensaje
    });
}
function mostrarMensajeError(mensaje) {
    Swal.fire({
        type: 'error',
        title: 'Error!',
        text: mensaje
    });
}
function mostrarMensajeAlerta(mensaje) {
    Swal.fire({
        type: 'warning',
        title: 'Atención!',
        text: mensaje
    });
}
function mostrarMensajeInfo(mensaje) {
    Swal.fire({
        type: 'info',
        title: 'Información!',
        text: mensaje
    });
}

//llenado de combobox
function llenarComboBox(lista, id, campos) {
    var cadena = '<option value="">SELECCIONE</option>';
    if (lista.length > 0) {
        for (var i = 0; i < lista.length; i++) {
            cadena += '<option value="' + lista[i][campos[0]] + '">' + lista[i][campos[1]] + '</option>';
        }
    }

    $('#' + id).html(cadena);
}

//marcar checkbox
function marcar(input) {
    if ($('#' + input).prop('checked'))
        $('#' + input).parent().addClass('checked');
    else
        $('#' + input).parent().removeClass('checked');
}

//cargar provincias y distritos
function cargarProvincias(listaProvincias, idDepartamento, comboDistrito, campos) {
    if (idDepartamento !== '') {
        var lista = listaProvincias.filter(function (item) {
            return item.Departamento === idDepartamento;
        });
        llenarComboBox(lista, comboDistrito, campos);
    }
}
function cargarDistritos(listaDistritos, idDepartamento, idProvincia, comboDistrito, campos) {
    if (idProvincia !== '') {
        var lista = listaDistritos.filter(function (item) {
            return item.Departamento === idDepartamento && item.Provincia === idProvincia;
        });
        llenarComboBox(lista, comboDistrito, campos);
    }
}

//funciones de modal
function limpiarModal(idModal) {
    $('#' + idModal + ' input[type=text], #' + idModal + ' select').val('');
}
function abrirModal(idModal) {
    $('#' + idModal).modal('show');
}
function cerrarModal(idModal) {
    $('#' + idModal).modal('hide');
}

//comunicación con el servidor
function consultarServidor(controlador, metodo, datos, funcion) {
    $.ajax({
        url: controlador,
        method: metodo,
        data: datos,
        success: function (data) {
            funcion(data);
        }
    }).fail(function () {
        mostrarMensajeError('Problemas con la conexión, revise la función Ajax.');
    });
}

//llenado de tablas en general
function cargarTabla(datos) {
    var cadenaTHead = '';
    var cadenaTBody = '';
    var lista = datos.Lista;
    var idTabla = datos.TablaId;
    var cabecera = datos.Cabecera;
    var numColumnasVacias = datos.NumColVacias;
    var columnasVisibles = datos.ColumnasVisibles;
    var fila = datos.Fila;
    var botones = datos.Botones;

    cadenaTHead += '<thead><tr>';
    for (var i = 0; i < cabecera.length; i++) {
        cadenaTHead += '<th class="text-center">' + cabecera[i] + '</th>';
    }
    if (numColumnasVacias > 0)
        for (var j = 0; j < numColumnasVacias; j++)
            cadenaTHead += '<th class="text-center"></th>';
    cadenaTHead += '</tr></thead>';

    cadenaTBody += '<tbody>';
    for (var k = 0; k < lista.length; k++) {
        cadenaTBody += '<tr class="';
        var pos = fila.valor.indexOf(lista[k][fila.columna]);
        if (fila.css[pos] !== null)
            cadenaTBody += fila.css[pos];
        cadenaTBody += '">';
        for (var l = 0; l < columnasVisibles.length; l++) {
            cadenaTBody += '<td>' + lista[k][columnasVisibles[l]] + '</td>';
        }
        if (botones.length > 0) {
            cadenaTBody += '<td class="text-center">';
            for (var m = 0; m < botones.length; m++) {
                if (botones[m].elemento === null || botones[m].valor === null) {
                    cadenaTBody += '<a class="btn btn-sm ' + botones[m].css[0] + '" href="javascript:void(0)"';
                    if (botones[m].funcion !== null) {
                        cadenaTBody += ' onclick="';
                        if (botones[m].objeto !== null) {
                            for (var o = 0; o < botones[m].objeto.length; o++) {
                                cadenaTBody += botones[m].funcion.replace('objeto' + o, lista[k][botones[m].objeto[o]]);
                            }
                        }
                        cadenaTBody += '"';
                    }
                    cadenaTBody += '><i class="' + botones[m].icono[0] + '"></i></a>';
                } else {
                    for (var n = 0; n < botones[m].valor.length; n++) {
                        if (lista[k][botones[m].elemento] === botones[m].valor[n]) {
                            cadenaTBody += '<a class="btn btn-sm ' + botones[m].css[n] + '" href="javascript:void(0)"';
                            if (botones[m].funcion !== null) {
                                cadenaTBody += ' onclick="';
                                if (botones[m].objeto !== null) {
                                    var funcion = botones[m].funcion;
                                    for (var o = 0; o < botones[m].objeto.length; o++) {
                                        funcion = funcion.replace('objeto' + o, lista[k][botones[m].objeto[o]]);
                                    }
                                    cadenaTBody += funcion;
                                }
                                cadenaTBody += '"';
                            }
                            cadenaTBody += '><i class="' + botones[m].icono[n] + '"></i></a>';
                        }
                    }
                }
            }
            cadenaTBody += '</td>';
        }
        cadenaTBody += '</tr>';
    }
    cadenaTBody += '</tbody>';

    $('#' + idTabla).html(cadenaTHead + cadenaTBody);
}