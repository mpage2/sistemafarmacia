package com.mycompany.pro1.DAO;

import com.mycompany.pro1.conexion.Conexion;
import java.sql.Connection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.mycompany.pro1.modelo.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mycompany.pro1.vista.*;

public class CRUD_Producto {

    int obtenerIdCategoriaCombo = 0;
    InterProducto incat;
    String descripcionCategoria = "";
    double precio = 0.0;
    int porcentajeIgv = 0;
    double IGV = 0;

    /*
    * *********************************
        MÉTODO PARA GUARDAR PRODUCTO
    * *********************************
     */
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_producto values (?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); //id
            consulta.setString(2, objeto.getNombre());
            consulta.setInt(3, objeto.getCantidad());
            consulta.setDouble(4, objeto.getPrecio());
            consulta.setString(5, objeto.getDescripcion());
            consulta.setInt(6, objeto.getPorcentajeIgv());
            consulta.setInt(7, objeto.getIdCategoria());
            consulta.setInt(8, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar producto" + e);
        }
        return respuesta;
    }

    /*
    * ************************************************************
        MÉTODO PARA CONSULTAR SI EL PRODUCTO YA EXISTE EN LA BD
    * ************************************************************
     */
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar producto" + e);
        }
        return respuesta;
    }

    /*
    * ********************************************
            MÉTODO PARA ACTUALIZAR PRODUCTO
    * ********************************************
     */
    public boolean actualizar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("update tb_producto set nombre =?, cantidad=?, precio=?, descripcion =?, porcentajeIgv=?, IdCategoria= ?, estado = ? where IdProducto= ?;");
            consulta.setString(1, objeto.getNombre());
            consulta.setInt(2, objeto.getCantidad());
            consulta.setDouble(3, objeto.getPrecio());
            consulta.setString(4, objeto.getDescripcion());
            consulta.setInt(5, objeto.getPorcentajeIgv());
            consulta.setInt(6, objeto.getIdCategoria());
            consulta.setInt(7, objeto.getEstado());
            consulta.setInt(8, objeto.getIdProducto());
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto" + e);
        }
        return respuesta;
    }

    /*
    * ********************************************
            MÉTODO PARA ELIMINAR PRODUCTO
    * ********************************************
     */
    public void eliminar(int idProducto) {
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_producto where IdProducto = ?;");
            consulta.setInt(1, idProducto); //actualizamos el parametro con el codigo
            consulta.executeUpdate(); //actualizamos y ejecutamos la consulta.
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto" + e);
        }
    }

    /*
    * ************************************************
            MÉTODO PARA ACTUALIZAR STOCK DEL PRODUCTO
    * ************************************************
     */
    public boolean actualizarStock(Producto object, int idProducto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_producto set cantidad=? where IdProducto = '" + idProducto + "'");
            consulta.setInt(1, object.getCantidad());
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto" + e);
        }
        return respuesta;
    }

    /**
     * *************************************
     * MÉTODO PARA CARGAR CATEGORÍAS *************************************
     */
    public List<String> CargarComboCategorias() {
        Connection cn = (Connection) Conexion.conectar();
        Statement st;
        try {
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tb_categoria");
            List<String> listaCategoria = new ArrayList<>();
            while (rs.next()) {
               listaCategoria.add(rs.getString("descripcion"));
            }
            return listaCategoria;
        } catch (SQLException ex) {
            System.out.println("Error al cargar categorias" + ex);

        }
        return null;
    }

    /**
     * *************************************
     * MÉTODO PARA OBTENER CATEGORÍAS *************************************
     */
    public int IdCategoria(String nombreCategoria) {
        String sql = "select * from tb_categoria where descripcion = '" + nombreCategoria + "'";
        Statement st;
        try {
            Connection cn = (Connection) Conexion.conectar();
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                obtenerIdCategoriaCombo = rs.getInt("IdCategoria");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id categoria");
        }
        return obtenerIdCategoriaCombo;
    }

    /*
    * ***********************************************************
    *    METODO PARA MOSTRAR LOS PRODUCTOS REGISTRADOS
    * ***********************************************************
     */
    public void CargarTablaProducto(JTable tabla) {
        Connection cn = Conexion.conectar();
        String titulos[] = {"N°", "nombre", "cantidad", "precio", "descripcion", "IGV", "Categoria", "estado"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        tabla.setModel(model);

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select p.IdProducto, p.nombre, p.cantidad , p.precio, p.descripcion, p.porcentajeIgv, c.descripcion, p.estado from tb_producto As	p, tb_categoria As c where p.IdCategoria = c.IdCategoria;");

            while (rs.next()) {
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setCantidad(rs.getInt(3));
                pro.setPrecio(rs.getDouble(4));
                pro.setDescripcion(rs.getString(5));
                pro.setPorcentajeIgv(rs.getInt(6));
                
                pro.setCategoria(rs.getString(7));
                pro.setEstado(rs.getInt(8));
        
               
                model.addRow(pro.GestionarProducto());
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de producto:" + e);
        }

    }

    public Producto EnviarDatosProductoSeleccionado(int idProducto) {
        Producto pro = null; //vacio
        Connection cn = Conexion.conectar();
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select p.IdProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIgv, c.descripcion\n" +
            " from tb_producto p inner join tb_categoria c on p.IdCategoria=c.IdCategoria where p.IdProducto = " + idProducto);

            if (rs.next()) {
                pro = new Producto();
                pro.setIdProducto(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setCantidad(rs.getInt(3));
                pro.setPrecio(rs.getDouble(4));
                pro.setDescripcion(rs.getString(5));
                pro.setPorcentajeIgv(rs.getInt(6));
                pro.setCategoria(rs.getString(7));
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de productos:" + e);
        }
        return pro;
    }
     /*
    * ***************************************
        METODO PARA RELACIONAR CATEGORIAS
    * ***************************************
     */
    public String relacionarCategoria(String idCategoria) {
        String sql = "select descripcion from tb_categoria where IdCategoria = '" + idCategoria + "'";
        Statement st;
        try {
            Connection cn = (Connection) Conexion.conectar();
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                descripcionCategoria = rs.getString("descripcion");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id categoria");
        }
        return descripcionCategoria;

}
}