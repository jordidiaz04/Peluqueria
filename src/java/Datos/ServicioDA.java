package Datos;

import Entidad.ServicioBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicioDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<ServicioBE> listar() throws Exception{
        ArrayList<ServicioBE> lstServicioBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Servicio_Listar()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                ServicioBE oServicioBE = new ServicioBE();
                oServicioBE.setIdServicio(rs.getInt("IdServicio"));
                oServicioBE.setNombre(rs.getString("Nombre"));
                oServicioBE.setDetalle(rs.getString("Detalle"));
                oServicioBE.setPrecio(rs.getDouble("Precio"));
                oServicioBE.setEstado(rs.getBoolean("Estado"));
                lstServicioBE.add(oServicioBE);
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
        
        return lstServicioBE;
    }
    
    public ServicioBE obtener(int IdCliente) throws Exception{        
        ServicioBE oServicioBE = null;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Servicio_Obtener(?)}");
            cstm.setInt(1, IdCliente);
            rs = cstm.executeQuery();
            while(rs.next()){
                oServicioBE = new ServicioBE();
                oServicioBE.setIdServicio(rs.getInt("IdServicio"));
                oServicioBE.setNombre(rs.getString("Nombre"));
                oServicioBE.setDetalle(rs.getString("Detalle"));
                oServicioBE.setPrecio(rs.getDouble("Precio"));
                oServicioBE.setEstado(rs.getBoolean("Estado"));
            }
        }
        catch(Exception ex){
            oServicioBE = null;
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
        
        return oServicioBE;
    }
    
    public boolean insertUpdate(ServicioBE oServicioBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Servicio_InsertUpdate(?,?,?,?,?)}");
            cstm.setString(1, oServicioBE.getConsulta());
            cstm.setInt(2, oServicioBE.getIdServicio());
            cstm.setString(3, oServicioBE.getNombre());
            cstm.setString(4, oServicioBE.getDetalle());
            cstm.setDouble(5, oServicioBE.getPrecio());
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
    
    public boolean enableDisable(ServicioBE oServicioBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Servicio_EnableDisable(?,?)}");
            cstm.setInt(1, oServicioBE.getIdServicio());
            cstm.setBoolean(2, oServicioBE.isEstado());
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
