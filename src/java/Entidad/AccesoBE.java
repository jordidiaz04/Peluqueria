package Entidad;

public class AccesoBE {
    private int IdPerfil;
    private int IdMenu;
    private boolean Estado;
    private String Menu;
    private String Icono;
    private boolean EsPadre;
    private int IdMenuPadre;
    private String Ruta;

    public AccesoBE() {
    }

    public int getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(int IdPerfil) {
        this.IdPerfil = IdPerfil;
    }

    public int getIdMenu() {
        return IdMenu;
    }

    public void setIdMenu(int IdMenu) {
        this.IdMenu = IdMenu;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String getMenu() {
        return Menu;
    }

    public void setMenu(String Menu) {
        this.Menu = Menu;
    }

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String Icono) {
        this.Icono = Icono;
    }

    public boolean isEsPadre() {
        return EsPadre;
    }

    public void setEsPadre(boolean EsPadre) {
        this.EsPadre = EsPadre;
    }

    public int getIdMenuPadre() {
        return IdMenuPadre;
    }

    public void setIdMenuPadre(int IdMenuPadre) {
        this.IdMenuPadre = IdMenuPadre;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }
}
