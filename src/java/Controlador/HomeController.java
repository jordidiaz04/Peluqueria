package Controlador;

import Datos.AccesoDA;
import Entidad.AccesoBE;
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

@WebServlet(name = "HomeController", urlPatterns = {"/HomeController.do"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try{
            String result = "";
            String Consulta = request.getParameter("Consulta");
            switch(Consulta){
                case "MENUS":
                    int IdPerfil = Integer.parseInt(request.getParameter("IdPerfil"));
                    List<AccesoBE> lstAccesoBE = new AccesoDA().listar(IdPerfil);
                    String accesosJSON = new Gson().toJson(lstAccesoBE);
                    String rpta = new Gson().toJson("ok");
                    result = "[" + rpta + "," + accesosJSON + "]";
                    break;
            }
            out.print(result);
            out.flush();
        }
        catch(Exception ex){
            String rpta = new Gson().toJson("error");
            String error = new Gson().toJson(ex.getMessage());
            String result = "[" + rpta + "," + error + "]";
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
