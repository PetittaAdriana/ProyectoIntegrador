/** Función que prepara la web con los datos que se solicitan */
window.addEventListener("DOMContentLoaded", function() {
	let id = getParameterByName("id");
	let op = getParameterByName("op");
	llamada(id, op);
});

/** Función que permite validar los campos antes de enviarlos dando una alerta o con un texto */
function validarFormulario() {
	let campos = ['nombre', 'apellidos', 'telefono', 'email', 'contrasena', 'contrasena2'];
	let ok = true;

	campos.forEach(function(campo) {
		let valor = document.getElementById(campo).value;
		let campoElemento = document.getElementById(campo);
		let mensajeError = campoElemento.nextSibling;

		if (valor === "") {
			ok = false;
			campoElemento.style.background = "rgb(255, 223, 223)";
			if (!mensajeError || mensajeError.textContent !== " Campo obligatorio") {
				let mensajeElemento = document.createElement("span");
				mensajeElemento.textContent = " Campo obligatorio";
				mensajeElemento.style.color = "black";
				campoElemento.parentNode.insertBefore(mensajeElemento, campoElemento.nextSibling);
			}
		} else {
			campoElemento.style.background = "";
			if (mensajeError && mensajeError.textContent === " Campo obligatorio") {
				campoElemento.parentNode.removeChild(mensajeError);
			}
		}

	});

	var contrasena = document.getElementById('contrasena').value;
	if (contrasena.length < 4) {
		alert('La contraseña no es válida, debe tener 4 o mas caracteres');
		return;
	}

	var contrasena2 = document.getElementById('contrasena2').value;
	if (contrasena2 !== contrasena) {
		alert('Las contraseñas no coinciden')
		return
	}

	if (ok) {
		document.getElementById("altas").submit();
	}
}

/** Función para otener el valor de un parametro en el GET */

function llamada(id, op) {
	fetch('GestionUsuarios?id=' + id + "&op=" + op)
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
	document.getElementById("id").value = datos.id;
	document.getElementById("nombre").value = datos.nombre;
	document.getElementById("apellidos").value = datos.apellidos;
	document.getElementById("telefono").value = datos.telefono;
	document.getElementById("email").value = datos.email;
	document.getElementById("permiso").value = datos.permiso;
}


