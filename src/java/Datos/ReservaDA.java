package Datos;

import Entidad.ReservaBE;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaDA {
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;
    private static Conexion cn;
    
    public ArrayList<ReservaBE> listar(String fechaInicial, String fechaFinal) throws Exception{
        ArrayList<ReservaBE> lstReservaBE = new ArrayList<>();
        try{
            cn = Conexion.instanciar();
            cstm = cn.conectar().prepareCall("{call Sp_Reserva_Listar(?,?)}");
            cstm.setString(1, fechaInicial);
            cstm.setString(2, fechaFinal);
            rs = cstm.executeQuery();
            while(rs.next()){
                ReservaBE oReservaBE = new ReservaBE();
                oReservaBE.setIdReserva(rs.getInt("IdReserva"));
                oReservaBE.setIdPersonal(rs.getInt("IdPersonal"));
                oReservaBE.setPersonal(rs.getString("Personal"));
                oReservaBE.setIdCliente(rs.getInt("IdCliente"));
                oReservaBE.setCliente(rs.getString("Cliente"));
                oReservaBE.setFecha(rs.getString("Fecha"));
                oReservaBE.setHora(rs.getString("Hora"));
                oReservaBE.setEstado(rs.getString("Estado"));
                lstReservaBE.add(oReservaBE);
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
        
        return lstReservaBE;
    }
}
