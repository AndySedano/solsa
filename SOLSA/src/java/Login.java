import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean st = false;
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        HttpSession session = request.getSession();
        String url = getInitParameter("url");
        String usuario_base = getInitParameter("user");
        String pass_base = getInitParameter("pass");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(url, usuario_base, pass_base)) {

                try (PreparedStatement ps = con.prepareStatement("SELECT * from usuarios WHERE username=? AND password=?")) {
                    ps.setString(1, user);
                    ps.setString(2, pass);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        st = true;
                        session.setAttribute("nombre", (rs.getString("nombre")));
                        session.setAttribute("departamento", (rs.getString("departamento").toUpperCase()));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (st) {

            request.setAttribute("res", "Bienvenido " + session.getAttribute("nombre"));
            /*switch (request.getAttribute("departamento").toString()){
             System.out.println(request.getAttribute("departamento").toString());
                
                
  
             }*/
            if (session.getAttribute("departamento").equals("SISTEMASSOLSA")) {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/Sistemas/ignorar.html");
                disp.include(request, response);
            } else {
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/gerente/bienvenido.jsp");
                disp.include(request, response);
            }
        } else {
            request.setAttribute("res", "Usuario o Contrase&ntilde;a Incorrectos");
            RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
            disp.include(request, response);
        }
    }
}
