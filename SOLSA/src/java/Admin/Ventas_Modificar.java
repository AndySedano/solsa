package Admin;

import Beans.Venta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Ventas_Modificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); return;
        }
        
        try (Connection con = Helpers.DB.newConnection(this)) {
            PreparedStatement ps = con.prepareStatement("SELECT nombre, direccion, telefono "
                    + "FROM Usuario WHERE username=? AND tipo='ventas';");
            ps.setString(1, request.getParameter("username"));
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Venta venta = new Venta();
                venta.setNombre(rs.getString("nombre"));
                venta.setDireccion(rs.getString("direccion"));
                venta.setTelefono(rs.getString("telefono"));
                request.setAttribute("venta", venta);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ventas_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Ventas_Modificacion.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login"); 
            return; 
        }
                
        String username = request.getParameter("username");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String mob = request.getParameter("submit");
        String sqlModificar = "UPDATE Usuario SET nombre=?, direccion=?, telefono=? WHERE username=? AND tipo='ventas';";
        String sqlDelete = "DELETE FROM Usuario WHERE username=? AND tipo='ventas';";

        try (Connection con = Helpers.DB.newConnection(this)) {
            int query;
            switch (mob) {
                case "modificar":
                    try (PreparedStatement ps = con.prepareStatement(sqlModificar)) {
                        ps.setString(1, nombre);
                        ps.setString(2, direccion);
                        ps.setString(3, telefono);
                        ps.setString(4, username);
                        query = ps.executeUpdate();
                    }
                    if (query==1){
                        request.setAttribute("message", "El usuario " + username + " ha sido modificado.");
                        doGet(request, response);
                    }
                break;
                case "borrar":
                    try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
                        ps.setString(1, username);
                        query = ps.executeUpdate();
                    }
                    if (query==1){
                        request.setAttribute("message", "El usuario " + username + " ha sido borrado.");
                        doGet(request, response);
                    }
                break;
            }
            
//            request.setAttribute("message", "El usuario " + username + " ha sido borrado.");
//            doGet(request, response);
            
        }
        catch (SQLException ex) {
            Logger.getLogger(Ventas_Modificar.class.getName()).log(Level.SEVERE, null, ex);
            
            request.setAttribute("error", "true");
            request.setAttribute("message", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
            doGet(request, response);
        }
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Ventas_Modificacion.jsp");
        disp.include(request, response);    
    } 
}    