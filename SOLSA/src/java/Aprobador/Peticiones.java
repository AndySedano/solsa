package Aprobador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Peticiones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("aprobador") == false) {
            response.sendRedirect("../Login");
            return;
        }

        List<Beans.Peticion> peticiones = new ArrayList<>();

        try (Connection con = Helpers.DB.newConnection(this)) {

            int idEmpresa = 0;
            try (PreparedStatement ps = con.prepareStatement("Select idEmpresa from Usuario where username = ?;")) {
                ps.setString(1, session.getAttribute("username").toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idEmpresa = rs.getInt("idEmpresa");
                }
            }

            try (PreparedStatement ps = con.prepareStatement("select * from VistaPeticiones where idEmpresa = ?;")) {
                ps.setInt(1, idEmpresa);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Beans.Peticion peticion = new Beans.Peticion();
                    peticion.setId(rs.getInt("id"));
                    peticion.setResponsable(rs.getString("username"));
                    peticion.setDate(rs.getString("fecha"));
                    peticion.setEstado(rs.getString("estado"));
                    peticiones.add(peticion);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("peticiones", peticiones);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Aprobador/Peticiones.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("aprobador") == false) {
            response.sendRedirect("../Login");
            return;
        }

        List<Beans.Peticion> peticiones = new ArrayList<>();

        try (Connection con = Helpers.DB.newConnection(this)) {

            String buscar;
            String sql;
            int idEmpresa = 0;
            try (PreparedStatement ps = con.prepareStatement("Select idEmpresa from Usuario where username = ?;")) {
                ps.setString(1, session.getAttribute("username").toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idEmpresa = rs.getInt("idEmpresa");
                }
            }

            if ((buscar = request.getParameter("buscar")).equals("")) {
                request.setAttribute("buscar", buscar);
                sql = "select * from VistaPeticiones where idEmpresa = ?;";
            } else {
                String query = request.getParameter("loquequieras").equals("1") ? "username" : "estado";
                sql = "select * from VistaPeticiones where idEmpresa = ? and " + query + "=?;";

            }
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                if (buscar.equals("")) {
                    ps.setInt(1, idEmpresa);
                } else {
                    ps.setInt(1, idEmpresa);
                    ps.setString(2, request.getParameter("buscar"));
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Beans.Peticion peticion = new Beans.Peticion();
                    peticion.setId(rs.getInt("id"));
                    peticion.setResponsable(rs.getString("username"));
                    peticion.setDate(rs.getString("fecha"));
                    peticion.setEstado(rs.getString("estado"));
                    peticiones.add(peticion);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("peticiones", peticiones);
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Aprobador/Peticiones.jsp");
        disp.include(request, response);

    }
}
