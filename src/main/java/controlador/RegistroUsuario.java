package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
public class RegistroUsuario extends HttpServlet {

    private final Pattern textoValido = Pattern.compile("^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$");
    private final Pattern usuarioValido = Pattern.compile("^[a-zA-Z0-9_]+$");
    private final Pattern correoValido = Pattern.compile("^[^@\\s]+@(gmail\\.com|hotmail\\.com|yahoo\\.com|outlook\\.com)$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recibir datos del formulario
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String biografia = request.getParameter("biografia");
        String fechaNacimiento = request.getParameter("fecha-nacimiento");
        String pais = request.getParameter("pais");
        String nombreUsuario = request.getParameter("nombre-usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String confirmarContrasena = request.getParameter("confirmar-contrasena");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Validar que las contraseñas coincidan
            if (!contrasena.equals(confirmarContrasena)) {
                out.println("<h2>Error</h2><p>Las contraseñas no coinciden.</p>");
                return;
            }

            // Validar longitud mínima de la contraseña
            if (contrasena.length() < 8) {
                out.println("<h2>Error</h2><p>La contraseña debe tener al menos 8 caracteres.</p>");
                return;
            }

            // Validar nombre y apellidos (solo letras y espacios)
            if (!textoValido.matcher(nombre).matches()) {
                out.println("<h2>Error</h2><p>El nombre contiene caracteres no permitidos.</p>");
                return;
            }

            if (!textoValido.matcher(apellidos).matches()) {
                out.println("<h2>Error</h2><p>Los apellidos contienen caracteres no permitidos.</p>");
                return;
            }

            // Validar nombre de usuario (letras, números y guión bajo)
            if (!usuarioValido.matcher(nombreUsuario).matches()) {
                out.println("<h2>Error</h2><p>El nombre de usuario contiene caracteres no permitidos.</p>");
                return;
            }

            // Validar correo
            if (!correoValido.matcher(correo).matches()) {
                out.println("<h2>Error</h2><p>El correo debe ser válido y terminar en @gmail.com, @hotmail.com, @yahoo.com o @outlook.com.</p>");
                return;
            }

            // Mostrar datos registrados
            out.println("<h2>Registro exitoso (cuenta no activada)</h2>");
            out.println("<p>Hemos enviado un correo de confirmación a: " + correo + ". Por favor, verifique su cuenta para poder iniciar sesión.<p>");
            out.println("<p>Usuario registrado: " + nombre + " " + apellidos + "</p>");
            out.println("<p>Usuario: " + nombreUsuario + "</p>");
            out.println("<p>Correo: " + correo + "</p>");
            out.println("<p>Biografía: " + biografia + "</p>");
            out.println("<p>Fecha de nacimiento: " + fechaNacimiento + "</p>");
            out.println("<p>País: " + pais + "</p>");
            

        }
    }
}
