document.addEventListener("DOMContentLoaded", function() {
    listarCboTipoDocumento(0);
    listarCboTiposDeSangre(0);
    listarCboSedes(0);
    listarCboTipoUsuario(0);
});

//LLAMADO A LA LISTA DE LOS VCOMBOBOX
function listarCboSedes(idsede) {
    $.ajax({
        type: "GET",
        url: "/sede/listar", // Asegúrate de que la ruta sea la correcta en tu aplicación
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cbosede").append(
                    `<option value="${value.idsede}">${value.nomsede}</option>`
                );
            });
            if (idsede > 0) {
                $("#cbosede").val(idsede);
            }
        }
    });
}
function listarCboTipoDocumento(idtipodocumento) {
    $.ajax({
        type: "GET",
        url: "/tipodocumento/listar", // Asegúrate de que la ruta sea la correcta en tu aplicación
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cbotipodocumento").append(
                    `<option value="${value.idtipodocumento}">${value.tipodocumento}</option>`
                );
            });
            if (idtipodocumento > 0) {
                $("#cbotipodocumento").val(idtipodocumento);
            }
        }
    });
}
function listarCboTipoUsuario(idtipousuario) {
    $.ajax({
        type: "GET",
        url: "/tipousuario/listar", // Asegúrate de que la ruta sea la correcta en tu aplicación
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cbotipousuario").append(
                    `<option value="${value.idtipousuario}">${value.nomusuario}</option>`
                );
            });
            if (idtipousuario > 0) {
                $("#cbotipousuario").val(idtipousuario);
            }
        }
    });
}
function listarCboTiposDeSangre(idtipodesangre) {
    $.ajax({
        type: "GET",
        url: "/tipodesangre/listar",
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cbotipodesangre").append(
                    `<option value="${value.idtipodesangre}">${value.nomsangre}</option>`
                );
            });
            if (idtipodesangre > 0) {
                $("#cbotipodesangre").val(idtipodesangre);
            }
        }
    });
}

// Función para limpiar el formulario
function limpiarFormulario() {
    $("#txtdni").val("");
    $("#txtnombres").val("");
    $("#txtapellidospa").val("");
    $("#txtapellidosma").val("");
    $("#txttelefono").val("");
    $("#txtfechanacimiento").val("");
    $('input[name="sexo"]').prop('checked', false);
    $("#txtpeso").val("");
    $("#txtaltura").val("");
    $("#txtcorreo").val("");
    $("#txtcontrasena").val("");
}

$(document).on("click", "#btnlimpiar", function(){
    limpiarFormulario(); // Llamar a la función para limpiar el formulario
});

//BTN GUARDAR
$(document).on("click", "#btnguardar", function(){

    // Limpiar mensajes de error
    limpiarMensajesError();

    // Validar que los campos no estén vacíos
    if (camposVacios()) {
        return; // Detener la ejecución si hay campos vacíos
    }

    // Puedes agregar más validaciones para otros campos aquí...

    $.ajax({
        type: "POST",
        url: "/paciente/guardar",  // Asegúrate de que la ruta sea la correcta en tu aplicación
        contentType: "application/json",
        data: JSON.stringify({
            idpaciente: $("#hddcodpaciente").val(),
            tipodocumento: $("#cbotipodocumento").val(),
            dni: $("#txtdni").val(),
            nombres: $("#txtnombres").val(),
            apellidospa: $("#txtapellidospa").val(),
            apellidosma: $("#txtapellidosma").val(),
            telefono: $("#txttelefono").val(),
            fechanacimiento: $("#txtfechanacimiento").val(),
            sexo: $('input[name="sexo"]:checked').val(),
            peso: $("#txtpeso").val(),
            altura: $("#txtaltura").val(),
            correo: $("#txtcorreo").val(),
            contrasena: $("#txtcontrasena").val(),
            tipousuario: $("#cbotipousuario").val(),
            tipodesangre: $("#cbotipodesangre").val(),
            sede: $("#cbosede").val(),
        }),
        success: function(resultado){
            alert(resultado.mensaje);
                if(resultado.respuesta){
                limpiarFormulario();
                // Redirigir a la página de login
                window.location.href = "/login";
           }
        },
            error: function(xhr, status, error) {
                console.error("Error en la solicitud AJAX:", status, error);
                // Puedes mostrar un mensaje de error al usuario si es necesario
       }
    });
    $("#modalNuevo").modal("hide");
});

