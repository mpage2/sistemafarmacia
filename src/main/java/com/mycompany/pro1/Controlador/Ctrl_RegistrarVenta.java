package com.mycompany.pro1.Controlador;
import com.mycompany.pro1.DAO.*;
import java.awt.event.ActionEvent;
import com.mycompany.pro1.modelo.*;
import com.mycompany.pro1.vista.*;
import java.awt.event.ActionListener;

public class Ctrl_RegistrarVenta implements ActionListener{
    /* 
        * *********************************************
                        AUTOR: ISABEL HUAMAN 
                METODO: CONTROLADOR PARA REGISTRAR VENTA
        * *********************************************
     */
    CRUD_RegistrarVenta crudVen;
    CabeceraVenta modCab;
    InterFacturacion inven;
    public Ctrl_RegistrarVenta(InterFacturacion venta){
        inven=venta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == inusu.jButton_Guardar){
//            
//        }
    }
    
}
