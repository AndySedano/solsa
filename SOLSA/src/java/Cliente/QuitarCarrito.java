package Cliente;
/*Quita productos al carrito*/

import Beans.CarritoBean;
import Beans.Foto;
import Beans.Producto;
import Ventas.EstadoPedido;
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

public class QuitarCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false) {
            response.sendRedirect("../Login");
        }

        if (session.getAttribute("carrito") != null) {
            request.setAttribute("inf", ((CarritoBean) session.getAttribute("carrito")).getProductos());
        } else {
            request.setAttribute("mensaje", "Carrito Vacio");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Carrito.jsp");
        disp.include(request, response);
    }

}
