package Datos;

import Entidad.PerfilBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<PerfilBE> listar() throws Exception{
        ArrayList<PerfilBE> lstPerfilBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Perfil_Listar()}");
            rs = cstm.executeQuery();
            while(rs.next()){
                PerfilBE oPerfilBE = new PerfilBE();
                oPerfilBE.setIdPerfil(rs.getInt("IdPerfil"));
                oPerfilBE.setDescripcion(rs.getString("Descripcion") == null ? "" : rs.getString("Descripcion"));
                oPerfilBE.setEstado(rs.getBoolean("Estado"));
                lstPerfilBE.add(oPerfilBE);
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
        
        return lstPerfilBE;
    }
}
