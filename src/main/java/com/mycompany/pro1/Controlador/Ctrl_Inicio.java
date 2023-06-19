/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro1.Controlador;

import com.mycompany.pro1.DAO.*;
import com.mycompany.pro1.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.pro1.modelo.*;
import com.mycompany.pro1.vista.*;

public class Ctrl_Inicio implements ActionListener{
    CRUD_Usuario crudUsu;
    FrmLogin vista;
    public Ctrl_Inicio(FrmLogin ini){
        vista = ini;
        vista.jButton_IniciarSesion.addActionListener(this);
        System.out.print( vista.jButton_IniciarSesion.getActionListeners().length);
                
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()== vista.jButton_IniciarSesion){
             if(!vista.txt_usuario.getText().isEmpty() && !vista.txt_password.getText().isEmpty()){
            
            crudUsu = new CRUD_Usuario();
            Usuario usuario = new Usuario();
            usuario.setUsuario(vista.txt_usuario.getText().trim());
            usuario.setPassword(vista.txt_password.getText().trim());
            if(crudUsu.loginUser(usuario)){
//            JOptionPane.showMessageDialog(null, "Bienvenido");
               
            
                  Main.menu =  new FrmMenu();
                  Main.menu.setVisible(true);
                  
                  Main.cmenu = new Ctrl_Menu(Main.menu);
                  vista.dispose();
              
       
            }else{
                JOptionPane.showMessageDialog(null, "Usuario y/o Clave incorrecto");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Campos vacíos");
        }
         }
    }
}
