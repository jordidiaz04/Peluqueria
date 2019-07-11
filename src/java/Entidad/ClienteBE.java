package Entidad;

public class ClienteBE {
    private String consulta = "I";
    
    private int IdCliente;
    private String IdTipoDocIdentidad;
    private String DocIdentidad;
    private String Nombres;
    private String Apellidos;
    private String Email;
    private String Telefono;
    private String Asistencia;
    
    private String NombreCompleto;
    private String TipoDocIdentidad;
    private String AsistenciaDescripcion;

    public ClienteBE() {
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getIdTipoDocIdentidad() {
        return IdTipoDocIdentidad;
    }

    public void setIdTipoDocIdentidad(String IdTipoDocIdentidad) {
        this.IdTipoDocIdentidad = IdTipoDocIdentidad;
    }

    public String getDocIdentidad() {
        return DocIdentidad;
    }

    public void setDocIdentidad(String DocIdentidad) {
        this.DocIdentidad = DocIdentidad;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getAsistencia() {
        return Asistencia;
    }

    public void setAsistencia(String Asistencia) {
        this.Asistencia = Asistencia;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public String getTipoDocIdentidad() {
        return TipoDocIdentidad;
    }

    public void setTipoDocIdentidad(String TipoDocIdentidad) {
        this.TipoDocIdentidad = TipoDocIdentidad;
    }

    public String getAsistenciaDescripcion() {
        return AsistenciaDescripcion;
    }

    public void setAsistenciaDescripcion(String AsistenciaDescripcion) {
        this.AsistenciaDescripcion = AsistenciaDescripcion;
    }
    
    
}
