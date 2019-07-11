package Datos;

import Entidad.UbigeoBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UbigeoDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<UbigeoBE> listarDepartamentos() throws Exception{
        ArrayList<UbigeoBE> lstUbigeoBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Ubigeo_ListarDepartamentos()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                UbigeoBE oUbigeoBE = new UbigeoBE();
                oUbigeoBE.setIdUbigeo(rs.getString("IdUbigeo"));
                oUbigeoBE.setDepartamento(rs.getString("Departamento"));
                oUbigeoBE.setProvincia(rs.getString("Provincia"));
                oUbigeoBE.setDistrito(rs.getString("Distrito"));
                oUbigeoBE.setUbicacion(rs.getString("Ubicacion"));
                lstUbigeoBE.add(oUbigeoBE);
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
        
        return lstUbigeoBE;
    }
    
    public ArrayList<UbigeoBE> listarProvincias() throws Exception{
        ArrayList<UbigeoBE> lstUbigeoBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Ubigeo_ListarProvincias()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                UbigeoBE oUbigeoBE = new UbigeoBE();
                oUbigeoBE.setIdUbigeo(rs.getString("IdUbigeo"));
                oUbigeoBE.setDepartamento(rs.getString("Departamento"));
                oUbigeoBE.setProvincia(rs.getString("Provincia"));
                oUbigeoBE.setDistrito(rs.getString("Distrito"));
                oUbigeoBE.setUbicacion(rs.getString("Ubicacion"));
                lstUbigeoBE.add(oUbigeoBE);
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
        
        return lstUbigeoBE;
    }
    
    public ArrayList<UbigeoBE> listarDistritos() throws Exception{
        ArrayList<UbigeoBE> lstUbigeoBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Ubigeo_ListarDistritos()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                UbigeoBE oUbigeoBE = new UbigeoBE();
                oUbigeoBE.setIdUbigeo(rs.getString("IdUbigeo"));
                oUbigeoBE.setDepartamento(rs.getString("Departamento"));
                oUbigeoBE.setProvincia(rs.getString("Provincia"));
                oUbigeoBE.setDistrito(rs.getString("Distrito"));
                oUbigeoBE.setUbicacion(rs.getString("Ubicacion"));
                lstUbigeoBE.add(oUbigeoBE);
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
        
        return lstUbigeoBE;
    }
}
