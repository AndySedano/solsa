package Cliente;
/*Ver y crear un carrito*/

import Beans.CarritoBean;
import Beans.Foto;
import Beans.Producto;
import SuperAdmin.AddUser;
import java.io.IOException;
import java.sql.Connection;
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

public class VerCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false)
        {
            response.sendRedirect("../Login"); return;
        }

        if (session.getAttribute("carrito") == null)
        {
            request.setAttribute("mensaje", "Carrito Vacio");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Carrito.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false)
        {
            response.sendRedirect("../Login"); return;
        }

        if (session.getAttribute("carrito") != null && request.getParameter("send").equals("add"))
        {
            String[] ids = request.getParameterValues("ids");
            String[] quantas = request.getParameterValues("quantas");
            CarritoBean c = (CarritoBean) session.getAttribute("carrito");
            c.actualizar(ids, quantas);
            int idCarrito = 0;

            try (Connection con = Helpers.DB.newConnection(this)) {
                //Query para crear la tabla Carrito
                try (PreparedStatement ps = con.prepareStatement("INSERT INTO Carrito (Usuario_Username) values (?);", Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, session.getAttribute("username").toString());
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();

                    if (rs.next()) {
                        idCarrito = rs.getInt(1);
                    }

                }

                //Queries para crear las tablas carrito_Producto
                for (Producto p : c.getProductos()) {

                    try (PreparedStatement ps = con.prepareStatement("INSERT INTO Carrito_Producto (Carrito_idCarrito, idProducto, cantidad) values (?,?,?);")) {
                        ps.setInt(1, idCarrito);
                        ps.setInt(2, p.getIdProducto());
                        ps.setInt(3, p.getCantidad());

                        ps.executeUpdate();

                    }
                }

                //Queries para crear la tabla peticion
                try (PreparedStatement ps = con.prepareStatement("INSERT INTO Peticion (fecha, estado, Carrito_idCarrito) values (curdate(), 'espera', ?);")) {
                    ps.setInt(1, idCarrito);
                    ps.executeUpdate();
                }

            } catch (SQLException ex) {
                Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("mensaje", "Peticion realizada");
        }
        else
        {
            session.setAttribute("carrito", null);
            request.setAttribute("mensaje", "Carrito Vacio");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Carrito.jsp");

        disp.include(request, response);
    }

}
