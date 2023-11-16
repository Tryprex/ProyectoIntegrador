document.addEventListener("DOMContentLoaded", function() {
    listarCboTipoDocumento(0);
    listarCboTiposDeSangre(0);
    listarCboSedes(0);
    listarCboTipoUsuario(0);
});


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
                    `<option value="${value.idtipodocumento}">${value.tipodocumentotipodocumento}</option>`
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

$(document).on("click", "#btnguardar", function(){
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
                        }
    });
    $("#modalNuevo").modal("hide");
});


// Función para limpiar el formulario
function limpiarFormulario() {
    $("#cbotipodocumento").val("");
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
    $("#cbotipousuario").val("");
    $("#cbotipodesangre").val("");
    $("#cbosede").val("");
}
$(document).on("click", "#btnlimpiar", function(){
    limpiarFormulario(); // Llamar a la función para limpiar el formulario
});