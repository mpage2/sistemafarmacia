/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro1.Controlador;

import com.mycompany.pro1.DAO.CRUD_Categoria;
import com.mycompany.pro1.Main;
import java.security.Principal.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.pro1.modelo.*;
import com.mycompany.pro1.vista.*;
import static com.mycompany.pro1.vista.FrmMenu.JDesktopPane_menu;

public class Ctrl_Categoria implements ActionListener {

    CRUD_Categoria crudCat;
    Categoria modCat;
    InterCategoria incat;
    InterGestionarCategoria geincat;

    public Ctrl_Categoria(InterCategoria cat) {
        incat = cat;
        incat.jButton1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == incat.jButton1) {
            Categoria categoria = new Categoria();
            crudCat = new CRUD_Categoria();

            if (incat.txt_descripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "complete todos los campos");

            } else {

                if (!crudCat.existeCategoria(incat.txt_descripcion.getText().trim())) {
                    categoria.setDescripcion(incat.txt_descripcion.getText().trim());
                    categoria.setEstado(1);
                    if (crudCat.guardar(categoria)) {
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        Main.geincat = new InterGestionarCategoria();
                        Main.cgeincat = new Ctrl_GestionarCategoria(Main.geincat);
                        JDesktopPane_menu.add(Main.geincat);
                        Main.geincat.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La categoria ya existe");
                }

            }
            incat.txt_descripcion.setText("");
        }
    }

}
