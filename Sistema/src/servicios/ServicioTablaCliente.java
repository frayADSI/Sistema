/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import modelo.Cliente;

/**
 *
 * @author Fray
 */
public class ServicioTablaCliente {
    private Connection  conn;
    private final String SQL_INSERT = "insert into tblcliente(clinumeroidentificacion,clinombre,cliapellido,clitelefono)"
            + " values(?,?,?,?)";
    public ServicioTablaCliente() {
        try {
            conn = Conexion.getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insert(Cliente cliente) throws SQLException {
        int rows = 0;
        PreparedStatement stmt = null;
        if (cliente.getCedula()!= 0 && cliente.getNombre()!=null &&cliente.getApellido() !=
                null && cliente.getTelefono() != 0) {
            try {
                stmt = conn.prepareStatement(SQL_INSERT);
                int index = 1;
                stmt.setLong(index++, cliente.getCedula());
                stmt.setString(index++, cliente.getNombre());
                stmt.setString(index++, cliente.getApellido());
                stmt.setInt(index, cliente.getTelefono());
                rows = stmt.executeUpdate();
                System.out.println("Registros insertados: " + rows);
            } finally {
                if (stmt != null) {
                    Conexion.close(stmt);
                }
                if (conn != null) {
                    Conexion.close(conn);
                }
            }
        }
        return rows;
    }
}
