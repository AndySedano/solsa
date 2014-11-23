package Admin;

import Beans.Departamento;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.javatuples.Pair;

public class Cliente_Alta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("admin") == false) {
            response.sendRedirect("../Login");
        }
        
        try (Connection con = Helpers.DB.newConnection(this)) {
            Class.forName("con.mysql.jdbc.Driver");
            PreparedStatement ps = con.prepareStatement(
                    "SELECT Departamento.nombre AS nombre, "
                    + "Departamento.idDepartamento AS idDepartamento, "
                    + "Empresa.nombre AS empresa "
                    + "FROM Departamento, Empresa "
                    + "WHERE Departamento.idEmpresa=Empresa.idEmpresa;");
            ResultSet rs = ps.executeQuery();
            ArrayList<Departamento> beans = new ArrayList<>();
            
            if (rs.next()) {
                Departamento bean = new Departamento();
                bean.setNombreDepartamento(rs.getString("nombre"));
                bean.setIdDepartamento(rs.getInt("idDepartamento"));
                bean.setNombreEmpresa(rs.getString("empresa"));
                beans.add(bean);
            }
            
            request.setAttribute("inf", beans);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cliente_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Admin/Cliente_Alta.jsp");
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
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Pair<String, Integer> hash = Helpers.Login.createNewHash(password);
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String tipo = "cliente";
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        boolean st = false;
        String sql = "INSERT INTO Usuario (username, password, salt, nombre, direccion, telefono, tipo, idDepartamento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            Class.forName("con.mysql.jdbc.Driver");
            try (Connection con = Helpers.DB.newConnection(this)) {
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hash.getValue0());
                    ps.setInt(3, hash.getValue1());
                    ps.setString(4, nombre);
                    ps.setString(5, direccion);
                    ps.setString(6, telefono);
                    ps.setString(7, tipo);
                    ps.setInt(8, idDepartamento);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        st = true;
                        session.setAttribute("username", session.getAttribute("username"));
                    }
                }
                if (st) {
                    request.setAttribute("res", "El ususario " + session.getAttribute("username") + 
                            " ha sido registrado exitosamente.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Cliente_Alta.jsp");
                    rd.include(request, response);
                } else {
                    request.setAttribute("res", "Lo sentimos, hubo un error, ingrese los datos nuevamente...");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("Cliente_Alta.jsp");
                    rd.include(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cliente_Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
