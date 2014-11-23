package Cliente;

import Beans.Foto;
import Beans.Producto;
import Ventas.EstadoPedido;
import java.io.IOException;
import java.sql.Connection;
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

public class Productos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false) {
            response.sendRedirect("../Login"); return;
        }

        try (Connection con = Helpers.DB.newConnection(this)) {

            int idDepartamento = 0;

            try (PreparedStatement ps = con.prepareStatement("SELECT idDepartamento from Usuario where username = ?;")) {
                ps.setString(1, session.getAttribute("username").toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idDepartamento = rs.getInt("idDepartamento");
                }
            }

            PreparedStatement ps = con.prepareStatement("SELECT Producto.idProducto AS id, Producto.nombre AS nombre, descripcion, precio, tipo, ProductoDepartamento.idDepartamento, Fotografia.idFotografia as idFoto, Fotografia.nombre AS nombreFoto\n"
                    + "FROM Producto JOIN ProductoDepartamento ON Producto.idProducto = ProductoDepartamento.idProducto JOIN Fotografia ON Fotografia.idFotografia = Producto.Fotografia_idFotografia\n"
                    + "WHERE idDepartamento = ?;");

            ps.setInt(1, idDepartamento);

            ResultSet rs = ps.executeQuery();

            ArrayList<Producto> beans = new ArrayList<>();

            while (rs.next()) {
                Producto bean = new Producto();
                bean.setIdProducto(rs.getInt("id"));
                bean.setNombre(rs.getString("nombre"));
                bean.setDescripcion(rs.getString("descripcion"));
                bean.setPrecio(Helpers.Money.toString(rs.getInt("precio")));
                bean.setTipo(rs.getString("tipo"));
                Foto f = new Foto();
                f.setNombre(rs.getString("nombreFoto"));
                f.setUrl(request.getContextPath() + "/Images/" + rs.getString("idFoto") + "-" + f.getNombre());
                bean.setFoto(f);
                beans.add(bean);
            }

            request.setAttribute("inf", beans);

        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Productos.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
