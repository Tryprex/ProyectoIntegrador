$(document).ready(function () {
    cargarPacientes();
    cargarSedes();
    cargarEspecialidades();
    cargarCitas();

    $("#step3 button").click(function () {
        cargarMedicosPorEspecialidad();
        mostrarPaso(4);
    });

    $("#medico").change(function () {
        var idMedicoSeleccionado = $("#medico").val();
        cargarFechasYHorasDisponibles(idMedicoSeleccionado);
    });

    $(".btnAgregarCita").click(function () {
        agregarCita();
    });

});


function cargarCitas() {
    $.ajax({
        url: "/cita/listar",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log("Datos recibidos:", data);
            $("#tblCitas tbody").empty();

            console.log("Respuesta del servidor:", data);

            $.each(data, function (index, cita) {
                try {
                    var fecha = moment(cita.horario.fechahorario).toDate();

                    var apellidos = cita.paciente.apellidospa + " " + cita.paciente.apellidosma;

                    var nombreCompleto = apellidos + ", " + cita.paciente.nombres;

                    var fila = "<tr>" +
                        "<td>" + fecha.toLocaleDateString() + "</td>" +
                        "<td>" + fecha.toLocaleTimeString() + "</td>" +
                        "<td>" + nombreCompleto + "</td>" +
                        "<td>" + cita.horario.medico.especialidad.nomespecialidad + "</td>" +
                        "<td>" + cita.sede.nomsede + "</td>" +
                        "</tr>";

                    $("#tblCitas tbody").append(fila);
                } catch (error) {
                    console.error("Error en la construcción de la fila:", error);
                }
            });
        },
        error: function (error) {
            console.error("Error al cargar las citas:", error);
        }
    });
}

function agregarCita() {
    // Recopila los datos de cada paso
    var paciente = $("#paciente").val();
    var sede = $("#sede").val();
    var especialidad = $("#especialidad").val();
    var medico = $("#medico").val();
    var selectedFecha = document.getElementById("fecha").value;
    var selectedHora = document.getElementById("hora").value;

    console.log("ID de Horario antes de agregarCita: ", $("#idhorario").val());

    $.ajax({
        url: "/cita/guardar",
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            paciente: paciente,
            sede: sede,
            especialidad: especialidad,
            medico: medico,
            fecha: selectedFecha,
            hora: selectedHora,
            horarioId: $("#idhorario").val(),
        }),
        success: function (resultado) {
            if (resultado.respuesta) {
                cargarCitas();
            } else {
                console.error("Error al agregar la cita: " + resultado.mensaje);
            }
        },
        error: function (error) {
            console.error("Error al agregar la cita: " + error);
        }
    });
}

function mostrarPaso(paso) {
    for (var i = 1; i <= 5; i++) {
        var elemento = document.getElementById('step' + i);
        if (i === paso) {
            elemento.style.display = 'block';
        } else {
            elemento.style.display = 'none';
        }
    }
}

function cargarPacientes() {
    $.ajax({
        url: "/paciente/listar",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#paciente").empty();

            $.each(data, function (index, paciente) {
                var option = "<option value='" + paciente.idpaciente + "'>" + paciente.nombres + " " + paciente.apellidospa + " " + paciente.apellidosma + "</option>";
                $("#paciente").append(option);
            });

            mostrarPaso(1);

            $("#paciente").change(function () {
                var idPaciente = $("#paciente").val();
                var nombrePaciente = $("#paciente option:selected").text();
                console.log("Paciente seleccionado (ID):", idPaciente);
                console.log("Nombre del paciente:", nombrePaciente);
            });

            var pacienteDefault = $("#paciente").val();
            var nombrePacienteDefault = $("#paciente option:selected").text();
            console.log("Paciente seleccionado (ID):", pacienteDefault);
            console.log("Nombre del paciente:", nombrePacienteDefault);
        },
        error: function (error) {
            console.error("Error al cargar los pacientes: " + error);
        }
    });
}

