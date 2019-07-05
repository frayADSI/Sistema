/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.*;
import vista.clientes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import modelo.*;
import servicios.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Fray
 */
public class Control implements ActionListener{
    FormularioPrincipal formularioPrincipal;
    FormularioClientes formularioClientes;
    private ControladorClientes controladorClientes = null;
    public Control() {
        formularioPrincipal = new FormularioPrincipal();
        formularioClientes = new FormularioClientes();

        formularioPrincipal.getRegistroClienteBtn().addActionListener(this);

}
    
    public void iniciar() {
        if(formularioPrincipal == null) {
            formularioPrincipal = new FormularioPrincipal();
            formularioPrincipal.setVisible(true);
        } else {
            formularioPrincipal.setVisible(true);
        }
    }
//----------------------------------Eventos----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //Eventos formulario principal
        //opcion clientes 
        if(e.getSource() == formularioPrincipal.getRegistroClienteBtn()) {
            formularioPrincipal.setVisible(false);
            if (formularioClientes == null) {
                formularioClientes = new FormularioClientes();
                formularioClientes.setVisible(true);
            } else {
                formularioClientes.setVisible(true);
            }
            if (controladorClientes == null) {
                controladorClientes = new ControladorClientes(this);
            }
            
        }

              
    }
}

