/** Se utiliza para llamar a la función llamada() */
window.addEventListener("DOMContentLoaded", function() {
	llamada();
});

/** Función que realiza una solicitud al servidor para obtener todos los productos (op=1)
 * Una vez que la solicitud se completa con éxito, la función llama a pintarResultados(datos) */
function llamada() {
	fetch('GestionProductos?op=1')
		.then(res => res.json())
		.then(res => pintarResultados(res));
}

/**Función que toma los datos de los productos recibidos como parámetro y los presenta en una tabla HTML */

function pintarResultados(datos) {
	let html = "<table>";
	html += "<tr><td>Imagen</td><td>Id</td><td>Tipo</td><td>Nombre</td><td>Presentación</td><td>Detalles</td><td>Proveedor</td><td>Precio</td><td>Editar</td><td>Borrar</td></tr>";
	for (var i = 0; i < datos.length; i++) {

		html += "<tr><td><img src='fotos/" + datos[i].foto + "'></td>";
		html += "<td>" + datos[i].cod_producto + "</td>";
		html += "<td>" + datos[i].tipo + "</td>";
		html += "<td>" + datos[i].nombre + "</td>";
		html += "<td>" + datos[i].presentacion + "</td>";
		html += "<td>" + datos[i].detalles + "</td>";
		html += "<td>" + datos[i].proveedor + "</td>";
		html += "<td>" + datos[i].precio_uni + "</td>";
		html += "<td><a href='modificarProducto.html?cod_producto=" + datos[i].cod_producto + "&op=2'><img src='img/editicon.jpg'></a></td>";
		html += "<td><a href='javascript:borrar(" + datos[i].cod_producto + ")'><img src='img/deleteicon.jpg'></a></td></tr>";
	}

	html += "</table>";
	document.getElementById("resultados").innerHTML = html;
}

/** Función que se utiliza para borrar un producto específico */
function borrar(cod_producto) {

	if (confirm("¿Seguro que quieres borrar?")) {
		fetch('GestionProductos?cod_producto=' + cod_producto + '&op=3')
			.then(response => response.json())
			.then(data => pintarResultados(data))
	} else {
	}
}

/** Función que se utiliza para buscar productos por tipo
 *  Una vez que la solicitud se completa con éxito, la función llama a pintarResultados(data) */
function busquedaPorTipo(tipo) {
	fetch('GestionProductos?&op=4&tipoProducto=' + tipo)
		.then(response => response.json())
		.then(data => pintarResultados(data))
}