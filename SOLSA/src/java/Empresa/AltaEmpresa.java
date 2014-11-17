package Empresa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */ 
public class AltaEmpresa extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Datos de la session y la conexion
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");
        boolean st = false;
        //Logo
        FileInputStream logo1 = null;
        String nombreLogo = request.getParameter("nombreLogo");
        File imagen = new File(request.getParameter("imagen"));
        logo1 = new FileInputStream(imagen);
        //Empresa
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String RFC = request.getParameter("RFC");
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        //Inserta foto y empresa
        String sql1 = "INSERT INTO Fotografia (nombre, imagen) VALUES (?, ?);";
        String sql2 = "INSERT INTO Empresa (nombre, direccion, telefono, RFC, Contrato_idContrato, Fotografia_idFotografia) VALUES (?, ?, ?, ?, ?, ?);";
        
        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                try (PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                    ps1.setString(1, nombreLogo);
                    ps1.setBlob(2, logo1);
                    ResultSet rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                            ps2.setString(1, nombre);
                            ps2.setString(2, direccion);
                            ps2.setString(3, telefono);
                            ps2.setString(4, RFC);
                            ps2.setInt(5, idContrato);
                            ps2.setInt(6, Statement.RETURN_GENERATED_KEYS);
                            ResultSet rs2 = ps2.executeQuery();
                            while (rs2.next()) {
                                st = true;
                                session.setAttribute("nombre", session.getAttribute("nombre"));
                            }
                        }
                    }
                }
                if (st) {
                    request.setAttribute("res", "La empresa " + session.getAttribute("nombre"));
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("lapagina.jsp");
                    rd.include(request, response);
                } else {
                    request.setAttribute("res", "Lo sentimos, ha ocurrido un error, ingrese los datos nuevamente");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("lapagina.jsp");
                    rd.include(request, response);
                }
            }
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AltaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
