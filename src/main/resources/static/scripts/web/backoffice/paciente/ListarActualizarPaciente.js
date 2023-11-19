$(document).on("click", ".btnactualizar", function(){
    $("#hddcodpaciente").val($(this).attr("data-idpaciente"));
    $("#cbotipousuario").empty();
    $("#cbotipodesangre").empty();
    $("#cbosede").empty();
    $("#cbotipodocumento").empty();


    // Cargar las cbo
    listarCboTiposDeSangre($(this).attr("data-tipodesangre"));
    listarCboSedes($(this).attr("data-sede"));
    listarCboTipoDocumento($(this).attr("data-tipodocumento"));
    listarCboTipoUsuario($(this).attr("data-tipousuario"));

    //disabled
    $("#cbotipodocumento").attr("disabled", true);
    $("#txtdni").attr("disabled", true);
    $("#txtfechanacimiento").attr("disabled", true);
    $("#txtcontrasena").attr("disabled", true);
    $("#cbotipousuario").attr("disabled", true);
    $("#cbotipodesangre").attr("disabled", true);

    $("#txtdni").val($(this).attr("data-dni"));
    $("#txtnombres").val($(this).attr("data-nombres"));
    $("#txtapellidospa").val($(this).attr("data-apellidospa"));
    $("#txtapellidosma").val($(this).attr("data-apellidosma"));
    $("#txttelefono").val($(this).attr("data-telefono"));
    $("#txtfechanacimiento").val($(this).attr("data-fechanacimiento"));

     var sexo = $(this).attr("data-sexo");
     if (sexo.toLowerCase() == "masculino") {
         $("#masculino").attr('checked', 'checked');
     } else if (sexo.toLowerCase() == "femenino") {
         $("#femenino").attr('checked', 'checked');
     }

    $("#txtpeso").val($(this).attr("data-peso"));
    $("#txtaltura").val($(this).attr("data-altura"));
    $("#txtcorreo").val($(this).attr("data-correo"));
    $("#txtcontrasena").val($(this).attr("data-contrasena"));


    // Mostrar el modal de actualización de paciente
    $("#modalNuevo").modal("show");

    // Mensajes de consola para depurar
    //   console.log("Correo:", $(this).attr("data-correo"));
    //    console.log("Contraseña:", $(this).attr("data-contrasena"));
});


$(document).on("click", "#btnguardar", function(){

    // Limpiar mensajes de error
    limpiarMensajesError();

    // Validar que los campos no estén vacíos
    if (camposVacios()) {
        return; // Detener la ejecución si hay campos vacíos
    }

    $.ajax({
        type: "POST",
        url: "/gestionpaciente/actualizar",
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
            if(resultado.respuesta){
                listarPacientes();
            }
            alert(resultado.mensaje);
        },
             error: function(xhr, status, error) {
                         console.error("Error en la solicitud AJAX:", status, error);

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
    if (validarPeso()) {
        camposVacios = true;
    }
    if (validarAltura()) {
        camposVacios = true;
    }
    if (validarCorreo()) {
        camposVacios = true;
    }
    return camposVacios;
}


function limpiarMensajesError() {
    $(".error-mensaje").text("");
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


//funciones de los combobox
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
function listarPacientes() {
    $.ajax({
        type: "GET",
        url: "/gestionpaciente/listar",
        dataType: "json",
        success: function (resultado) {
            $("#tblpaciente > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tblpaciente > tbody").append("<tr>" +
                    "<td>" + value.tipodocumento.tipodocumento + "</td>" +
                    "<td>" + value.dni + "</td>" +
                    "<td>" + value.nombres + "</td>" +
                    "<td>" + value.apellidospa + "</td>" +
                    "<td>" + value.apellidosma + "</td>" +
                    "<td>" + value.telefono + "</td>" +
                    "<td>" + value.fechanacimiento + "</td>" +
                    "<td>" + value.sexo + "</td>" +
                    "<td>" + value.peso + "</td>" +
                    "<td>" + value.altura + "</td>" +
                    "<td>" + value.tipodesangre.nomsangre  + "</td>" +
                    "<td>" + value.sede.nomsede + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-info btnactualizar'" +
                    "data-idpaciente='"+value.idpaciente+"'"+
                    "data-tipodocumento='" + value.tipodocumento.idtipodocumento + "'" +
                    "data-dni='" + value.dni + "'" +
                    "data-nombres='" + value.nombres + "'" +
                    "data-apellidospa='" + value.apellidospa + "'" +
                    "data-apellidosma='" + value.apellidosma + "'" +
                    "data-telefono='" + value.telefono + "'" +
                    "data-fechanacimiento='" + value.fechanacimiento + "'" +
                    "data-sexo='" + (value.sexo == "Masculino" ? "masculino" : "femenino") + "'" +
                    "data-peso='" + value.peso + "'" +
                    "data-altura='" + value.altura + "'" +
                    "data-correo='" + value.correo + "'" +
                    "data-contrasena='" + value.contrasena + "'" +
                    "data-tipousuario='" + value.tipousuario.idtipousuario + "'" +
                    "data-tipodesangre='" + value.tipodesangre.idtipodesangre + "'" +
                    "data-sede='" + value.sede.idsede + "'>" +
                    "<i class='fas fa-edit'></i></button>" +
                    "</td></tr>");
            });
        }
    });
}

