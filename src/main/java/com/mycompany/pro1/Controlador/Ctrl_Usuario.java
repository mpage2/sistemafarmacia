
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro1.Controlador;

import com.mycompany.pro1.DAO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.pro1.modelo.*;
import com.mycompany.pro1.vista.*;

public class Ctrl_Usuario implements ActionListener {
   // int idUsuario = 0;
    CRUD_Usuario crudUsu;
    Usuario modUsu;
    InterUsuario inusu;
    //InterGestionarUsuario geinusu;
    public Ctrl_Usuario(InterUsuario usu){
        inusu=usu;
        inusu.jButton_Guardar.addActionListener(this);
 
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == inusu.jButton_Guardar){
              
            if (inusu.txt_nombre.getText().isEmpty() || inusu.txt_apellido.getText().isEmpty() || inusu.txt_usuario.getText().isEmpty()
                || inusu.txt_password.getText().isEmpty() || inusu.txt_telefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completa todos los campos");

            } else {

                //validamos si el usuario ya está registrado
                Usuario usuario = new Usuario();
                crudUsu = new CRUD_Usuario();
                if (!crudUsu.existeUsuario(inusu.txt_usuario.getText().trim())) {
                    //enviamos datos del usuario
                    usuario.setNombre(inusu.txt_nombre.getText().trim());
                    usuario.setApellido(inusu.txt_apellido.getText().trim());
                    usuario.setUsuario(inusu.txt_usuario.getText().trim());
                    usuario.setPassword(inusu.txt_password.getText().trim());
                    usuario.setTelefono(inusu.txt_telefono.getText().trim());
                    usuario.setEstado(1);

                    if (crudUsu.guardar(usuario)) {
                        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya está registrado");
                }
            }
            this.Limpiar();
        }

          
    }
 
    
    
    
    
    
    
    /**
     * *************************************
     * MÉTODO PARA LIMPIAR CAMPOS *************************************
     */
    void Limpiar() {
        inusu.txt_nombre.setText("");
        inusu.txt_apellido.setText("");
        inusu.txt_password.setText("");
        inusu.txt_usuario.setText("");
        inusu.txt_telefono.setText("");
    }
    
}
