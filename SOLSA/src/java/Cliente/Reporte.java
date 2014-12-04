package Cliente;

import Aprobador.Peticion;
import Beans.Producto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class Reporte extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("tipo").equals("cliente") == false) {
            response.sendRedirect("../Login"); return;
        }
        

        List<Producto> productos = new ArrayList<>();
        try (Connection con = Helpers.DB.newConnection(this)){
            java.sql.Date fechaInicio = null;
            java.sql.Date fechaFin = null;
            String sql;
            
            if(request.getParameter("fechaInicio") != null && request.getParameter("fechaFin") != null){
                SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
                request.setAttribute("fechaInicio", request.getParameter("fechaInicio"));
                request.setAttribute("fechaFin", request.getParameter("fechaFin"));
                request.setAttribute("conFechas", true);
                fechaInicio = new java.sql.Date(format.parse(request.getParameter("fechaInicio")).getTime());
                fechaFin = new java.sql.Date(format.parse(request.getParameter("fechaFin")).getTime());
                            
            }
            
            if(fechaInicio != null){
                sql = "select count(*) as numAprobados from Usuario, Peticion where Peticion.estado = 'aprobado' and Usuario.username = ?;"
                + "and fecha > ? and fecha < ?;";
            }
            else{
                String username = (String) session.getAttribute("userneme");
                sql = "select count(*) as numAprobados from Usuario, Peticion where estado = 'aprobado' and Usuario.username = '" + username + "';";                
            }
            
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if(fechaInicio != null){
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                    ps.setString(3, (String) session.getAttribute("username"));
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numAprobados", rs.getInt("numAprobados"));
            }
            

            if(fechaInicio != null){
                sql = "select count(*) as numAprobados from Peticion where estado = 'aprobado';"
                + "and fecha > ? and fecha < ?;";
            }
            else{
                sql = "select count(*) as numAprobados from Peticion where estado = 'aprobado'";                
            }
            
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if(fechaInicio != null){
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                    
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numAprobados", rs.getInt("numAprobados"));
            }            
            
            if(fechaInicio != null){
                sql = "select count(*) as numCancelado from Peticion where estado = 'cancelado' "
                        +"and fecha > ? and fecha < ?;";
            }
            else{
                sql = "select count(*) as numCancelado from Peticion where estado = 'cancelado'";
            }
            
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if(fechaInicio != null){
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numCancelado", rs.getInt("numCancelado"));
            }
            
            
            if(fechaInicio != null){
                sql = "select count(*) as numEntregado from Peticion where estado = 'entregado' "
                        +"and fecha > ? and fecha < ?;";
            }
            else{
                sql = "select count(*) as numEntregado from Peticion where estado = 'entregado'";
            }
            
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if(fechaInicio != null){
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numEntregado", rs.getInt("numEntregado"));
            }       
            
  
            if(fechaInicio != null){
                sql = "select count(*) as numEspera from Peticion where estado = 'espera' "
                        +"and fecha > ? and fecha < ?;";
            }
            else{
                sql = "select count(*) as numEspera from Peticion where estado = 'espera'";
            }
            
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if(fechaInicio != null){
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("numEspera", rs.getInt("numEspera"));
            }                 
            
            if(fechaInicio != null){
                sql = "select sum (costoTotal) as total from Pedido_PrecioTotal where estado = 'entregado'"
                        + "and fecha > ? and fecha < ?;";
            }
            else{
                sql = "select sum(precioTotal) as total from Pedido)PrecioTotal where estado = 'entregado';";
            }
            try(PreparedStatement ps = con.prepareStatement(sql))
            {
                if(fechaInicio != null)
                {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                rs.next();
                request.setAttribute("total", Helpers.Money.toString(rs.getInt("total")));                
            }
            
            if(fechaInicio != null){
                sql = "SELECT Usuario.username, Producto.nombre as producto, count(*) as Cantidad FROM Producto\n" +
                    "INNER JOIN Carrito_Producto ON Carrito_Producto.idProducto = Producto.idProducto\n" +
                    "INNER JOIN Carrito ON Carrito_Producto.Carrito_idCarrito=Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "WHERE Usuario.username = '?'\n;" + 
                    "and fecha > ? and fecha < ? GROUP BY Producto.idProducto;";
            }
            else{
                sql = "SELECT Usuario.username, Producto.nombre as producto, count(*) as Cantidad FROM Producto\n" +
                    "INNER JOIN Carrito_Producto ON Carrito_Producto.idProducto = Producto.idProducto\n" +
                    "INNER JOIN Carrito ON Carrito_Producto.Carrito_idCarrito=Carrito.idCarrito\n" +
                    "INNER JOIN Usuario ON Carrito.Usuario_Username = Usuario.username\n" +
                    "WHERE Usuario.username = 'cliente' GROUP BY Producto.idProducto;";
            }
            try(PreparedStatement ps = con.prepareStatement(sql))
            {
                if(fechaInicio != null)
                {
                    ps.setString(1, (String) session.getAttribute("username"));
                    ps.setDate(2, fechaInicio);
                    ps.setDate(3, fechaFin);
                }
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Producto producto = new Producto();
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCantidad(rs.getInt("Cantidad"));
                    
                    productos.add(producto);
                }
            }
            
           
        }
        catch (ParseException | SQLException ex)
        {
            Logger.getLogger(Aprobador.Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }        

        RequestDispatcher disp = getServletContext().getRequestDispatcher("/Cliente/Reporte.jsp");
        disp.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
