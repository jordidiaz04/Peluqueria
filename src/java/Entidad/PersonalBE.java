package Entidad;

public class PersonalBE {
    private String consulta = "I";
    
    private int IdPersonal;
    private String Sexo;
    private String IdTipoDocIdentidad;
    private String DocIdentidad;
    private String Nombres;
    private String Apellidos;
    private String IdUbigeo;
    private String Domicilio;
    private String Email;
    private String Telefono;
    private String Contraseña;
    private int IdPerfil;
    private boolean Estado;
    
    private String NombreCompleto;
    private String SexoDescripcion;
    private String TipoDocIdentidad;
    private String Distrito;
    private String Perfil;

    public PersonalBE() {
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(int IdPersonal) {
        this.IdPersonal = IdPersonal;
    }
    
    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
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

    public String getIdUbigeo() {
        return IdUbigeo;
    }

    public void setIdUbigeo(String IdUbigeo) {
        this.IdUbigeo = IdUbigeo;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
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

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public int getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(int IdPerfil) {
        this.IdPerfil = IdPerfil;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
    public String getSexoDescripcion() {
        return SexoDescripcion;
    }

    public void setSexoDescripcion(String SexoDescripcion) {
        this.SexoDescripcion = SexoDescripcion;
    }

    public String getTipoDocIdentidad() {
        return TipoDocIdentidad;
    }

    public void setTipoDocIdentidad(String TipoDocIdentidad) {
        this.TipoDocIdentidad = TipoDocIdentidad;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }
}
