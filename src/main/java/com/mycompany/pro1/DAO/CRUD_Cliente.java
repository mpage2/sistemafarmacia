package com.mycompany.pro1.DAO;

import java.sql.Connection;
import com.mycompany.pro1.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.mycompany.pro1.modelo.*;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CRUD_Cliente {

//    int idUsuario = 0;
//    InterGestionarUsuario geinusu;

    /*
    * **************************************
        MÉTODO PARA GUARDAR NUEVO CLIENTE
    * **************************************
     */
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values (?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); //id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getDni());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente" + e);
        }
        return respuesta;
    }

    /*
    * ************************************************************
        MÉTODO PARA CONSULTAR SI EL CLIENTE YA EXISTE EN LA BD
    * ************************************************************
     */
    public boolean existeCliente(String dni) {
        boolean respuesta = false;
        String sql = "select DNI from tb_cliente where DNI = '" + dni + "';";
        Statement st;
        try {
            Connection cn = Conexion.conectar();
            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente" + e);
        }
        return respuesta;
    }

    /*
    * ********************************************
            MÉTODO PARA ACTUALIZAR CLIENTE
    * ********************************************
     */
    public boolean actualizar(Cliente objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_cliente set nombre =?, apellido=?, DNI=?, telefono =?, direccion=?, estado= ? where IdCliente= ?;");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getDni());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            consulta.setInt(7, objeto.getIdCliente());
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente" + e);
        }
        return respuesta;
    }

    /*
    * ********************************************
            MÉTODO PARA ELIMINAR CLIENTE
    * ********************************************
     */
    public void eliminar(int idCliente) {
//        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_cliente where IdCliente = ?;");
            consulta.setInt(1, idCliente); //actualizamos el parametro con el codigo
            consulta.executeUpdate(); //actualizamos y ejecutamos la consulta.
            cn.close();//cerramos la conexion.
//            consulta.executeUpdate();
//            if (consulta.executeUpdate() > 0) {
//                respuesta = true;
//            }
//            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario" + e);
        }
//        return respuesta;
    }

    public void CargarTablaCliente(JTable tabla) {
        Connection cn = Conexion.conectar();
        String titulos[] = {"N°", "nombre", "apellido", "DNI", "telefono", "dirección", "estado"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        tabla.setModel(model);

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tb_cliente");

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setDni(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                cli.setDireccion(rs.getString(6));
                cli.setEstado(rs.getInt(7));
                model.addRow(cli.GestionarCliente());
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de cliente:" + e);
        }

    }

    public Cliente EnviarDatosClienteSeleccionado(int idCliente) {
        Cliente cli = null; //vacio
        Connection cn = Conexion.conectar();
//        String titulos[]={"N°","nombre","apellido","usuario","password","telefono","estado"};
//        DefaultTableModel model = new DefaultTableModel(null,titulos);
//        tabla.setModel(model);

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tb_cliente where IdCliente = " + idCliente);

            while (rs.next()) {
                cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setDni(rs.getString(4));
                cli.setDireccion(rs.getString(5));
                cli.setTelefono(rs.getString(6));
                //usu.setEstado(rs.getInt(7));
//                model.addRow(usu.GestionarUsuario());
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla de usuarios:" + e);
        }
        return cli;
    }

}