function mostrarError(campo, mensaje) {
    $(campo + "-error").text(mensaje);
}



function camposVacios() {
    let camposVacios = false;

    // Verificar y mostrar mensajes de error para cada campo
    if (validarDoc()) {
        camposVacios = true;
    }
    if (validarNombre()) {
        camposVacios = true;
    }
    if  (validarApellidospa()) {
        camposVacios = true;
    }
    if  (validarApellidosma()) {
        camposVacios = true;
    }
    if (validarTelefono()) {
        camposVacios = true;
    }
    if (validarFechaNacimiento()) {
        camposVacios = true;
    }
    if (validarSexo()) {
        camposVacios = true;
    }
    if (validarPeso()) {
        camposVacios = true;
    }
    if (validarAltura()) {
        camposVacios = true;
    }
    if (validarCorreo()) {
        camposVacios = true;
    }
    if (validarContrasena()) {
        camposVacios = true;
    }
    return camposVacios;
}
function limpiarMensajesError() {
    $(".error-mensaje").text("");
}

function validarDoc() {
    let documento = $("#txtdni").val().trim();

    // Verificar si el campo está vacío
    if (documento === "") {
        mostrarError("#txtdni", "Este campo no puede estar vacío.");
        return true;
    }

    // Verificar la longitud del documento
    if (documento.length > 9) {
        mostrarError("#txtdni", "Ingresa un N° de documento valido.");
        return true;
    }
    return false;
}


function validarNombre() {
    let nombreInput = $("#txtnombres");
    let nombre = nombreInput.val().trim();
    let caracteresValidos = /^[A-Za-zÁ-ÿ\s]+$/;

    if (nombre === "") {
        mostrarError("#txtnombres", "Este campo no puede estar vacío.");
        return true;
    } else if (!caracteresValidos.test(nombre)) {
        mostrarError("#txtnombres", "Ingrese un nombre válido");
        return true;
    } else {
        // Ocultar el mensaje de error si el campo no está vacío y es válido
        nombreInput.siblings(".error-mensaje").text("");
        return false;
    }
}

function validarApellidospa() {
    let apellidoPaterno = $("#txtapellidospa").val().trim();
    let caracteresValidos = /^[A-Za-zÁ-ÿ\s]+$/;

    // Validar Apellido Paterno
    if (apellidoPaterno === "") {
        mostrarError("#txtapellidospa", "Este campo no puede estar vacío.");
        return true;
    } else if (!caracteresValidos.test(apellidoPaterno)) {
        mostrarError("#txtapellidospa", "Ingrese un apellido paterno válido.");
        return true;
    }
    return false;
}

function validarApellidosma() {
    let apellidoMaterno = $("#txtapellidosma").val().trim();
    let caracteresValidos = /^[A-Za-zÁ-ÿ\s]+$/;

    // Validar Apellido Materno
    if (apellidoMaterno === "") {
        mostrarError("#txtapellidosma", "Este campo no puede estar vacío.");
        return true;
    } else if (!caracteresValidos.test(apellidoMaterno)) {
        mostrarError("#txtapellidosma", "Ingrese un apellido materno válido.");
        return true;
    }

    return false;
}

function validarTelefono() {
    let telefono = $("#txttelefono").val().trim();
    let soloNumeros = /^[0-9]+$/;

    if (telefono === "") {
        mostrarError("#txttelefono", "Este campo no puede estar vacío.");
        return true;
    } else if (!soloNumeros.test(telefono)) {
        mostrarError("#txttelefono", "Ingrese un número de teléfono válido (solo números).");
        return true;
    } else if (telefono.length < 7 || telefono.length > 15) {
        mostrarError("#txttelefono", "El número de teléfono debe tener entre 7 y 15 dígitos.");
        return true;
    }

    return false;
}

