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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;
import servicios.*;
/**
 *
 * @author Fray
 */
public class Control implements ActionListener{
    private FormularioPrincipal formularioPrincipal;
    private FormularioClientes formularioClientes;
    private InsertarClienteForm insertarClienteForm;
    
    public Control() {
        formularioPrincipal = new FormularioPrincipal();
        formularioClientes = new FormularioClientes();
        insertarClienteForm = new InsertarClienteForm();
        formularioPrincipal.getRegistroClienteBtn().addActionListener(this);
        formularioClientes.getMenuBtn().addActionListener(this);
        formularioClientes.getInsertarBtn().addActionListener(this);
        insertarClienteForm.getRegistrarBtn().addActionListener(this);
        insertarClienteForm.getVolverBtn().addActionListener(this);
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
        }
        //eventos en formulario de clientes
        if(e.getSource() == formularioClientes.getMenuBtn()) {
            formularioClientes.setVisible(false);
            if (formularioPrincipal == null) {
                formularioPrincipal = new FormularioPrincipal();
                formularioPrincipal.setVisible(true);
            } else {
                formularioPrincipal.setVisible(true);
            }
        }
        if(e.getSource() == formularioClientes.getInsertarBtn()) {
            formularioClientes.setVisible(false);
            if (insertarClienteForm== null) {
                insertarClienteForm = new InsertarClienteForm();
                insertarClienteForm.setVisible(true);
            } else {
                insertarClienteForm.setVisible(true);
            }
            
        }
        //eventos insertar clientes
        if (e.getSource() == insertarClienteForm.getRegistrarBtn()) {
            long cedula = Long.parseLong(insertarClienteForm.getCedula().getText());
            String nombre = insertarClienteForm.getNombre().getText();
            String apellido = insertarClienteForm.getApellido().getText();
            int telf = Integer.parseInt(insertarClienteForm.getTelefono().getText());
            Cliente clienteTemp = new Cliente(cedula, nombre, apellido, telf);
            ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
            try {
                tablaCliente.insert(clienteTemp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == insertarClienteForm.getVolverBtn()) {
            insertarClienteForm.setVisible(false);
            if(formularioClientes == null) {
                formularioClientes = new FormularioClientes();
                formularioClientes.setVisible(true);
            } else {
                formularioClientes.setVisible(true);
            }
        }
        
    }
}
