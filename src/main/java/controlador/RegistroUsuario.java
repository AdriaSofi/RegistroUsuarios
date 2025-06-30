package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RegistroUsuario", urlPatterns = {"/RegistroUsuario"})
public class RegistroUsuario extends HttpServlet {

    // Procesa el formulario de registro
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

        // Validación para contraseñas iguales
        if (!contrasena.equals(confirmarContrasena)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2>Error</h2>");
                out.println("<p>Las contraseñas no coinciden.</p>");
            }
            return;
        }

        // Mostrar datos registrados al usuario
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h2>Registro exitoso</h2>");
            out.println("<p>Usuario registrado: " + nombre + " " + apellidos + "</p>");
            out.println("<p>Biografía: " + biografia + "</p>");
            out.println("<p>Fecha de nacimiento: " + fechaNacimiento + "</p>");
            out.println("<p>País: " + pais + "</p>");

            out.println("<p>Correo: " + correo + "</p>");
            out.println("<p>Usuario: " + nombreUsuario + "</p>");
        }
    }
}