function validarFechaNacimiento() {
    let fechaNacimiento = $("#txtfechanacimiento").val().trim();
    let fechaValida = /^(19|20)\d\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;

    if (fechaNacimiento === "") {
        mostrarError("#txtfechanacimiento", "Este campo no puede estar vacío.");
        return true;
    } else if (!fechaValida.test(fechaNacimiento)) {
        mostrarError("#txtfechanacimiento", "Ingrese una fecha de nacimiento válida en el formato DD-MM-YYYY.");
        return true;
    }

    return false;
}

function validarSexo() {
    let sexo = $('input[name="sexo"]:checked').val();
    if (!sexo) {
        mostrarError("#sexo", "Este campo no puede estar vacío. Debes seleccionar una opción.");
        return true;
    }
    return false;
}

function validarPeso() {
    let peso = $("#txtpeso").val().trim();
    let soloNumeros = /^[0-9]+$/;

    if (peso === "") {
        mostrarError("#txtpeso", "Este campo no puede estar vacío.");
        return true;
    } else if (!soloNumeros.test(peso)) {
        mostrarError("#txtpeso", "Este campo solo puede contener números.");
        return true;
    } else if (parseInt(peso) < 1 || parseInt(peso) > 500) {
        mostrarError("#txtpeso", "Ingrese un peso válido entre 1 y 500 kilogramos.");
        return true;
    }

    return false;
}

function validarAltura() {
    let altura = $("#txtaltura").val().trim();
    let soloNumeros = /^[0-9]+$/;

    if (altura === "") {
        mostrarError("#txtaltura", "Este campo no puede estar vacío.");
        return true;
    } else if (!soloNumeros.test(altura)) {
        mostrarError("#txtaltura", "Este campo solo puede contener números.");
        return true;
    } else if (parseInt(altura) < 50 || parseInt(altura) > 300) {
        mostrarError("#txtaltura", "Ingrese una altura válida entre 50 y 300 centímetros.");
        return true;
    }

    return false;
}

function validarCorreo() {
    let correo = $("#txtcorreo").val().trim();
    let regexCorreo = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
    if (correo === "") {
        mostrarError("#txtcorreo", "Este campo no puede estar vacío.");
        return true;
    } else if (!regexCorreo.test(correo)) {
        mostrarError("#txtcorreo", "Por favor, ingresa un correo electrónico válido.");
        return true;
    }
    return false;
}


function validarContrasena() {
    let contrasena = $("#txtcontrasena").val().trim();
    // Verifica si la contraseña tiene al menos 8 caracteres
    if (contrasena.length < 6) {
        mostrarError("#txtcontrasena", "La contraseña debe tener al menos 6 caracteres.");
        return true;
    }
    // Verifica si la contraseña contiene al menos un número
    if (!/\d/.test(contrasena)) {
        mostrarError("#txtcontrasena", "La contraseña debe contener al menos un número.");
        return true;
    }
    // Verifica si la contraseña contiene al menos una letra mayúscula
    if (!/[A-Z]/.test(contrasena)) {
        mostrarError("#txtcontrasena", "La contraseña debe contener al menos una letra mayúscula.");
        return true;
    }
    return false;
}




/*function validarDoc() {
    let dni = $("#txtdni").val().trim();
    let tipoDocumento = $("#cbotipodocumento").val();
    let soloNumeros = /^[0-9]+$/;

    if (dni === "") {
        mostrarError("#txtdni", "Este campo no puede estar vacío.");
        return true;
    } else if (!soloNumeros.test(dni)) {
        mostrarError("#txtdni", "Este campo solo puede contener números.");
        return true;
    } else if (tipoDocumento === "DNI" && dni.length !== 7) {
        mostrarError("#txtdni", "El DNI debe tener exactamente 7 dígitos.");
        return true;
    } else if (tipoDocumento === "Pasaporte" && dni.length !== 9) {
        mostrarError("#txtdni", "El CARNET debe tener exactamente 9 dígitos.");
        return true;
    }
    return false;
}*/


