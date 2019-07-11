package Controlador;

import Datos.MenuDA;
import Entidad.MenuBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MenuController", urlPatterns = {"/MenuController.do"})
public class MenuController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String menuJSON = "";
            String menusJSON = "";
            ArrayList<MenuBE> lstMenuBE;
            MenuBE oMenuBE;
            int IdMenu;
            boolean rspt;
            
            String Consulta = request.getParameter("Consulta");
            switch (Consulta) {
                case "LISTAR":
                    lstMenuBE = new MenuDA().listar();
                    menusJSON = gson.toJson(lstMenuBE);
                    result = "[\"ok\"," + menusJSON + "]";
                    break;
                case "OBTENER":
                    IdMenu = Integer.parseInt(request.getParameter("IdMenu"));
                    oMenuBE = new MenuDA().obtener(IdMenu);
                    menuJSON = gson.toJson(oMenuBE);
                    result = "[\"ok\"," + menuJSON + "]";
                    break;
                case "INSERT":
                case "UPDATE":
                    IdMenu = Integer.parseInt(request.getParameter("IdMenu"));
                    String Titulo = request.getParameter("Titulo");
                    String Ruta = request.getParameter("Ruta");
                    boolean EsPadre = Boolean.parseBoolean(request.getParameter("EsPadre"));
                    int IdMenuPadre = Integer.parseInt(request.getParameter("IdMenuPadre"));
                    String Icono = request.getParameter("Icono");
                    
                    oMenuBE = new MenuBE();
                    oMenuBE.setConsulta(Consulta.equals("INSERT") ? "I" : "U");
                    oMenuBE.setIdMenu(IdMenu);
                    oMenuBE.setTitulo(Titulo);
                    oMenuBE.setRuta(Ruta);
                    oMenuBE.setEsPadre(EsPadre);
                    oMenuBE.setIdMenuPadre(IdMenuPadre);
                    oMenuBE.setIcono(Icono);                    
                    rspt = new MenuDA().insertUpdate(oMenuBE);
                    if(rspt){
                        lstMenuBE = new MenuDA().listar();
                        menusJSON = gson.toJson(lstMenuBE);
                    }
                    result = "[" + rspt + "," + menusJSON + "]";
                    break;
                case "ENABLE":
                case "DISABLE":
                    IdMenu = Integer.parseInt(request.getParameter("IdMenu"));
                    boolean Estado = Boolean.parseBoolean(request.getParameter("Estado"));
                    oMenuBE = new MenuBE();
                    oMenuBE.setIdMenu(IdMenu);
                    oMenuBE.setEstado(Estado);
                    rspt = new MenuDA().enableDisable(oMenuBE);
                    if(rspt){
                        lstMenuBE = new MenuDA().listar();
                        menusJSON = gson.toJson(lstMenuBE);
                    }
                    result = "[" + rspt + "," + menusJSON + "]";
                    break;
            }
            out.print(result);
            out.flush();
        } catch (Exception ex) {
            String error = gson.toJson(ex.getMessage());
            result = "[\"error\" ," + error + "]";
            out.print(result);
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
