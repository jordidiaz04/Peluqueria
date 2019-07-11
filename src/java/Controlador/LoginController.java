package Controlador;

import Datos.PersonalDA;
import Entidad.PersonalBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController.do"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try{
            String result = "";
            String Consulta = request.getParameter("Consulta");
            switch(Consulta){
                case "LOGIN":
                    String Usuario = request.getParameter("Usuario");
                    String Contraseña = request.getParameter("Contraseña");
                    PersonalBE oPersonalBE = new PersonalDA().loguear(Usuario, Contraseña);

                    String usuarioJSON = new Gson().toJson(oPersonalBE);
                    String rpta = new Gson().toJson("ok");
                    result = "[" + rpta + "," + usuarioJSON + "]";
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
