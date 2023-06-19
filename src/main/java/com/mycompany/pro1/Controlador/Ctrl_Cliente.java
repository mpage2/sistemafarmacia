
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro1.Controlador;

import com.mycompany.pro1.DAO.*;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.pro1.modelo.*;
import com.mycompany.pro1.vista.*;
import com.mycompany.pro1.Main;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class Ctrl_Cliente implements ActionListener {
    // int idUsuario = 0;

    CRUD_Cliente crudCli;
    Cliente modCli;
    InterCliente incli;

    //InterGestionarUsuario geinusu;
    public Ctrl_Cliente(InterCliente cli) {
        incli = cli;
        incli.jButton_Guardar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == incli.jButton_Guardar) {
            Cliente cliente = new Cliente();
            crudCli = new CRUD_Cliente();

            if (!incli.txt_nombre.getText().isEmpty() && !incli.txt_apellido.getText().isEmpty() && !incli.txt_dni.getText().isEmpty()) {
            // JOptionPane.showMessageDialog(null, "Correcto");

            if (!crudCli.existeCliente(incli.txt_dni.getText().trim())) {

                cliente.setNombre(incli.txt_nombre.getText().trim());
                cliente.setApellido(incli.txt_apellido.getText().trim());
                cliente.setDni(incli.txt_dni.getText().trim());
                cliente.setTelefono(incli.txt_telefono.getText().trim());
                cliente.setDireccion(incli.txt_direccion.getText().trim());
                cliente.setEstado(1);

                if (crudCli.guardar(cliente)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    incli.txt_nombre.setBackground(Color.GREEN);
                    incli.txt_apellido.setBackground(Color.green);
                    incli.txt_dni.setBackground(Color.green);
                    incli.txt_direccion.setBackground(Color.green);
                    incli.txt_telefono.setBackground(Color.green);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Este cliente ya existe");
                incli.txt_nombre.setBackground(Color.white);
                incli.txt_apellido.setBackground(Color.white);
                incli.txt_dni.setBackground(Color.white);
                incli.txt_direccion.setBackground(Color.white);
                incli.txt_telefono.setBackground(Color.white);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Completa todos los campos");
            incli.txt_nombre.setBackground(Color.red);
            incli.txt_apellido.setBackground(Color.red);
            incli.txt_dni.setBackground(Color.red);
            incli.txt_direccion.setBackground(Color.red);
            incli.txt_telefono.setBackground(Color.red);
        }
        //metodo limpiar
        this.Limpiar();
        }

    }

    /**
     * *************************************
     * MÉTODO PARA LIMPIAR CAMPOS *************************************
     */
    void Limpiar() {
        incli.txt_nombre.setText("");
        incli.txt_apellido.setText("");
        incli.txt_dni.setText("");
        incli.txt_telefono.setText("");
        incli.txt_direccion.setText("");
    }

}
