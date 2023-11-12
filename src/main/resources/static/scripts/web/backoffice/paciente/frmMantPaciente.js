$(document).on("click", "#btnagregar", function(){
    $("#cbotipodocumento").empty();
    $("#cbotipousuario").empty();
    $("#cbotipodesangre").empty();
    $("#cbosede").empty();

    listarCboTipoDocumento(0);
    listarCboTiposDeSangre(0);
    listarCboSedes(0);
    listarCboTipoUsuario(0);

    $("#hddcodpaciente").val("0");

    $("#txtdni").val("");
    $("#txtnombres").val("");
    $("#txtapellidospa").val("");
    $("#txtapellidosma").val("");
    $("#txttelefono").val("");
    $("#txtfechanacimiento").val("");
    $('input[name="sexo"]').removeAttr('checked');
    $("#txtpeso").val("");
    $("#txtaltura").val("");
    $("#txtcorreo").val("");
    $("#txtcontrasena").val("");

    // Mostrar el modal
    $("#modalNuevo").modal("show");
});

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
    var idpaciente = $("#hddcodpaciente").val();



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
            if(resultado.respuesta){
                listarPacientes();  // Asegúrate de tener una función para listar pacientes
            }
            alert(resultado.mensaje);
        }
    });
    $("#modalNuevo").modal("hide");
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
        url: "/paciente/listar",
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
