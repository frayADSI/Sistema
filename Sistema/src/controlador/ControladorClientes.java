/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.clientes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import servicios.ServicioTablaCliente;
import vista.FormularioPrincipal;

/**
 *
 * @author Fray
 */
public class ControladorClientes implements ActionListener{
    private Control control;
    private InsertarClienteForm insertarClienteForm;
    private ActualizarClienteForm actualizarClienteForm;
    private ConsultarClienteForm consultarClienteForm;
    private EliminarClienteForm eliminarClienteForm;
    public ControladorClientes(Control controlTemp) {
        this.control = controlTemp;
        insertarClienteForm = new InsertarClienteForm();
        actualizarClienteForm = new ActualizarClienteForm();
        consultarClienteForm = new ConsultarClienteForm();
        eliminarClienteForm = new EliminarClienteForm();
        control.formularioClientes.getMenuBtn().addActionListener(this);
        control.formularioClientes.getInsertarBtn().addActionListener(this);
        control.formularioClientes.getUpdateBtn().addActionListener(this);
        control.formularioClientes.getSelectBtn().addActionListener(this);
        control.formularioClientes.getDeleteBtn().addActionListener(this);
        insertarClienteForm.getRegistrarBtn().addActionListener(this);
        insertarClienteForm.getVolverBtn().addActionListener(this);
        actualizarClienteForm.getActualizarBtn().addActionListener(this);
        actualizarClienteForm.getVolverBtn().addActionListener(this);
        consultarClienteForm.getConsultarBtn().addActionListener(this);
        consultarClienteForm.getVolverBtn().addActionListener(this);
        eliminarClienteForm.getEliminarBtn().addActionListener(this);
        eliminarClienteForm.getVolverBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == control.formularioClientes.getInsertarBtn()) {
            control.formularioClientes.setVisible(false);
            if (insertarClienteForm== null) {
                insertarClienteForm = new InsertarClienteForm();
                insertarClienteForm.setVisible(true);
            } else {
                insertarClienteForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioClientes.getUpdateBtn()) {
            control.formularioClientes.setVisible(false);
            if (actualizarClienteForm== null) {
                actualizarClienteForm = new ActualizarClienteForm();
                actualizarClienteForm.setVisible(true);
            } else {
                actualizarClienteForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioClientes.getSelectBtn()) {
            control.formularioClientes.setVisible(false);
            if (consultarClienteForm== null) {
                consultarClienteForm = new ConsultarClienteForm();
                consultarClienteForm.setVisible(true);
            } else {
                consultarClienteForm.setVisible(true);
            }
        }
        if(e.getSource() == control.formularioClientes.getDeleteBtn()) {
            control.formularioClientes.setVisible(false);
            if (eliminarClienteForm== null) {
                eliminarClienteForm = new EliminarClienteForm();
                eliminarClienteForm.setVisible(true);
            } else {
                eliminarClienteForm.setVisible(true);
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
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //eventos actualizar clientes
        if (e.getSource() == actualizarClienteForm.getActualizarBtn()) {
            long cedula = Long.parseLong(actualizarClienteForm.getCedula().getText());
            String nombre = actualizarClienteForm.getNombre().getText();
            String apellido = actualizarClienteForm.getApellido().getText();
            int telf = Integer.parseInt(actualizarClienteForm.getTelefono().getText());
            Cliente clienteTemp = new Cliente(cedula, nombre, apellido, telf);
            ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
            try {
                tablaCliente.update(clienteTemp);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == actualizarClienteForm.getVolverBtn()) {
            actualizarClienteForm.setVisible(false);
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //Consultar Cliente formulario
        if(e.getSource() == consultarClienteForm.getConsultarBtn()) {
            if(consultarClienteForm.getCedulaConsultarTF().getText() != "") {
                long cedulaConsultar = Long.parseLong(consultarClienteForm.getCedulaConsultarTF().getText());
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                try {
                  Cliente cliente = tablaCliente.consultar(cedulaConsultar);  
                  consultarClienteForm.getCedulaLbl().setText("" + cliente.getCedula());
                  consultarClienteForm.getNombreLbl().setText(cliente.getNombre());
                  consultarClienteForm.getApellidoLbl().setText(cliente.getApellido());
                  consultarClienteForm.getTelefonoLbl().setText("" + cliente.getTelefono());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            }
        }
        
        if(e.getSource() == consultarClienteForm.getVolverBtn()) {
            consultarClienteForm.setVisible(false);
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //eliminar cliente
        if(e.getSource() == eliminarClienteForm.getEliminarBtn()) {
            if (eliminarClienteForm.getCedulaEliminarTF().getText() != "") {
                long cedula = Long.parseLong(eliminarClienteForm.getCedulaEliminarTF().getText());
                int confirmacion = JOptionPane.showConfirmDialog(eliminarClienteForm.getRootPane(), "Está seguro que"
                        + " desea eliminar el cliente con cédula" + cedula + "?", "Eliminación de cliente", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirmacion == 0) {
                    try {
                        ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                        tablaCliente.delete(cedula);
                        JOptionPane.showMessageDialog(control.formularioClientes.getRootPane(), "se ha borrado el cliente con cedula" + cedula
                        + " exitosamente!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
                //eventos en formulario de clientes
        if(e.getSource() == control.formularioClientes.getMenuBtn()) {
            control.formularioClientes.setVisible(false);
            if (control.formularioPrincipal == null) {
                control.formularioPrincipal = new FormularioPrincipal();
                control.formularioPrincipal.setVisible(true);
            } else {
                control.formularioPrincipal.setVisible(true);
            }
        }
        
        if(e.getSource() == eliminarClienteForm.getVolverBtn()) {
            eliminarClienteForm.setVisible(false);
            if (control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
    }
}
