package Datos;

import Entidad.PersonalBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonalDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<PersonalBE> listar() throws Exception{
        ArrayList<PersonalBE> lstPersonalBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Personal_Listar()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                PersonalBE oPersonalBE = new PersonalBE();
                oPersonalBE.setIdPersonal(rs.getInt("IdPersonal"));
                oPersonalBE.setSexo(rs.getString("Sexo"));
                oPersonalBE.setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                oPersonalBE.setDocIdentidad(rs.getString("DocIdentidad"));
                oPersonalBE.setNombres(rs.getString("Nombres"));
                oPersonalBE.setApellidos(rs.getString("Apellidos"));
                oPersonalBE.setIdUbigeo(rs.getString("IdUbigeo") == null ? "" : rs.getString("IdUbigeo"));
                oPersonalBE.setDomicilio(rs.getString("Domicilio") == null ? "" : rs.getString("Domicilio"));
                oPersonalBE.setEmail(rs.getString("Email") == null ? "" : rs.getString("Email"));
                oPersonalBE.setTelefono(rs.getString("Telefono"));
                oPersonalBE.setContraseña(rs.getString("Contraseña"));
                oPersonalBE.setIdPerfil(rs.getInt("IdPerfil"));
                oPersonalBE.setEstado(rs.getBoolean("Estado"));
                oPersonalBE.setNombreCompleto(oPersonalBE.getNombres() + " " + oPersonalBE.getApellidos());
                oPersonalBE.setSexoDescripcion(rs.getString("SexoDescripcion"));
                oPersonalBE.setTipoDocIdentidad(rs.getString("TipoDocIdentidad"));
                oPersonalBE.setDistrito(rs.getString("Distrito"));
                oPersonalBE.setPerfil(rs.getString("Perfil"));
                lstPersonalBE.add(oPersonalBE);
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
        
        return lstPersonalBE;
    }
    
    public PersonalBE obtener(int IdPersonal) throws Exception{        
        PersonalBE oPersonalBE = null;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Personal_Obtener(?)}");
            cstm.setInt(1, IdPersonal);
            rs = cstm.executeQuery();
            while(rs.next()){
                oPersonalBE = new PersonalBE();
                oPersonalBE.setIdPersonal(rs.getInt("IdPersonal"));
                oPersonalBE.setSexo(rs.getString("Sexo"));
                oPersonalBE.setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                oPersonalBE.setDocIdentidad(rs.getString("DocIdentidad"));
                oPersonalBE.setNombres(rs.getString("Nombres"));
                oPersonalBE.setApellidos(rs.getString("Apellidos"));
                oPersonalBE.setIdUbigeo(rs.getString("IdUbigeo") == null ? "" : rs.getString("IdUbigeo"));
                oPersonalBE.setDomicilio(rs.getString("Domicilio") == null ? "" : rs.getString("Domicilio"));
                oPersonalBE.setEmail(rs.getString("Email") == null ? "" : rs.getString("Email"));
                oPersonalBE.setTelefono(rs.getString("Telefono"));
                oPersonalBE.setContraseña(rs.getString("Contraseña"));
                oPersonalBE.setIdPerfil(rs.getInt("IdPerfil"));
                oPersonalBE.setEstado(rs.getBoolean("Estado"));
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
        
        return oPersonalBE;
    }
    
    public boolean insertUpdate(PersonalBE oPersonalBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Personal_InsertUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstm.setString(1, oPersonalBE.getConsulta());
            cstm.setInt(2, oPersonalBE.getIdPersonal());
            cstm.setString(3, oPersonalBE.getSexo());
            cstm.setString(4, oPersonalBE.getIdTipoDocIdentidad());
            cstm.setString(5, oPersonalBE.getDocIdentidad());
            cstm.setString(6, oPersonalBE.getNombres());
            cstm.setString(7, oPersonalBE.getApellidos());
            cstm.setString(8, oPersonalBE.getIdUbigeo());
            cstm.setString(9, oPersonalBE.getDomicilio());
            cstm.setString(10, oPersonalBE.getEmail());
            cstm.setString(11, oPersonalBE.getTelefono());
            cstm.setString(12, oPersonalBE.getContraseña());
            cstm.setInt(13, oPersonalBE.getIdPerfil());
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
    
    public boolean enableDisable(PersonalBE oPersonalBE) throws Exception{
        boolean result = false;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Personal_EnableDisable(?,?)}");
            cstm.setInt(1, oPersonalBE.getIdPersonal());
            cstm.setBoolean(2, oPersonalBE.isEstado());
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
    
    public PersonalBE loguear(String DocIdentidad, String Contraseña) throws Exception{
        PersonalBE oPersonalBE = null;
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Personal_Login(?,?)}");
            cstm.setString(1, DocIdentidad);
            cstm.setString(2, Contraseña);
            rs = cstm.executeQuery();
            while(rs.next()){
                oPersonalBE = new PersonalBE();
                oPersonalBE.setIdPersonal(rs.getInt("IdPersonal"));
                oPersonalBE.setSexo(rs.getString("Sexo"));
                oPersonalBE.setIdTipoDocIdentidad(rs.getString("IdTipoDocIdentidad"));
                oPersonalBE.setDocIdentidad(rs.getString("DocIdentidad"));
                oPersonalBE.setNombres(rs.getString("Nombres"));
                oPersonalBE.setApellidos(rs.getString("Apellidos"));
                oPersonalBE.setIdUbigeo(rs.getString("IdUbigeo") == null ? "" : rs.getString("IdUbigeo"));
                oPersonalBE.setDomicilio(rs.getString("Domicilio") == null ? "" : rs.getString("Domicilio"));
                oPersonalBE.setEmail(rs.getString("Email") == null ? "" : rs.getString("Email"));
                oPersonalBE.setTelefono(rs.getString("Telefono"));
                oPersonalBE.setContraseña(rs.getString("Contraseña"));
                oPersonalBE.setIdPerfil(rs.getInt("IdPerfil"));
                oPersonalBE.setEstado(rs.getBoolean("Estado"));
            }
        }
        catch(Exception ex){
            oPersonalBE = null;
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
        
        return oPersonalBE;
    }
}
