package Datos;

import Entidad.MultitablaBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MultitablaDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<MultitablaBE> listar(int IdMultitabla) throws Exception{
        ArrayList<MultitablaBE> lstMultitablaBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Multitabla_Listar(?)}");
            cstm.setInt(1, IdMultitabla);
            rs = cstm.executeQuery();
            while(rs.next()){
                MultitablaBE oMultitablaBE = new MultitablaBE();
                oMultitablaBE.setIdMultitabla(rs.getInt("IdMultitabla"));
                oMultitablaBE.setCodigo(rs.getString("Codigo"));
                oMultitablaBE.setDescripcion(rs.getString("Descripcion"));
                oMultitablaBE.setLongitud(rs.getInt("Longitud"));
                oMultitablaBE.setDetalle(rs.getString("Detalle"));
                lstMultitablaBE.add(oMultitablaBE);
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
        
        return lstMultitablaBE;
    }
}
