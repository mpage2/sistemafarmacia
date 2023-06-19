/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro1.Controlador;

import com.mycompany.pro1.DAO.Reportes;
import com.mycompany.pro1.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.pro1.vista.*;

/**
 *
 * @author huama
 */
public class Ctrl_Menu implements ActionListener {

    FrmMenu vista;

    public Ctrl_Menu(FrmMenu menu) {
        vista = menu;
        vista.jMenuItem_nuevo_usuario.addActionListener(this);
        vista.jMenuItem_gestionar_usuario.addActionListener(this);
        vista.jMenuItem3_nuevo_producto.addActionListener(this);
        vista.jMenuItem_gestionar_producto.addActionListener(this);
        vista.jMenuItem_actualizar_stock.addActionListener(this);
        vista.jMenuItem_nuevo_cliente.addActionListener(this);
        vista.jMenuItem_gestionar_cliente.addActionListener(this);
        vista.jMenuItem_nueva_categoria.addActionListener(this);
        vista.jMenuItem_gestionar_categoria.addActionListener(this);
        vista.jMenuItem_nueva_venta.addActionListener(this);
        vista.jMenuItem_gestionar_venta.addActionListener(this);
        vista.jMenuItem_reportes_clientes.addActionListener(this);
        vista.jMenuItem_reportes_categorias.addActionListener(this);

        vista.jMenu1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jMenuItem_nuevo_usuario) {
            Main.inusu = new InterUsuario();
            Main.cinusu = new Ctrl_Usuario(Main.inusu);
            vista.JDesktopPane_menu.add(Main.inusu);
            Main.inusu.setVisible(true);
        }
        if (e.getSource() == vista.jMenuItem_gestionar_usuario) {
            Main.geinusu = new InterGestionarUsuario();
            Main.cgeinusu = new Ctrl_GestionarUsuario(Main.geinusu);
            vista.JDesktopPane_menu.add(Main.geinusu);
            Main.geinusu.setVisible(true);
        }
        if (e.getSource() == vista.jMenuItem3_nuevo_producto) {
            Main.inpro = new InterProducto();
            Main.cinpro = new Ctrl_Producto(Main.inpro);
            vista.JDesktopPane_menu.add(Main.inpro);
            Main.inpro.setVisible(true);
        }
        if (e.getSource() == vista.jMenuItem_gestionar_producto) {
            Main.geinpro = new InterGestionarProducto();
            Main.cgeinpro = new Ctrl_GestionarProducto(Main.geinpro);
            vista.JDesktopPane_menu.add(Main.geinpro);
            Main.geinpro.setVisible(true);
        }

        if (e.getSource() == vista.jMenuItem_nuevo_cliente) {
            Main.incli = new InterCliente();
            Main.cincli = new Ctrl_Cliente(Main.incli);
            vista.JDesktopPane_menu.add(Main.incli);
            Main.incli.setVisible(true);
        }
        if (e.getSource() == vista.jMenuItem_gestionar_cliente) {
            Main.geincli = new InterGestionarCliente();
            Main.cgeincli = new Ctrl_GestionarCliente(Main.geincli);
            vista.JDesktopPane_menu.add(Main.geincli);
            Main.geincli.setVisible(true);
        }

        if (e.getSource() == vista.jMenuItem_nueva_categoria) {
            Main.incat = new InterCategoria();
            Main.cincat = new Ctrl_Categoria(Main.incat);
            vista.JDesktopPane_menu.add(Main.incat);
            Main.incat.setVisible(true);
        }
        if (e.getSource() == vista.jMenuItem_gestionar_categoria) {
            Main.geincat = new InterGestionarCategoria();
            Main.cgeincat = new Ctrl_GestionarCategoria(Main.geincat);
            vista.JDesktopPane_menu.add(Main.geincat);
            Main.geincat.setVisible(true);
        }
        if(e.getSource() == vista.jMenuItem_nueva_venta){
                Main.inven = new InterFacturacion();
                Main.cinven = new Ctrl_RegistrarVenta(Main.inven);
                vista.JDesktopPane_menu.add(Main.inven);
                Main.inven.setVisible(true);
            }
        if (e.getSource() == vista.jMenuItem_reportes_clientes) {
            System.out.println("boton reportes");
            Reportes reportes = new Reportes();
            reportes.ReportesClientes();
//            vista.JDesktopPane_menu.add(Main.geincat);

//            Main.geincat.setVisible(true);
        }

    }
}
