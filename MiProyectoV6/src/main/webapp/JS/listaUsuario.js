/** Se utiliza para llamar a la función llamada() */
window.addEventListener("DOMContentLoaded", function() {
	llamada();
});

function llamada() {
	fetch('GestionUsuarios?op=1')
		.then(response => response.json())
		.then(data => pintarTabla(data));
}


function pintarTabla(datos) {
	let html = "<table>";
	html += "<tr><td>Id</td><td>Nombre</td><td>Apellidos</td><td>Teléfono</td><td>Email</td><td>Editar</td><td>Borrar</td></tr>";
	for (let i = 0; i < datos.length; i++) {

		html += "<tr><td>" + datos[i].id + "</td>";
		html += "<td>" + datos[i].nombre + "</td>";
		html += "<td>" + datos[i].apellidos + "</td>";
		html += "<td>" + datos[i].telefono + "</td>";
		html += "<td>" + datos[i].email + "</td>";
		html += "<td><a href='modificarUsuario.html?id=" + datos[i].id + "&op=2'><img src='img/editicon.jpg'></a></td>";
		html += "<td><a href='javascript:borrar(" + datos[i].id + ")'><img src='img/deleteicon.jpg'></a></td>";
		html += "</tr>";
	}

	html += "</table>";
	document.getElementById("listado").innerHTML = html;
}

function borrar(id) {

	if (confirm("¿Seguro que quieres borrar?")) {
		fetch('GestionUsuarios?id=' + id + '&op=3')
			.then(response => response.json())
			.then(data => pintarTabla(data))
	} else {
	}
}

function busquedaPorTipo(tipo) {
	fetch('GestionUsuarios?&op=4&tipoUsuario=' + tipo)
		.then(response => response.json())
		.then(data => pintarTabla(data))
}
