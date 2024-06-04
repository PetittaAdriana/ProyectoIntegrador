/** Función que prepara la web con los datos que se solicitan */
window.addEventListener("DOMContentLoaded", function() {
	let cod_producto = getParameterByName("cod_producto");
	let op = getParameterByName("op");
	llamada(cod_producto, op);
});

/** Función para otener el valor de un parametro en el GET */
function llamada(cod_producto, op) {
	fetch('GestionProductos?cod_producto=' + cod_producto + "&op=" + op)
		.then(response => response.json())
		.then(data => pintarFormulario(data));
}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

/** Asigna los valores de los datos recibidos a los campos de html */
function pintarFormulario(datos) {
	document.getElementById("cod_producto").value = datos.cod_producto;
	document.getElementById("tipo").value = datos.tipo;
	document.getElementById("nombre").value = datos.nombre;
	document.getElementById("presentacion").value = datos.presentacion;
	document.getElementById("detalles").value = datos.detalles;
	document.getElementById("proveedor").value = datos.proveedor;
	document.getElementById("precio_uni").value = datos.precio_uni;
	document.getElementById("foto").value = datos.foto;
}

function goBack() {
            window.location.href = "ofertas.html"; 
}
