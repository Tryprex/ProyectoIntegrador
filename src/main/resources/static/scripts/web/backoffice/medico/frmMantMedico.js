function buscarMedicos() {
    // Obtener el valor seleccionado en el combo de especialidades
    var especialidadId = $("#especialidadId").val();

    // Realizar la solicitud Ajax
    $.ajax({
        url: '/medico/buscarPorEspecialidad',
        type: 'GET',
        data: { especialidadId: especialidadId },
        dataType: 'json', // Especificar que esperamos JSON como respuesta
        success: function (result) {
            // Limpiar el cuerpo de la tabla
            $("#tblmedico tbody").empty();

            // Iterar sobre los médicos en el resultado y agregar filas a la tabla
            $.each(result, function (index, medico) {
                var row = "<tr>" +
                    "<td>" + medico.nommedico + "</td>" +
                    "<td>" + medico.apellido + "</td>" +
                    "<td>" + medico.dni + "</td>" +
                    "<td>" + medico.cmp + "</td>" +
                    "<td>" + medico.especialidad.nomespecialidad + "</td>" +
                    "</tr>";

                $("#tblmedico tbody").append(row);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error al realizar la búsqueda. Detalles:", xhr.responseText);
        }
    });
}
