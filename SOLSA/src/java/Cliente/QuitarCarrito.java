package Cliente;
/*Quita productos al carrito*/

import Beans.CarritoBean;
import Beans.Producto;
import java.io.IOException;
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
            CarritoBean c = (CarritoBean) session.getAttribute("carrito");
            int id = Integer.parseInt(request.getParameter("id"));
            c.remove(id);
            session.setAttribute("carrito", c);
        } else {
            request.setAttribute("mensaje", "Carrito Vacio");
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Carrito.jsp");
        disp.include(request, response);
    }

}