function cargarSedes() {
    $.ajax({
        url: "/sede/listar",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);

            $("#sede").empty();

            $.each(data, function (index, sede) {
                $("#sede").append('<option value="' + sede.idsede + '">' + sede.nomsede + '</option>');
            });

            mostrarPaso(2);

            $("#sede").change(function () {
                var idSede = $("#sede").val();
                var nombreSede = $("#sede option:selected").text();
                console.log("Sede seleccionada (ID):", idSede);
                console.log("Nombre de la sede:", nombreSede);
            });

            var sedeDefault = $("#sede").val();
            var nombreSedeDefault = $("#sede option:selected").text();
            console.log("Sede seleccionada (ID):", sedeDefault);
            console.log("Nombre de la sede:", nombreSedeDefault);
        },
        error: function (error) {
            console.error("Error al cargar las sedes: " + error);
        }
    });
}

function cargarEspecialidades() {
    $.ajax({
        url: "/especialidad/listar",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#especialidad").empty();
            console.log(data);

            $.each(data, function (index, especialidad) {
                var option = "<option value='" + especialidad.idespecialidad + "'>" + especialidad.nomespecialidad + "</option>";
                $("#especialidad").append(option);
            });

            mostrarPaso(3);
        },
        error: function (error) {
            console.error("Error al cargar las especialidades: " + error);
        }
    });
}

function cargarMedicosPorEspecialidad() {
    var idEspecialidad = $("#especialidad").val();

    if (idEspecialidad) {
        console.log("Especialidad seleccionada (ID):", idEspecialidad);

        $.ajax({
            url: "/medico/buscarPorEspecialidad",
            type: "GET",
            data: { especialidadId: idEspecialidad },
            dataType: "json",
            success: function (data) {
                $("#medico").empty();
                $.each(data, function (index, medico) {
                    var option = "<option value='" + medico.idmedico + "'>" + medico.nommedico + " " + medico.apellido + "</option>";
                    $("#medico").append(option);
                });

                mostrarPaso(4);

                var primerMedico = data[0];
                if (primerMedico) {
                    $("#medico").val(primerMedico.idmedico);
                }

                // Llamar a la función para cargar fechas y horas disponibles
                var idMedicoSeleccionado = $("#medico").val();
                cargarFechasYHorasDisponibles(idMedicoSeleccionado);

                $("#medico").change(function () {
                    var idMedicoSeleccionado = $("#medico").val();
                    var nombreMedicoSeleccionado = $("#medico option:selected").text();

                    // Mostrar en la consola la información del médico seleccionado
                    console.log("Médico seleccionado (ID):", idMedicoSeleccionado);
                    console.log("Nombre del médico:", nombreMedicoSeleccionado);

                    // Llamar a la función para cargar fechas y horas disponibles al seleccionar un médico
                    cargarFechasYHorasDisponibles(idMedicoSeleccionado);
                });
            },
            error: function (error) {
                console.error("Error al cargar médicos:", error);
            }
        });
    } else {
        console.error("No se ha seleccionado una especialidad");
    }
}

function cargarFechasYHorasDisponibles(idMedico) {
    $.ajax({
        url: "/horario/fechasYHorasDisponibles",
        type: "GET",
        data: { medicoId: idMedico },
        dataType: "json",
        success: function (data) {
            idhorario = data.idHorario;
            console.log("ID de Horario recibido:", idhorario);
            console.log("Fechas recibidas:", data.fechas);
            console.log("Horas recibidas:", data.horas);
            $("#fecha").empty();
            $("#hora").empty();

            $.each(data.fechas, function (index, fecha) {
                var optionFecha = "<option value='" + fecha + "'>" + fecha + "</option>";
                $("#fecha").append(optionFecha);
            });

            $.each(data.horas, function (index, hora) {
                var optionHora = "<option value='" + hora + "'>" + hora + "</option>";
                $("#hora").append(optionHora);
            });

            var fechaDefault = data.fechas[0];
            var horaDefault = data.horas[0];
            $("#fecha").val(fechaDefault);
            $("#hora").val(horaDefault);

            console.log("Fecha por defecto:", fechaDefault);
            console.log("Hora por defecto:", horaDefault);

        },
        error: function (error) {
            console.error("Error al cargar fechas y horas disponibles:", error);
        }
    });
}

