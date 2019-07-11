package Datos;

import Entidad.ClienteBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<ClienteBE> listar() throws Exception{
        ArrayList<ClienteBE> lstClienteBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Cliente_Listar()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                ClienteBE oClienteBE = new ClienteBE();
                oClienteBE.setIdCliente(rs.getInt("IdCliente"));
                oClienteBE.setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                oClienteBE.setDocIdentidad(rs.getString("DocIdentidad"));
                oClienteBE.setNombres(rs.getString("Nombres"));
                oClienteBE.setApellidos(rs.getString("Apellidos"));
                oClienteBE.setEmail(rs.getString("Email") == null ? "" : rs.getString("Email"));
                oClienteBE.setTelefono(rs.getString("Telefono"));
                oClienteBE.setAsistencia(rs.getString("Asistencia"));
                oClienteBE.setNombreCompleto(oClienteBE.getNombres() + " " + oClienteBE.getApellidos());
                oClienteBE.setTipoDocIdentidad(rs.getString("TipoDocIdentidad"));
                oClienteBE.setAsistenciaDescripcion(rs.getString("AsistenciaDescripcion"));
                lstClienteBE.add(oClienteBE);
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            try{
                if(rs != null) rs.close();
                if(cstm != null) cstm.close();
                if(cn != null) cn.desconectar();
            }
            catch(Exception ex){
                throw ex;
            }
        }
        
        return lstClienteBE;
    }
    
    public ClienteBE obtener(int IdCliente) throws Exception{        
        ClienteBE oClienteBE = null;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Cliente_Obtener(?)}");
            cstm.setInt(1, IdCliente);
            rs = cstm.executeQuery();
            while(rs.next()){
                oClienteBE = new ClienteBE();
                oClienteBE.setIdCliente(rs.getInt("IdCliente"));
                oClienteBE.setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                oClienteBE.setDocIdentidad(rs.getString("DocIdentidad"));
                oClienteBE.setNombres(rs.getString("Nombres"));
                oClienteBE.setApellidos(rs.getString("Apellidos"));
                oClienteBE.setEmail(rs.getString("Email") == null ? "" : rs.getString("Email"));
                oClienteBE.setTelefono(rs.getString("Telefono"));
                oClienteBE.setAsistencia(rs.getString("Asistencia"));
            }
        }
        catch(Exception ex){
            oClienteBE = null;
            throw ex;
        }
        finally{
            try{
                if(rs != null) rs.close();
                if(cstm != null) cstm.close();
                if(cn != null) cn.desconectar();
            }
            catch(Exception ex){
                throw ex;
            }
        }
        
        return oClienteBE;
    }
    
    public boolean insertUpdate(ClienteBE oClienteBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Cliente_InsertUpdate(?,?,?,?,?,?,?,?)}");
            cstm.setString(1, oClienteBE.getConsulta());
            cstm.setInt(2, oClienteBE.getIdCliente());
            cstm.setString(3, oClienteBE.getIdTipoDocIdentidad());
            cstm.setString(4, oClienteBE.getDocIdentidad());
            cstm.setString(5, oClienteBE.getNombres());
            cstm.setString(6, oClienteBE.getApellidos());
            cstm.setString(7, oClienteBE.getEmail());
            cstm.setString(8, oClienteBE.getTelefono());
            int rpta = cstm.executeUpdate();
            if(rpta == 1) result = true;
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            try{
                if(rs != null) rs.close();
                if(cstm != null) cstm.close();
                if(cn != null) cn.desconectar();
            }
            catch(Exception ex){
                throw ex;
            }
        }
        
        return result;
    }
}
