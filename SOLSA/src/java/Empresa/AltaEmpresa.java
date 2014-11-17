package Empresa;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HttpSession session = request.getSession();
        String url = getInitParameter("url");
        String user = getInitParameter("user");
        String pass = getInitParameter("pass");
        boolean st = false;
        //Logo
        FileInputStream logo1 = null;
        String nombreLogo = request.getParameter("nombreLogo");
        File imagen = new File(request.getParameter("imagen"));
        logo1 = new FileInputStream(imagen);
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String RFC = request.getParameter("RFC");
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
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
                            ps2.setString(3, nombre);
                            ps2.setString(4, direccion);
                            ps2.setString(5, telefono);
                            ps2.setString(6, RFC);
                            ps2.setInt(7, idContrato);
                            ResultSet rs2 = ps2.executeQuery();
                            while (rs2.next()) {
                                
                            }
                        }
                    }
                }
            }
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AltaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
