package Datos;

import Entidad.AccesoBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccesoDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<AccesoBE> listar(int IdPerfil) throws Exception{
        ArrayList<AccesoBE> lstAccesoBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Acceso_Listar(?)}");
            cstm.setInt(1, IdPerfil);
            rs = cstm.executeQuery();
            while(rs.next()){
                AccesoBE oAccesoBE = new AccesoBE();
                oAccesoBE.setIdPerfil(rs.getInt("IdPerfil"));
                oAccesoBE.setIdMenu(rs.getInt("IdMenu"));
                oAccesoBE.setEstado(rs.getBoolean("Estado"));
                oAccesoBE.setMenu(rs.getString("Menu"));
                oAccesoBE.setIcono(rs.getString("Icono") == null ? "" : rs.getString("Icono"));
                oAccesoBE.setEsPadre(rs.getBoolean("EsPadre"));
                oAccesoBE.setIdMenuPadre(rs.getInt("IdMenuPadre"));
                oAccesoBE.setRuta(rs.getString("Ruta") == null ? "" : rs.getString("Ruta"));
                lstAccesoBE.add(oAccesoBE);
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
        
        return lstAccesoBE;
    }
    
    public ArrayList<AccesoBE> obtener(int IdPerfil) throws Exception{
        ArrayList<AccesoBE> lstAccesoBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Acceso_Obtener(?)}");
            cstm.setInt(1, IdPerfil);
            rs = cstm.executeQuery();
            while(rs.next()){
                AccesoBE oAccesoBE = new AccesoBE();
                oAccesoBE.setIdPerfil(rs.getInt("IdPerfil"));
                oAccesoBE.setIdMenu(rs.getInt("IdMenu"));
                oAccesoBE.setEstado(rs.getBoolean("Estado"));
                oAccesoBE.setIdMenuPadre(rs.getInt("IdMenuPadre"));
                lstAccesoBE.add(oAccesoBE);
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
        
        return lstAccesoBE;
    }
    
    public boolean guardar(String Accesos) throws Exception{
        boolean result = true;
        try{
            cn = Conexion.instanciar();
            String[] accesos = Accesos.split("▼");
            for (String acceso : accesos) {
                cstm = cn.conectar().prepareCall("{call Sp_Acceso_Guardar(?,?,?)}");
                cstm.setInt(1, Integer.parseInt(acceso.split("▲")[0]));
                cstm.setInt(2, Integer.parseInt(acceso.split("▲")[1]));
                cstm.setBoolean(3, Boolean.parseBoolean(acceso.split("▲")[2]));
                boolean rpta = cstm.executeUpdate() == 1;
                if(!rpta) result = false;
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
        
        return result;
    }
}
