function mostrarImagen(event) {
    const archivo = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function (e) {
        const imagenPreview = document.getElementById('foto-preview');
        imagenPreview.style.display = 'block'; // Mostrar la imagen de vista previa
        imagenPreview.src = e.target.result; // Establecer la imagen seleccionada como fuente
    };

    if (archivo) {
        reader.readAsDataURL(archivo);
    }
}
