package Controlador;

import Datos.AccesoDA;
import Datos.MenuDA;
import Datos.PerfilDA;
import Entidad.AccesoBE;
import Entidad.MenuBE;
import Entidad.PerfilBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccesoController", urlPatterns = {"/AccesoController.do"})
public class AccesoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String menusJSON;
            String perfilesJSON;
            String accesosJSON;
            boolean rspt;
            
            String Consulta = request.getParameter("Consulta");
            switch (Consulta) {
                case "LISTAR":
                    ArrayList<MenuBE> lstMenus = new MenuDA().listar();
                    ArrayList<PerfilBE> lstPerfiles = new PerfilDA().listar();
                    menusJSON = gson.toJson(lstMenus);
                    perfilesJSON = gson.toJson(lstPerfiles);
                    result = "[\"ok\"," + perfilesJSON + "," + menusJSON + "]";
                    break;
                case "OBTENER":
                    int IdPerfil = Integer.parseInt(request.getParameter("IdPerfil"));
                    ArrayList<AccesoBE> lstAccesos = new AccesoDA().obtener(IdPerfil);
                    accesosJSON = gson.toJson(lstAccesos);
                    result = "[\"ok\"," + accesosJSON + "]";
                    break;
                case "GUARDAR":
                    String Accesos = request.getParameter("Accesos");
                    boolean grabo = new AccesoDA().guardar(Accesos);
                    String rpta = gson.toJson(grabo);
                    result = "[\"ok\"," + rpta + "]";
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