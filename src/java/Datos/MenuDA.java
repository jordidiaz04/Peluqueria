package Datos;

import Entidad.MenuBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<MenuBE> listar() throws Exception{
        ArrayList<MenuBE> lstMenuBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Menu_Listar()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                MenuBE oMenuBE = new MenuBE();
                oMenuBE.setIdMenu(rs.getInt("IdMenu"));
                oMenuBE.setTitulo(rs.getString("Titulo"));
                oMenuBE.setRuta(rs.getString("Ruta") == null ? "" : rs.getString("Ruta"));
                oMenuBE.setEsPadre(rs.getBoolean("EsPadre"));
                oMenuBE.setIdMenuPadre(rs.getInt("IdMenuPadre"));
                oMenuBE.setIcono(rs.getString("Icono") == null ? "" : rs.getString("Icono"));
                oMenuBE.setEstado(rs.getBoolean("Estado"));
                oMenuBE.setMenuPadre(rs.getString("MenuPadre"));
                lstMenuBE.add(oMenuBE);
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
        
        return lstMenuBE;
    }
    
    public MenuBE obtener(int IdMenu) throws Exception{
        MenuBE oMenuBE = null;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Menu_Obtener(?)}");
            cstm.setInt(1, IdMenu);
            rs = cstm.executeQuery();
            while(rs.next()){
                oMenuBE = new MenuBE();
                oMenuBE.setIdMenu(rs.getInt("IdMenu"));
                oMenuBE.setTitulo(rs.getString("Titulo"));
                oMenuBE.setRuta(rs.getString("Ruta") == null ? "" : rs.getString("Ruta"));
                oMenuBE.setEsPadre(rs.getBoolean("EsPadre"));
                oMenuBE.setIdMenuPadre(rs.getInt("IdMenuPadre"));
                oMenuBE.setIcono(rs.getString("Icono") == null ? "" : rs.getString("Icono"));
                oMenuBE.setEstado(rs.getBoolean("Estado"));
            }
        }
        catch(Exception ex){
            oMenuBE = null;
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
        
        return oMenuBE;
    }
    
    public boolean insertUpdate(MenuBE oMenuBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Menu_InsertUpdate(?,?,?,?,?,?,?)}");
            cstm.setString(1, oMenuBE.getConsulta());
            cstm.setInt(2, oMenuBE.getIdMenu());
            cstm.setString(3, oMenuBE.getTitulo());
            cstm.setString(4, oMenuBE.getRuta());
            cstm.setBoolean(5, oMenuBE.isEsPadre());
            cstm.setInt(6, oMenuBE.getIdMenuPadre());
            cstm.setString(7, oMenuBE.getIcono());
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
    
    public boolean enableDisable(MenuBE oMenuBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Menu_EnableDisable(?,?)}");
            cstm.setInt(1, oMenuBE.getIdMenu());
            cstm.setBoolean(2, oMenuBE.isEstado());
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
