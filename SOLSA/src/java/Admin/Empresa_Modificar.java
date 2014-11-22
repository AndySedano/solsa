package Admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class Empresa_Modificar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Empresa_Modificacion.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //sesion y conexion
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");
        boolean st = false;
        //logo
        FileInputStream logo1 = null;
        String nombreLogo = request.getParameter("nombreLogo");
        File imagen = new File(request.getParameter("imagen"));
        logo1 = new FileInputStream(imagen);
        //empresa
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String RFC = request.getParameter("RFC");
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        //foto y empresa
        String sql = "UPDATE Empresa SET nombre=?, direccion=?, telefono=?, RFC=?, idContrato=? WHERE idEmpresa=?";
        
        try{
            Class.forName("con.mysql.jdbc.Driver");
            try(Connection con = DriverManager.getConnection(url, user, pass)){
                try (PreparedStatement ps = con.prepareStatement(sql)){
                    ps.setString(1,nombre);
                    ps.setString(2, direccion);
                    ps.setString(3, telefono);
                    ps.setString(4, RFC);
                    ps.setInt(5, idContrato);
                    ps.setInt(6, idEmpresa);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                        session.setAttribute("nombre", session.getAttribute("nombre"));
                    }
                }
                if (st) {
                    request.setAttribute("res", "La empresa " + session.getAttribute("nombre") + " ha siido modificada.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/Empresa_Modificacion.jsp");
                    rd.include(request, response);
                            
                } else {
                    request.setAttribute("res", "Lo sentimos, ha ocurrido un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/Empresa_Modificacion.jsp");
                    rd.include(request, response);
                }
                con.close();
            } 
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Empresa_Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}