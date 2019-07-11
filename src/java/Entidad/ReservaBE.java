package Entidad;

public class ReservaBE {
    private String consulta = "I";
    
    private int IdReserva;
    private int IdCliente;
    private int IdPersonal;
    private String Fecha;
    private String Hora;
    private String Estado;
    
    private String Personal;
    private String Cliente;

    public ReservaBE() {
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getIdReserva() {
        return IdReserva;
    }

    public void setIdReserva(int IdReserva) {
        this.IdReserva = IdReserva;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(int IdPersonal) {
        this.IdPersonal = IdPersonal;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String Personal) {
        this.Personal = Personal;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }
}
