package com.mycompany.pro1.DAO;

import java.sql.Connection;
import com.mycompany.pro1.conexion.Conexion;
import com.mycompany.pro1.modelo.CabeceraVenta;
import com.mycompany.pro1.modelo.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mycompany.pro1.vista.*;

public class CRUD_RegistrarVenta {
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

//    int idUsuario = 0;
//    InterGestionarUsuario geinusu;

    /*
    * **************************************
        MÉTODO PARA GUARDAR LA CABECERA DE VENTA
    * **************************************
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_Cabecera_venta values (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0); //id
            consulta.setInt(2, objeto.getIdCliente());
            consulta.setDouble(3, objeto.getValorPagar());
            consulta.setString(4, objeto.getFechaVenta());
            consulta.setInt(5, objeto.getEstado());
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            
            ResultSet rs = consulta.getGeneratedKeys();
            while(rs.next()){
                iDColVar= rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }
            
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta" + e);
        }
        return respuesta;
    }
    
    /*
    * ***************************************
        MÉTODO PARA GUARDAR DETALLE DE VENTA
    * ****************************************
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values (?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); //id
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubtotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getIgv());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta" + e);
        }
        return respuesta;
    }

    

    /*
    * ********************************************
            MÉTODO PARA ACTUALIZAR PRODUCTO
    * ********************************************
     */
    public boolean actualizar(CabeceraVenta objeto) {
        boolean respuesta = false;
        java.sql.Connection cn = Conexion.conectar();
        try {
            java.sql.PreparedStatement consulta = cn.prepareStatement(
                    "update tb_Cabecera_venta set IdCliente =?, estado = ? "
                            + "where IdCabeceraVenta= ?;");
            
            
            
            consulta.setInt(1, objeto.getIdCliente());
            consulta.setInt(2, objeto.getEstado());
            
            consulta.setInt(3, objeto.getIdCabeceraventa());
            
            consulta.executeUpdate();
        

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta" + e);
        }
        return respuesta;
    }
    
    public void CargarTablaUsuarios(JTable tabla) {
        Connection cn = Conexion.conectar();
        String titulos[]={"N°","nombre","apellido","usuario","password","telefono","estado"};
        DefaultTableModel model = new DefaultTableModel(null,titulos);
        tabla.setModel(model);
        
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tb_usuario");
           

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setUsuario(rs.getString(4));
                usu.setPassword(rs.getString(5));
                usu.setTelefono(rs.getString(6));
                usu.setEstado(rs.getInt(7));
                model.addRow(usu.GestionarUsuario());
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de usuarios:" + e);
        }

    }

    public Usuario EnviarDatosUsuarioSeleccionado(int idUsuario) {
        Usuario usu = null; //vacio
        Connection cn = Conexion.conectar();
//        String titulos[]={"N°","nombre","apellido","usuario","password","telefono","estado"};
//        DefaultTableModel model = new DefaultTableModel(null,titulos);
//        tabla.setModel(model);
        
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tb_usuario where IdUsuario = " + idUsuario);
           

            while (rs.next()) {
                usu = new Usuario();
                usu.setIdUsuario(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setUsuario(rs.getString(4));
                usu.setPassword(rs.getString(5));
                usu.setTelefono(rs.getString(6));
                //usu.setEstado(rs.getInt(7));
//                model.addRow(usu.GestionarUsuario());
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de usuarios:" + e);
        }
        return usu;
    }

   

 

}
