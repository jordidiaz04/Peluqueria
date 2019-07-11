package Entidad;

public class MenuBE {
    private String consulta = "I";
    
    private int IdMenu;
    private String Titulo;
    private String Ruta;
    private boolean EsPadre;
    private int IdMenuPadre;
    private String Icono;
    private boolean Estado;    
    private String MenuPadre;

    public MenuBE() {
    }
    
    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getIdMenu() {
        return IdMenu;
    }

    public void setIdMenu(int IdMenu) {
        this.IdMenu = IdMenu;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
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

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String Icono) {
        this.Icono = Icono;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String getMenuPadre() {
        return MenuPadre;
    }

    public void setMenuPadre(String MenuPadre) {
        this.MenuPadre = MenuPadre;
    }
}
