package Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cliente_Modificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); return;
        }
        
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Modificacion.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request .setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("superadmin") == false)
        {
            response.sendRedirect("../Login"); return;
        }

        String username = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        int departamento = Integer.parseInt(request.getParameter("departamento"));
        String mob = request.getParameter("submit");
        String sqlModificar = "UPDATE Usuario SET nombre=?, direccion=?, telefono=? departamento=? WHERE username=? AND tipo='cliente';";
        String sqlDelete = "DELETE FROM Usuario WHERE username=? AND tipo='cliente';";

        try (Connection con = Helpers.DB.newConnection(this)) {
            if (mob.equals("modificar")) {
                try (PreparedStatement ps = con.prepareStatement(sqlModificar)) {
                    ps.setString(1, nombre);
                    ps.setString(2, direccion);
                    ps.setString(3, telefono);
                    ps.setInt(4, departamento);
                    ps.setString(5, username);
                    ps.executeUpdate();
                } 
            } else {
                try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
                    ps.setString(1, username);
                    ps.executeUpdate();
                } 
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Cliente_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}    