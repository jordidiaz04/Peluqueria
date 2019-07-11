package Controlador;

import Datos.ServicioDA;
import Entidad.ServicioBE;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServicioController", urlPatterns = {"/ServicioController.do"})
public class ServicioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        String result = "[\"error\", \"No se encontró la función en el servidor.\"]";
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try{
            String servicioJSON = "";
            String listaServiciosJSON = "";
            ArrayList<ServicioBE> lstServicioBE;
            ServicioBE oServicioBE;
            int IdServicio;
            boolean rspt;
            
            String Consulta = request.getParameter("Consulta");
            switch(Consulta){
                case "LISTAR":                  
                    lstServicioBE = new ServicioDA().listar();                   
                    listaServiciosJSON = gson.toJson(lstServicioBE);
                    result = "[\"ok\"," + listaServiciosJSON + "]";
                    break;
                case "OBTENER":
                    IdServicio = Integer.parseInt(request.getParameter("IdServicio"));
                    oServicioBE = new ServicioDA().obtener(IdServicio);
                    servicioJSON = gson.toJson(oServicioBE);
                    result = "[\"ok\"," + servicioJSON + "]";
                    break;
                case "INSERT":
                case "UPDATE":
                    IdServicio = Integer.parseInt(request.getParameter("IdServicio"));
                    String Nombre = request.getParameter("Nombre");
                    String Detalle = request.getParameter("Detalle");
                    double Precio = Double.parseDouble(request.getParameter("Precio"));
                    oServicioBE = new ServicioBE();
                    oServicioBE.setConsulta(Consulta.equals("INSERT") ? "I" : "U");
                    oServicioBE.setIdServicio(IdServicio);
                    oServicioBE.setNombre(Nombre);
                    oServicioBE.setDetalle(Detalle);
                    oServicioBE.setPrecio(Precio);
                    rspt = new ServicioDA().insertUpdate(oServicioBE);
                    if(rspt){
                        lstServicioBE = new ServicioDA().listar();                   
                        listaServiciosJSON = gson.toJson(lstServicioBE);
                    }
                    result = "[\"ok\"," + listaServiciosJSON + "]";
                    break;
                case "ENABLE":
                case "DISABLE":
                    IdServicio = Integer.parseInt(request.getParameter("IdServicio"));
                    boolean Estado = Boolean.parseBoolean(request.getParameter("Estado"));
                    oServicioBE = new ServicioBE();
                    oServicioBE.setIdServicio(IdServicio);
                    oServicioBE.setEstado(Estado);
                    rspt = new ServicioDA().enableDisable(oServicioBE);
                    if(rspt){
                        lstServicioBE = new ServicioDA().listar();                   
                        listaServiciosJSON = gson.toJson(lstServicioBE);
                    }
                    result = "[" + rspt + "," + listaServiciosJSON + "]";
                    break;
            }
        }
        catch(Exception ex){
            String error = gson.toJson(ex.getMessage());
            result = "[\"error\" ," + error + "]";
        }
        
        out.print(result);
        out.flush();
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