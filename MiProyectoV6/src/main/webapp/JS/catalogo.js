/** Función que se utiliza para llamar a la función llamada() con el parámetro "GestionProductos?op=1" */
window.addEventListener("DOMContentLoaded", function() {
	llamada("GestionProductos?op=1");
});

/** Función que realiza una solicitud al servidor para obtener todos los productos (op=1)
 * Una vez que la solicitud se completa con éxito, la función llama a pintarResultados(datos) */
function llamada() {
	fetch('GestionProductos?op=1')
		.then(res => res.json())
		.then(res => pintarResultados(res));
}

/**Función que toma los datos de los productos recibidos como parámetro y los presenta en divisiones individuales */
function pintarResultados(datos) {
	let container = document.getElementById("resultados");
	container.innerHTML = ""; // Limpiar el contenido existente

	for (var i = 0; i < datos.length; i++) {
		// Crear un nuevo contenedor div
		let div = document.createElement("div");
		div.classList.add("producto"); // Agregar una clase para el estilo si es necesario

		// Agregar la imagen
		let img = document.createElement("img");
		img.src = "fotos/" + datos[i].foto;
		div.appendChild(img);

		// Agregar el nombre
		let nombre = document.createElement("p");
		nombre.textContent = datos[i].nombre;
		div.appendChild(nombre);

		// Agregar la presentación
		let presentacion = document.createElement("p");
		presentacion.textContent = datos[i].presentacion;
		div.appendChild(presentacion);

		// Agregar los detalles
		let detalles = document.createElement("p");
		detalles.textContent = datos[i].detalles;
		div.appendChild(detalles);

		// Agregar el laboratorio
		let proveedor = document.createElement("p");
		proveedor.textContent = datos[i].proveedor;
		div.appendChild(proveedor);

		// Agregar el precio unitario
		let precio = document.createElement("p");
		precio.textContent = "Precio: " + datos[i].precio_uni + " € ";
		div.appendChild(precio);

		// Agregar el botón de compra 
		let comprarBtn = document.createElement("button");
		comprarBtn.textContent = "Comprar";
		div.appendChild(comprarBtn);

		// Agregar el nuevo contenedor al contenedor principal
		container.appendChild(div);
	}
}

/** Función que se utiliza para buscar productos por tipo
 *  Una vez que la solicitud se completa con éxito, la función llama a pintarResultados(data) */
function busquedaPorTipo(tipo) {
	fetch('GestionProductos?&op=4&tipoProducto=' + tipo)
		.then(response => response.json())
		.then(data => pintarResultados(data))
}
