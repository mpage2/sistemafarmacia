package com.mycompany.pro1;
//libreria

import com.mycompany.pro1.Controlador.*;
import com.formdev.flatlaf.*;
import com.mycompany.pro1.DAO.Reportes;
import com.mycompany.pro1.vista.*;
public class Main {
 
    public static FrmLogin ini;
    public static Ctrl_Inicio cini;

    public static Ctrl_Menu cmenu;
    public static FrmMenu menu;
    
    public static Ctrl_Usuario cinusu;
    public static InterUsuario inusu;
    public static Ctrl_GestionarUsuario cgeinusu;
    public static InterGestionarUsuario geinusu;
    
    public static Ctrl_Producto cinpro;
    public static InterProducto inpro;
    public static Ctrl_GestionarProducto cgeinpro;
    public static InterGestionarProducto geinpro;
    
    public static Ctrl_Cliente cincli;
    public static InterCliente incli;
    public static Ctrl_GestionarCliente cgeincli;
    public static InterGestionarCliente geincli;
    
    
    
    public static Ctrl_Categoria cincat;
    public static InterCategoria incat;
    public static Ctrl_GestionarCategoria cgeincat;
    public static InterGestionarCategoria geincat;
    
    public static Ctrl_RegistrarVenta cinven;
    public static InterFacturacion inven;
    
    public static Reportes rep;
    
    
    public static void main(String[] args) { 
        FlatDarkLaf.setup();
        ini = new FrmLogin();
        ini.setVisible(true);
        cini =  new Ctrl_Inicio(ini);
  
        
    }
    
}